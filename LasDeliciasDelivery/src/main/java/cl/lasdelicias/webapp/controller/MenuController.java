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
import cl.lasdelicias.webapp.models.entity.Bebida;
import cl.lasdelicias.webapp.models.entity.Fondo;
import cl.lasdelicias.webapp.util.paginator.PageRender;


@Controller
@RequestMapping(value ="/menu", method = RequestMethod.GET)
@SessionAttributes("producto")
public class MenuController {

	
	@Autowired 
	private IProductoService productoService;

	@RequestMapping(value = "/tradicional", method=RequestMethod.GET)
	public String tradicional(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Fondo> productos = productoService.findTradicional(pageRequest);
		
		PageRender<Fondo> pageRender =  new PageRender<Fondo>("/menu/tradicional", productos);
		model.put("titulo", "Tradicional");
		model.put("productos", productos);
		model.put("page", pageRender);
		
		return "view/menu";
	}
	
	@RequestMapping(value = "/vegan", method=RequestMethod.GET)
	public String vegan(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Fondo> productos = productoService.findVegan(pageRequest);
		
		PageRender<Fondo> pageRender =  new PageRender<Fondo>("/menu/vegan", productos);
		model.put("titulo", "Vegans");
		model.put("productos", productos);
		model.put("page", pageRender);
		
		return "view/menu";
	}
	
	@RequestMapping(value = "/bebidas", method=RequestMethod.GET)
	public String bebidas(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Bebida> productos = productoService.findByBebida(pageRequest);
		
		PageRender<Bebida> pageRender =  new PageRender<Bebida>("/menu/bebidas", productos);
		model.put("titulo", "Jugos y Bebidas");
		model.put("productos", productos);
		model.put("page", pageRender);
				
		return "view/menu";
	}

}
