/**
 * 
 */
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.mapper.BeanMapper;
import org.springside.modules.web.Servlets;

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.dto.DrawingVo;
import net.bobstudio.so.dto.PageVo;
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
			@RequestParam(value = "message", required = false) String message, RedirectAttributes redirect,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<Drawing> drawings = drawingService.findAll(searchParams, pageNumber, pageSize, sortType);
		PageModel.setModelForPage(sortType, model, new PageVo("/drawings", drawings));

		if (!"message".equals(message)) {
			redirect.addFlashAttribute("globalTip", message);
		}

		return new ModelAndView("products/drawingsList", "drawings", BeanMapper.mapList(drawings.getContent(), DrawingVo.class));
	}
}
