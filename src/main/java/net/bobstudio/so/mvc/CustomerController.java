package net.bobstudio.so.mvc;

import java.util.Map;

import javax.servlet.ServletRequest;

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

import net.bobstudio.so.domain.Customer;
import net.bobstudio.so.dto.CustomerVo;
import net.bobstudio.so.dto.PageVo;
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
	public ModelAndView list(@ModelAttribute CustomerVo customerVo,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Page<Customer> customers = customerService.findAll(searchParams, pageNumber, pageSize, sortType);
		PageModel.setModelForPage(sortType, model, new PageVo("/customers", customers));

		return new ModelAndView("customers/customerList", "customers", BeanMapper.mapList(customers.getContent(), CustomerVo.class));
	}

}
