package cl.lasdelicias.webapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cl.lasdelicias.webapp.models.service.IProductoService;
import cl.lasdelicias.webapp.models.entity.Fondo;
import cl.lasdelicias.webapp.util.paginator.PageRender;


@Controller
@RequestMapping(value ="/menu", method = RequestMethod.GET)
@SessionAttributes("producto")
public class MenuController {

	
	@Autowired 
	private IProductoService productoService;
	
	@RequestMapping(value = "/products", method=RequestMethod.GET)
	public String galeria(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Fondo> fondos = productoService.findByFondo(pageRequest);
		
		PageRender<Fondo> pageRender =  new PageRender<Fondo>("/menu/products", fondos);				
		model.put("titulo", "Menú");
		model.put("fondos", fondos);
		model.put("page", pageRender);
		
		return "view/menu";
	}
	
}
