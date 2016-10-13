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
import net.bobstudio.so.service.ProductService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	public ModelAndView list(@ModelAttribute ProductVo productVo) {
		Iterable<Product> products = productService.findAll();
		
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
	
	@PostMapping("create")
	public ModelAndView create(@Valid ProductVo productVo, BindingResult result,
	        RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("products/main#addProduct", "formErrors", result.getAllErrors());
		}
		redirect.addFlashAttribute("globalProduct", "Successfully created a new product");
		
		Product product = BeanMapper.map(productVo, Product.class);
		productService.saveProduct(product);
		return list(new ProductVo());
	}

}
