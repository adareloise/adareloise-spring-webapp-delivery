package cl.lasdelicias.webapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.lasdelicias.webapp.models.entity.Ingrediente;
import cl.lasdelicias.webapp.models.service.IIngredienteService;
import cl.lasdelicias.webapp.util.paginator.PageRender;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/ingredientes")
@SessionAttributes("ingrediente")
public class IngredienteController {

	@Autowired 
	private IIngredienteService ingredienteService;
	
	@GetMapping("/create")
	public String crear(Model model){
		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("titulo", "Crear Ingrediente");
		return "form/ingrediente";
	}
	
	@PostMapping("/save")
	public String guardar(@Validated Ingrediente ingrediente, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Ingrediente");
			return "redirect:/ingredientes/listar";
		}
			
		String mensajeFlash = (ingrediente.getId() != null) ? "Ingrediente editado con éxito!" : "Ingrediente creado con éxito!";			
		ingredienteService.save(ingrediente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/ingredientes/listar";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		Ingrediente ingrediente = null;
		ModelAndView mv = new ModelAndView("form/ingrediente");

		if(id > 0) {
			ingrediente = ingredienteService.findOne(id);
		} else {
			flash.addFlashAttribute("error", "El ID del Ingrediente no puede ser cero!");
			return new ModelAndView("redirect:/ingredientes/listar");
		}
		mv.addObject("ingrediente", ingrediente);
		mv.addObject("titulo", "Editar ingrediente");
		
		return mv;
	}
	
	@RequestMapping(value = "/listar", method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Ingrediente> ingredientes = ingredienteService.findAll(pageRequest);
		
		PageRender<Ingrediente> pageRender =  new PageRender<Ingrediente>("/ingredientes/listar", ingredientes);				
		model.put("titulo", "Ingredientes");
		model.put("ingredientes", ingredientes);
		model.put("page", pageRender);
		
		return "serv/listar_ingredientes";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {		
		if(id > 0) {
						
			ingredienteService.delete(id);
			flash.addFlashAttribute("success", "Ingrediente eliminado con éxito!");

		}
		return "redirect:/ingredientes/listar";
	}
}
