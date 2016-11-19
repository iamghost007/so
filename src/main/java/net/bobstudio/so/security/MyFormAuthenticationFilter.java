package net.bobstudio.so.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	private static Logger logger = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

	@Override 
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,ServletResponse response) throws Exception {
		logger.info("Change Login Main Url......");
		
		WebUtils.getAndClearSavedRequest(request);	//清理原先的地址

		WebUtils.redirectToSavedRequest(request, response, "/home");

		return false;
	}
}
