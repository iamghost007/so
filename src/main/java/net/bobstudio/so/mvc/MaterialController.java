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

import com.google.common.collect.Maps;

import net.bobstudio.so.domain.Material;
import net.bobstudio.so.domain.MaterialInstock;
import net.bobstudio.so.domain.MaterialOutstock;
import net.bobstudio.so.dto.MaterialVo;
import net.bobstudio.so.dto.MaterialInstockVo;
import net.bobstudio.so.dto.MaterialOutstockVo;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.dto.Status;
import net.bobstudio.so.service.MaterialService;

/**
 * @author Walter <zhanggx2003@126.com> 2016年9月29日
 */
@Controller
@RequestMapping("/materials")
public class MaterialController {
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("name", "名称");
	}

	@Autowired
	private MaterialService materialService;

	@GetMapping("main")
	public ModelAndView list(@ModelAttribute MaterialVo materialVo,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<Material> materials = materialService.findAll(searchParams, pageNumber, pageSize,
				sortType);
		model.addAttribute("allStatus", Status.values());
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("page", new PageVo("/materials", materials));

		return new ModelAndView("products/materialsList", "materials", BeanMapper.mapList(materials.getContent(), MaterialVo.class));
	}

	@GetMapping("/instocks/main")
	public ModelAndView listInstock(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<MaterialInstock> mateInstocks = materialService.findAllInstock(searchParams, pageNumber, pageSize,
				sortType);

		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("page", new PageVo("/materials/instocks", mateInstocks));

		return new ModelAndView("products/mateInstocksList", "mateInstocks",
				BeanMapper.mapList(mateInstocks.getContent(), MaterialInstockVo.class));
	}

	@GetMapping("/outstocks/main")
	public ModelAndView listOutstock(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<MaterialOutstock> mateOutstocks = materialService.findAllOutstock(searchParams, pageNumber, pageSize,
				sortType);

		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("page", new PageVo("/materials/outstocks", mateOutstocks));

		return new ModelAndView("products/mateOutstocksList", "mateOutstocks",
				BeanMapper.mapList(mateOutstocks.getContent(), MaterialOutstockVo.class));
	}

}
