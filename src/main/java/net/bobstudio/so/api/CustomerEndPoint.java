package net.bobstudio.so.api;

import net.bobstudio.so.domain.Customer;
import net.bobstudio.so.dto.CustomerVo;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.modules.constants.MediaTypes;
import org.springside.modules.mapper.BeanMapper;

@RestController
public class CustomerEndPoint {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/api/customers/{id}", produces = MediaTypes.JSON_UTF_8)
	public CustomerVo getOneCustomer(@PathVariable("id") Long id, Model model) {
		Customer customer = customerService.findOne(id);

		CustomerVo pvo = BeanMapper.map(customer, CustomerVo.class);
		return pvo;
	}

	// @RequiresPermissions("customer:edit")
	@RequestMapping(value = "/api/customers/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public CustomerVo createCustomer(@RequestBody CustomerVo customerVo,
	        UriComponentsBuilder uriBuilder) {
		Customer customer = BeanMapper.map(customerVo, Customer.class);
		customerService.saveCustomer(customer);

		return BeanMapper.map(customer, CustomerVo.class);
	}

	// @RequiresPermissions("customer:edit")
	@RequestMapping(value = "/api/customers/{id}/delete")
	public void deleteCustomer(@PathVariable("id") Long id) {
		customerService.deleteCustomer(id);
	}

}
