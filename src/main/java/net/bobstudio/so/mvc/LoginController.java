package net.bobstudio.so.mvc;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bobstudio.so.service.AccountService;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	private AccountService accountService;

	@GetMapping
	public String login() {
		System.out.println("开始登陆啦。。。。。"+new java.util.Date());
		return "accounts/login";
	}

	@RequestMapping("login")
	public String fail(Model model) {
		if(accountService.getCurrentUserId() == -1L) {
			model.addAttribute("logerror", "");
			return "accounts/login";
		}
		else {
			return "home";
		}
	}
	
	@RequestMapping("home")
	public String home(Model model) {
		model.addAttribute("currentUserName", accountService.getCurrentUserName());
		return "home";
	}
}
