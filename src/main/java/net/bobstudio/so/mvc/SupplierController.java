package net.bobstudio.so.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;

import net.bobstudio.so.domain.Supplier;
import net.bobstudio.so.dto.SupplierVo;
import net.bobstudio.so.dto.Status;
import net.bobstudio.so.service.SupplierService;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute SupplierVo supplierVo, Model model) {
		Iterable<Supplier> suppliers = supplierService.findSuppliers();
		model.addAttribute("allStatus", Status.values());
			
		return new ModelAndView("suppliers/supplierList","suppliers", BeanMapper.mapList(suppliers, SupplierVo.class));
	}
	
}
