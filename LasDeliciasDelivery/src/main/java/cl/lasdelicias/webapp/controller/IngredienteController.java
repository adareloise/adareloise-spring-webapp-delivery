package cl.lasdelicias.webapp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/ingrediente")
@SessionAttributes("ingrediente")
public class IngredienteController {
	
}
