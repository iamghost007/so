/**
 * 
 */
package net.bobstudio.so.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.bobstudio.so.domain.Material;
import net.bobstudio.so.service.MaterialService;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
@Controller
@RequestMapping("/products/materials")
public class MaterialController {
	@Autowired
	private MaterialService materialService;
	
	@GetMapping("list")
	public ModelAndView list() {
		Iterable<Material> materials = materialService.findAll();
		return new ModelAndView("products/materials/list", "products", materials);
	}

}
