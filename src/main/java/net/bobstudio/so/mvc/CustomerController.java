package net.bobstudio.so.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;

import net.bobstudio.so.domain.Customer;
import net.bobstudio.so.dto.CustomerVo;
<<<<<<< HEAD
import net.bobstudio.so.dto.Status;
=======
>>>>>>> 8b855269d39a12fec31b6d5442d7fca84c6c9b13
import net.bobstudio.so.service.CustomerService;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute CustomerVo customerVo, Model model) {
		Iterable<Customer> customers = customerService.findCustomers();
		model.addAttribute("allStatus", Status.values());
			
		return new ModelAndView("customers/customerList","customers", BeanMapper.mapList(customers, CustomerVo.class));
	}
	
}