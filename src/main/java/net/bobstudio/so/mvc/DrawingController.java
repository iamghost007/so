/**
 * 
 */
package net.bobstudio.so.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.service.DrawingService;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
@Controller
@RequestMapping("/products/drawings")
public class DrawingController {
	@Autowired
	private DrawingService drawingService;
	
	@GetMapping("list")
	public ModelAndView list() {
		Iterable<Drawing> drawings = drawingService.findAll();
		return new ModelAndView("products/drawings/list", "drawings", drawings);
	}

}
