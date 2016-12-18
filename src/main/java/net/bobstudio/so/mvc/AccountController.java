/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bobstudio.so.mvc;

import java.util.Map;

import javax.servlet.ServletRequest;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.dto.AccountVo;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;
import org.springside.modules.web.Servlets;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("main")
	public ModelAndView list(@ModelAttribute AccountVo accountVo, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Page<Account> accounts = accountService.findAll(searchParams, pageNumber, pageSize, sortType);
		
		PageModel.setModelForPage(sortType, model, new PageVo("/accounts", accounts));

		return new ModelAndView("accounts/userList", "accounts", BeanMapper.mapList(accounts.getContent(), AccountVo.class));
	}

}
