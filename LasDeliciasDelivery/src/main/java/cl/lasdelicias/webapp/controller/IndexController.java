package cl.lasdelicias.webapp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"/index", "/", "/home"})
	public String index(){
		return "view/index";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/service")
	public String indexServ(Model model){
		return "serv/index";
	} 
}
