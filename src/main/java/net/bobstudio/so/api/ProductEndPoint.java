
package net.bobstudio.so.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.modules.constants.MediaTypes;
import org.springside.modules.mapper.BeanMapper;

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.domain.Material;
import net.bobstudio.so.domain.Product;
import net.bobstudio.so.dto.DrawingVo;
import net.bobstudio.so.dto.MaterialVo;
import net.bobstudio.so.dto.ProductVo;
import net.bobstudio.so.service.DrawingService;
import net.bobstudio.so.service.MaterialService;
import net.bobstudio.so.service.ProductService;

/**
 * @author Bob Zhang[zzb205@163.com]
 * 2016年10月12日
 */
@RestController
public class ProductEndPoint {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/api/products/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public ProductVo createProduct(@RequestBody ProductVo productVo,
	        UriComponentsBuilder uriBuilder) {
		Product product = BeanMapper.map(productVo, Product.class);
		productService.saveProduct(product);

		return BeanMapper.map(product, ProductVo.class);
	}

	@RequestMapping(value = "/api/products/{id}", produces = MediaTypes.JSON_UTF_8)
	public ProductVo listOneProduct(@PathVariable("id") Long id) {
		Product product = productService.findOne(id);

		return BeanMapper.map(product, ProductVo.class);
	}

	@RequestMapping(value = "/api/products/{id}/delete")
	public void deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
	}

	////////////////////////////////////////////////////////////////
	@Autowired
	private MaterialService materialService;

	@RequestMapping(value = "/api/materials/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public MaterialVo createMaterial(@RequestBody MaterialVo materialVo,
	        UriComponentsBuilder uriBuilder) {
		Material material = BeanMapper.map(materialVo, Material.class);
		materialService.saveMaterial(material);

		return BeanMapper.map(material, MaterialVo.class);
	}

	@RequestMapping(value = "/api/materials/{id}", produces = MediaTypes.JSON_UTF_8)
	public MaterialVo listOneMaterial(@PathVariable("id") Long id) {
		Material material = materialService.findOne(id);

		return BeanMapper.map(material, MaterialVo.class);
	}

	@RequestMapping(value = "/api/materials/{id}/delete")
	public void deleteMaterial(@PathVariable("id") Long id) {
		materialService.deleteMaterial(id);
	}

	///////////////////////////////////////////////////////////////
	@Autowired
	private DrawingService drawingService;

	@RequestMapping(value = "/api/drawings/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public DrawingVo createDrawing(@RequestBody DrawingVo drawingVo,
	        UriComponentsBuilder uriBuilder) {
		Drawing drawing = BeanMapper.map(drawingVo, Drawing.class);
		drawingService.saveDrawing(drawing);

//		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
//		URI uri = uriBuilder.path("/products/drawingsList").build().toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(uri);
//
//		return new ResponseEntity<DrawingVo>(headers, HttpStatus.CREATED);
		
		return BeanMapper.map(drawing, DrawingVo.class);
	}

	@RequestMapping(value = "/api/drawings/{id}", produces = MediaTypes.JSON_UTF_8)
	public DrawingVo listOneDrawing(@PathVariable("id") Long id) {
		Drawing drawing = drawingService.findOne(id);

		return BeanMapper.map(drawing, DrawingVo.class);
	}

	@RequestMapping(value = "/api/drawings/{id}/delete")
	public void deleteDrawing(@PathVariable("id") Long id) {
		drawingService.deleteDrawing(id);
	}


}
