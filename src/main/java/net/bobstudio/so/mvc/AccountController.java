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

import javax.validation.Valid;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.repository.AccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author Bob Zhang 
 * 2016.9.26
 */
@Controller
@RequestMapping("/")
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@GetMapping
	public ModelAndView list() {
		Iterable<Account> accounts = accountDao.findAll();
		return new ModelAndView("accounts/list", "accounts", accounts);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Account account) {
		return new ModelAndView("accounts/view", "account", account);
	}

	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Account account) {
		return "accounts/form";
	}

	@PostMapping
	public ModelAndView create(@Valid Account account, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("accounts/form", "formErrors", result.getAllErrors());
		}
		account = accountDao.save(account);
		redirect.addFlashAttribute("globalAccount", "Successfully created a new account");
		return new ModelAndView("redirect:/{account.id}", "account.id", account.id);
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		accountDao.deleteAccount(id);
		Iterable<Account> accounts = accountDao.findAll();
		return new ModelAndView("accounts/list", "accounts", accounts);
	}

	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Account account) {
		return new ModelAndView("accounts/form", "account", account);
	}

}
