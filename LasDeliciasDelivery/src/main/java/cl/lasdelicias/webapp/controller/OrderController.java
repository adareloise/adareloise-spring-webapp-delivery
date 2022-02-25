package cl.lasdelicias.webapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.lasdelicias.webapp.models.entity.Cliente;
import cl.lasdelicias.webapp.models.entity.Order;
import cl.lasdelicias.webapp.models.service.IClienteService;
import cl.lasdelicias.webapp.models.service.IOrderService;

@Controller
@RequestMapping(value ="/order", method = RequestMethod.GET)
@SessionAttributes("order")
public class OrderController {
	
	@Autowired 
	private IOrderService iorder;
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/create/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model,
			RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(clienteId);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:form/registrar";
		}

		Order order = new Order();
		order.setCliente(cliente);

		model.put("order", order);
		model.put("titulo", "Crear Orden");

		return "form/orden";
	}

}
