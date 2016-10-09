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

import net.bobstudio.so.domain.Material;
import net.bobstudio.so.dto.MaterialVo;
import net.bobstudio.so.service.MaterialService;

/**
 * @author Walter <zhanggx2003@126.com> 2016年9月29日
 */
@Controller
@RequestMapping("/materials")
public class MaterialController {
	@Autowired
	private MaterialService materialService;

	@GetMapping("main")
	public ModelAndView list(@ModelAttribute MaterialVo materialVo) {
		Iterable<Material> materials = materialService.findAll();
		return new ModelAndView("products/materialsList", "materials",
				BeanMapper.mapList(materials, MaterialVo.class));
	}

	@PostMapping("create")
	public ModelAndView create(@Valid MaterialVo materialVo, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("materials/main#addProduct", "formErrors", result.getAllErrors());
		}
		redirect.addFlashAttribute("globalMaterial", "Successfully created a new material");

		Material material = BeanMapper.map(materialVo, Material.class);
		materialService.saveMaterial(material);
		return list(new MaterialVo());
	}

}
