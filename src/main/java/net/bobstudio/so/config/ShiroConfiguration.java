package net.bobstudio.so.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.bobstudio.so.security.MyFormAuthenticationFilter;
import net.bobstudio.so.security.ShiroDbRealm;

@Configuration
public class ShiroConfiguration {
	private static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getSecurityManager() {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(getShiroDbRealm());
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}

	@Bean(name="shiroEhcacheManager")
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}

	@Bean
	public ShiroDbRealm getShiroDbRealm() {
		ShiroDbRealm realm = new ShiroDbRealm();
		realm.setCacheManager(getEhCacheManager());
		return realm;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
		logger.info("ShiroConfiguration:::getShiroFilterFactoryBean");
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(getSecurityManager());
		shiroFilter.setLoginUrl("/login");
		shiroFilter.setSuccessUrl("/home");
		
		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/login");
		filters.put("logout", logoutFilter);
		filters.put("authc", getFormAuthenticationFilter());
		shiroFilter.setFilters(filters);
		
		Map<String, String> filterChainDefinitions = new LinkedHashMap<String, String>();
		filterChainDefinitions.put("/", "anon");
		filterChainDefinitions.put("/js/**", "anon");
		filterChainDefinitions.put("/css/**", "anon");
		filterChainDefinitions.put("/api/accounts/login", "anon");
		filterChainDefinitions.put("/logout", "logout");
		filterChainDefinitions.put("/login", "authc");
		filterChainDefinitions.put("/error/**", "anon");
		filterChainDefinitions.put("/accounts/**", "perms[account:view]");
		filterChainDefinitions.put("/roles/**", "perms[account:view]");
		filterChainDefinitions.put("/products/**", "perms[product:view]");
		filterChainDefinitions.put("/materials/**", "perms[product:view]");
		filterChainDefinitions.put("/plans/**", "perms[plan:view]");
		filterChainDefinitions.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitions);
		
		shiroFilter.setUnauthorizedUrl("/error/403.html");

		
		return shiroFilter;
	}
	
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(getSecurityManager());
		return aasa;
	}
	
	@Bean(name = "shiroDialect")
	public ShiroDialect getShiroDialect(){
		return new ShiroDialect();
	}
	
	@Bean
	public SimpleMappingExceptionResolver getMappingExceptionResolver(){
		SimpleMappingExceptionResolver mappingExceptionResolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.apache.shiro.authz.UnauthorizedException", "/error/403.html");
		mappings.put("org.apache.shiro.authz.UnauthenticatedException", "/error/403.html");
		mappings.put("org.apache.shiro.authc.DisabledAccountException", "/error/403.html");
		mappings.put("java.lang.Throwable", "/error/500.html");
		
		mappingExceptionResolver.setExceptionMappings(mappings);
		
		return mappingExceptionResolver;
	}
	
	@Bean(name = "myFormAuthenticationFilter") 
	public FormAuthenticationFilter getFormAuthenticationFilter(){
		return new MyFormAuthenticationFilter();
	}

	/**
	 * 注册DelegatingFilterProxy（Shiro） 集成Shiro有2种方法： 1.
	 * 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
	 * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了） 2.
	 * 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
	 * 默认拦截 /*）
	 *
	 * @param dispatcherServlet
	 * @return
	 * @author SHANHY
	 * @create 2016年1月13日
	 */
	// @Bean
	// public FilterRegistrationBean filterRegistrationBean() {
	// FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
	// filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
	// //
	// 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
	// filterRegistration.addInitParameter("targetFilterLifecycle", "true");
	// filterRegistration.setEnabled(true);
	// filterRegistration.addUrlPatterns("/*");//
	// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
	// return filterRegistration;
	// }
}