/**
 * 
 */
package net.bobstudio.so.mvc;

import net.bobstudio.so.domain.Product;
import net.bobstudio.so.domain.ProductInstock;
import net.bobstudio.so.domain.ProductOutstock;
import net.bobstudio.so.dto.ProductInstockVo;
import net.bobstudio.so.dto.ProductOutstockVo;
import net.bobstudio.so.dto.ProductVo;
import net.bobstudio.so.dto.Status;
import net.bobstudio.so.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute ProductVo productVo, Model model) {
		Iterable<Product> products = productService.findAll();
		model.addAttribute("allStatus", Status.values());
		
		return new ModelAndView("products/productsList", "products", BeanMapper.mapList(products, ProductVo.class));
	}
	
	@GetMapping("/instocks/main")
	public ModelAndView listInstock() {
		Iterable<ProductInstock> prodInstocks = productService.findAllInstock();
		
		return new ModelAndView("products/prodInstocksList", "prodInstocks", BeanMapper.mapList(prodInstocks, ProductInstockVo.class));
	}
	
	@GetMapping("/outstocks/main")
	public ModelAndView listOutstock() {
		Iterable<ProductOutstock> prodOutstocks = productService.findAllOutstock();
		
		return new ModelAndView("products/prodOutstocksList", "prodOutstocks", BeanMapper.mapList(prodOutstocks, ProductOutstockVo.class));
	}
	
}
