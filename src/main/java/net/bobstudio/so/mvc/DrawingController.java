/**
 * 
 */
package net.bobstudio.so.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.mapper.BeanMapper;

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.dto.DrawingVo;
import net.bobstudio.so.service.DrawingService;

/**
 * @author Walter <zhanggx2003@126.com> 2016年9月29日
 */
@Controller
@RequestMapping("/drawings")
public class DrawingController {
	@Autowired
	private DrawingService drawingService;

	@GetMapping("main")
	public ModelAndView list(@ModelAttribute DrawingVo drawingVo,
	        @RequestParam(value = "message", required = false) String message,
	        RedirectAttributes redirect) {
		Iterable<Drawing> drawings = drawingService.findAll();
		
		if(!"message".equals(message)){
			redirect.addFlashAttribute("globalTip", message);
		}
		
		return new ModelAndView("products/drawingsList", "drawings", BeanMapper.mapList(drawings,
		        DrawingVo.class));
	}
}
