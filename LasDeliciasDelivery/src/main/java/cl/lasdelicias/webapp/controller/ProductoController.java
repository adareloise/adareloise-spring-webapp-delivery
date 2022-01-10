package cl.lasdelicias.webapp.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.lasdelicias.webapp.models.entity.Bebida;
import cl.lasdelicias.webapp.models.entity.Fondo;
import cl.lasdelicias.webapp.models.entity.Producto;
import cl.lasdelicias.webapp.models.entity.ProductoType;
import cl.lasdelicias.webapp.models.service.IProductoService;
import cl.lasdelicias.webapp.models.service.IUploadFileService;
import cl.lasdelicias.webapp.util.paginator.PageRender;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {
	
	@Autowired 
	private IProductoService productoService;
	
	@Autowired 
	private IUploadFileService uploadFileService;
		
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		
		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/object/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Producto producto = productoService.findOne(id);
		if (producto == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/gallery/listar";
		}

		model.put("producto", producto);
		model.put("titulo", "Detalle del producto: " + producto.getNombre());
		return "object/producto";
	}
	
	@GetMapping("/create/fondo")
	public String crearFondo(Model model){
		Producto producto = new Fondo();
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Crear Fondo");
		return "form/fondo";
	}
	
	@GetMapping("/create/bebida")
	public String crearBebida(Model model){
		Producto producto = new Bebida();
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Crear bebida");
		return "form/bebida";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid Producto producto, BindingResult result, Model model, @RequestParam("file") MultipartFile foto,
			RedirectAttributes flash, SessionStatus status) {		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Producto");
			return "redirect:/producto/listar";
		}
		
		if (!foto.isEmpty()) {

			if (producto.getId() != null && producto.getId() > 0 && producto.getFoto() != null
					&& producto.getFoto().length() > 0) {

				uploadFileService.delete(producto.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			producto.setFoto(uniqueFilename);
		}
		
		String mensajeFlash = (producto.getId() != null) ? "producto editado con éxito!" : "producto creado con éxito!";			
		productoService.save(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/listar";
	}
	
	@PostMapping("/save/fondo")
	public String guardarFondo(@Valid Producto producto,BindingResult result, Model model, @RequestParam("file") MultipartFile foto,
								RedirectAttributes flash, SessionStatus status) {		
						
		if(result.hasErrors()) {
			model.addAttribute("titulo", "producto");
			return "redirect:/producto/listar/fondos";
		}
		
		if (!foto.isEmpty()) {

			if (producto.getId() != null && producto.getId() > 0 && producto.getFoto() != null
					&& producto.getFoto().length() > 0) {

				uploadFileService.delete(producto.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			producto.setFoto(uniqueFilename);
		}
		
		String mensajeFlash = (producto.getId() != null) ? "producto editado con éxito!" : "producto creado con éxito!";			
		producto.setType(ProductoType.FONDO);
		productoService.saveFondo(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/listar/fondos";
	}
	
	@PostMapping("/save/bebida")
	public String guardarBebida(@Valid Producto producto, BindingResult result, Model model, @RequestParam("file") MultipartFile foto,
			RedirectAttributes flash, SessionStatus status) {		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "bebidas");
			return "redirect:/producto/listar/bebidas";
		}
		
		producto.setType(ProductoType.BEBIDA);
		
		if (!foto.isEmpty()) {

			if (producto.getId() != null && producto.getId() > 0 && producto.getFoto() != null
					&& producto.getFoto().length() > 0) {

				uploadFileService.delete(producto.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			producto.setFoto(uniqueFilename);
		}
		
		String mensajeFlash = (producto.getId() != null) ? "producto editado con éxito!" : "producto creado con éxito!";			
		productoService.saveBebida(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/listar/bebidas";
	}
			
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		Producto producto = null;
		ModelAndView mv = new ModelAndView("form/producto");

		if(id > 0) {
			producto = productoService.findOne(id);
		} else {
			flash.addFlashAttribute("error", "El ID de la obra no puede ser cero!");
			return new ModelAndView("redirect:/gallery/listar");
		}
		mv.addObject("producto", producto);
		mv.addObject("titulo", "Editar producto");
		
		return mv;
	}
	
	@GetMapping("/edit/fondo/{id}")
	public ModelAndView editarFondo(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		Producto producto = null;
		ModelAndView mv = new ModelAndView("form/fondo");

		if(id > 0) {
			producto = productoService.findOneFondo(id);
		} else {
			flash.addFlashAttribute("error", "El ID de la obra no puede ser cero!");
			return new ModelAndView("redirect:/gallery/listar");
		}
		mv.addObject("producto", producto);
		mv.addObject("titulo", "Editar producto");
		
		return mv;
	}
	
	@GetMapping("/edit/bebida/{id}")
	public ModelAndView editarBebida(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		Producto producto = null;
		ModelAndView mv = new ModelAndView("form/bebida");

		if(id > 0) {
			producto = productoService.findOneBebida(id);
		} else {
			flash.addFlashAttribute("error", "El ID de la obra no puede ser cero!");
			return new ModelAndView("redirect:/gallery/listar");
		}
		mv.addObject("producto", producto);
		mv.addObject("titulo", "Editar producto");
		
		return mv;
	}
		
	@RequestMapping(value = "/listar", method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Producto> productos = productoService.findAll(pageRequest);
		
		PageRender<Producto> pageRender =  new PageRender<Producto>("/producto/listar", productos);				
		model.put("titulo", "Productos");
		model.put("productos", productos);
		model.put("page", pageRender);
		
		return "serv/listar_productos";
	}
	
	@RequestMapping(value = "/listar/fondos", method=RequestMethod.GET)
	public String listarFondos(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Fondo> fondos = productoService.findByFondo(pageRequest);
		
		PageRender<Fondo> pageRender =  new PageRender<Fondo>("/producto/listar/fondos", fondos);				
		model.put("titulo", "Fondos");
		model.put("fondos", fondos);
		model.put("page", pageRender);
		
		return "serv/listar_fondos";
	}
	
	@RequestMapping(value = "/listar/bebidas", method=RequestMethod.GET)
	public String listarBebidas(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Bebida> bebidas = productoService.findByBebida(pageRequest);
		
		PageRender<Bebida> pageRender =  new PageRender<Bebida>("/producto/listar/bebidas", bebidas);				
		model.put("titulo", "Bebidas Y Jugos");
		model.put("bebidas", bebidas);
		model.put("page", pageRender);
		
		return "serv/listar_bebidas";
	}
			
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {		
		String redirect = null;
		
		if(id > 0) {
			Producto producto = productoService.findOne(id);
			
			if(producto.getType() == ProductoType.FONDO) {
				redirect = "redirect:/producto/listar/fondo";
			}else {
				redirect = "redirect:/producto/listar/bebida";
			}
			
			productoService.delete(id);
			flash.addFlashAttribute("success", "Producto eliminado con éxito!");
			
			if (uploadFileService.delete(producto.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + producto.getFoto() + " eliminada con exito!");
			}
		}
		
		return redirect;
	}
	
}
