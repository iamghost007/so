package net.bobstudio.so.api;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.dto.DrawingVo;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.DrawingService;
import net.bobstudio.so.service.exception.ErrorCode;
import net.bobstudio.so.service.exception.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.modules.constants.MediaTypes;
import org.springside.modules.mapper.BeanMapper;

// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class AccountEndPoint {

	private static Logger logger = LoggerFactory.getLogger(AccountEndPoint.class);

	@Autowired
	private AccountService accountServcie;

	@RequestMapping(value = "/api/accounts/login", produces = MediaTypes.JSON_UTF_8)
	public Map<String, String> login(@RequestParam("email") String email,
	        @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or password empty", ErrorCode.BAD_REQUEST);
		}

		String token = accountServcie.login(email, password);

		return Collections.singletonMap("token", token);
	}

	@RequestMapping(value = "/api/accounts/logout")
	public void logout(@RequestParam(value = "token", required = false) String token) {
		accountServcie.logout(token);
	}

	@RequestMapping(value = "/api/accounts/register")
	public void register(@RequestParam("email") String email,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(name)
		        || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or name or password empty", ErrorCode.BAD_REQUEST);
		}

		accountServcie.register(email, name, password);
	}

	@Autowired
	private DrawingService drawingService;

	@RequestMapping(value = "/api/drawings/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public DrawingVo createDrawing(@RequestBody DrawingVo drawingVo,
	        UriComponentsBuilder uriBuilder) {
		Drawing drawing = BeanMapper.map(drawingVo, Drawing.class);
		drawingService.saveDrawing(drawing);

//		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
//		URI uri = uriBuilder.path("/products/drawingsList").build().toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(uri);
//
//		return new ResponseEntity<DrawingVo>(headers, HttpStatus.CREATED);
		drawingVo.setId(drawing.id);
		return drawingVo;

	}
}
