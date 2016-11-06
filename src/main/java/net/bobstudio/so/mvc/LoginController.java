package net.bobstudio.so.mvc;

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
		System.out.println("开始登陆啦。。。。。");
		return "accounts/login";
	}

	@RequestMapping("login")
	public String fail(Model model) {
		model.addAttribute("logerror", "");
		return "accounts/login";
	}
	
	@RequestMapping("home")
	public String home(Model model) {
		model.addAttribute("currentUserName", accountService.getCurrentUserName());
		return "home";
	}
}
