/**
 * 
 */
package net.bobstudio.so.mvc;

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

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.dto.DrawingVo;
import net.bobstudio.so.service.DrawingService;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
@Controller
@RequestMapping("/drawings")
public class DrawingController {
	@Autowired
	private DrawingService drawingService;
	
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute DrawingVo drawingVo) {
		Iterable<Drawing> drawings = drawingService.findAll();
		return new ModelAndView("products/drawingsList", "drawings", BeanMapper.mapList(drawings,DrawingVo.class));
	}
	
	@PostMapping("create")
	//@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public ModelAndView create(@Valid DrawingVo drawingVo, BindingResult result,
	        RedirectAttributes redirect) {
		if (result.hasErrors()) {
			//return new ModelAndView("drawings/main#addProduct", "formErrors", result.getAllErrors());
		}
		redirect.addFlashAttribute("globalDrawing", "Successfully created a new Drawing");
		
		Drawing drawing = BeanMapper.map(drawingVo, Drawing.class);
		drawingService.saveDrawing(drawing);
		return list(new DrawingVo());
	}

}
