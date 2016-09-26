/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package net.bobstudio.so.functional;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.test.data.RandomData;

import com.google.common.collect.Maps;

import net.bobstudio.so.api.support.ErrorResult;
import net.bobstudio.so.service.exception.ErrorCode;

// 测试方法的执行顺序在不同JVM里是不固定的，此处设为按方法名排序，避免方法间数据影响的不确定性
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookEndpointTest extends BaseFunctionalTest {

	// 注入Spring Context中的BookDao，实现白盒查询数据库实际情况

	private RestTemplate restTemplate;
	private JsonMapper jsonMapper = new JsonMapper();

	private String loginUrl;
	private String logoutUrl;

	@Before
	public void setup() {
		// TestRestTemplate与RestTemplate, 服务端返回非200返回码时，不会抛异常.
		restTemplate = new TestRestTemplate();
		loginUrl = "http://localhost:" + port + "/api/accounts/login";
		logoutUrl = "http://localhost:" + port + "/api/accounts/logout";
	}


	@Test
	public void applyRequest() {
		String token = login("calvin.xiao@springside.io");

		logout(token);
	}

	private String login(String user) {
		Map<String, String> map = Maps.newHashMap();
		map.put("email", user);
		map.put("password", "springside");

		ResponseEntity<Map> response = restTemplate.getForEntity(loginUrl + "?email={email}&password={password}",
				Map.class, map);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		return (String) response.getBody().get("token");
	}

	public void logout(String token) {
		restTemplate.getForEntity(logoutUrl + "?token={token}", String.class, token);
	}
}
