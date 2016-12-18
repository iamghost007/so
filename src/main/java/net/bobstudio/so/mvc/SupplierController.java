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

import net.bobstudio.so.domain.Supplier;
import net.bobstudio.so.dto.SupplierVo;
import net.bobstudio.so.dto.PageVo;
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
	public ModelAndView list(@ModelAttribute SupplierVo supplierVo,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<Supplier> suppliers = supplierService.findSuppliers(searchParams, pageNumber, pageSize, sortType);
		
		PageModel.setModelForPage(sortType, model, new PageVo("/suppliers", suppliers));

		return new ModelAndView("suppliers/supplierList", "suppliers", BeanMapper.mapList(suppliers.getContent(), SupplierVo.class));
	}

}
