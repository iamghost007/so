/**
 * 
 */
package net.bobstudio.so.mvc;

import net.bobstudio.so.domain.Product;
import net.bobstudio.so.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView list() {
		Iterable<Product> products = productService.findAll();
		return new ModelAndView("products/main", "products", products);
	}

}
