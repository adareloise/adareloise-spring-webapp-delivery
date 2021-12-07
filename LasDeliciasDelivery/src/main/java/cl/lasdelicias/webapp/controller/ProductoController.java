package cl.lasdelicias.webapp.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.lasdelicias.webapp.models.entity.Producto;
import cl.lasdelicias.webapp.models.service.IProductoService;
import cl.lasdelicias.webapp.models.service.IUploadFileService;
import cl.lasdelicias.webapp.util.paginator.PageRender;

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
	
	@GetMapping("/create")
	public String crear(Model model){
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Crear Producto");
		return "form/producto";
	}
	
	@PostMapping("/save")
	public String guardar(@Validated Producto producto, BindingResult result, Model model, @RequestParam("file") MultipartFile foto,
			RedirectAttributes flash, SessionStatus status) {		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "producto de Obra");
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
		
	@RequestMapping(value = "/listar", method=RequestMethod.GET)
	public String galeria(@RequestParam(name="page", defaultValue="0") int page, Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Producto> productos = productoService.findAll(pageRequest);
		
		PageRender<Producto> pageRender =  new PageRender<Producto>("/producto/listar", productos);				
		model.put("titulo", "Productos");
		model.put("productos", productos);
		model.put("page", pageRender);
		
		return "serv/listar_productos";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {		
		if(id > 0) {
			Producto producto = productoService.findOne(id);
			
			productoService.delete(id);
			flash.addFlashAttribute("success", "Producto eliminado con éxito!");

			if (uploadFileService.delete(producto.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + producto.getFoto() + " eliminada con exito!");
			}
		}
		return "redirect:/producto/listar";
	}
	
}
