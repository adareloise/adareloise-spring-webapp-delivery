package cl.lasdelicias.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.lasdelicias.webapp.models.service.IClienteService;
import cl.lasdelicias.webapp.view.xml.ClienteList;

@RestController
@RequestMapping(value ="/api", method = RequestMethod.GET)
public class ApiRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping(value = "/cliente/listar")
	public ClienteList listar() {
		return new ClienteList(clienteService.findAll());
	}

}
