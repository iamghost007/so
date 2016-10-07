/**
 * 
 */
package net.bobstudio.so.mvc;

import net.bobstudio.so.domain.Product;
import net.bobstudio.so.dto.ProductDto;
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
	public ModelAndView list(@ModelAttribute ProductDto productDto) {
		Iterable<Product> products = productService.findAll();
		
		return new ModelAndView("products/main", "products", BeanMapper.mapList(products, ProductDto.class));
	}
	
	@PostMapping("create")
	public ModelAndView create(@Valid ProductDto productDto, BindingResult result,
	        RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("products/main#addProduct", "formErrors", result.getAllErrors());
		}
		redirect.addFlashAttribute("globalProduct", "Successfully created a new product");
		
		Product product = BeanMapper.map(productDto, Product.class);
		productService.saveProduct(product);
		return list(new ProductDto());
	}

}
