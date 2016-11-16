package net.bobstudio.so.api;

import net.bobstudio.so.domain.Supplier;
import net.bobstudio.so.dto.SupplierVo;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.SupplierService;

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
public class SupplierEndPoint {

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/api/suppliers/{id}", produces = MediaTypes.JSON_UTF_8)
	public SupplierVo getOneSupplier(@PathVariable("id") Long id, Model model) {
		Supplier supplier = supplierService.findOne(id);

		SupplierVo pvo = BeanMapper.map(supplier, SupplierVo.class);
		return pvo;
	}

	// @RequiresPermissions("supplier:edit")
	@RequestMapping(value = "/api/suppliers/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public SupplierVo createSupplier(@RequestBody SupplierVo supplierVo,
	        UriComponentsBuilder uriBuilder) {
		Supplier supplier = BeanMapper.map(supplierVo, Supplier.class);
		supplierService.saveSupplier(supplier);

		return BeanMapper.map(supplier, SupplierVo.class);
	}

	// @RequiresPermissions("supplier:edit")
	@RequestMapping(value = "/api/suppliers/{id}/delete")
	public void deleteSupplier(@PathVariable("id") Long id) {
		supplierService.deleteSupplier(id);
	}

}
