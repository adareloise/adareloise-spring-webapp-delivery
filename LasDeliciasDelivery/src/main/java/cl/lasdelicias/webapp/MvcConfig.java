package cl.lasdelicias.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cl.lasdelicias.webapp.models.entity.Bebida;
import cl.lasdelicias.webapp.models.entity.Fondo;
import cl.lasdelicias.webapp.models.entity.Producto;


@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error/403");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller =  new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(new Class[] {cl.lasdelicias.webapp.view.xml.ClienteList.class});
		return marshaller;
	}
	
	@Bean
	public Producto productoFondo () {
		Producto fondo = new Fondo();	
		return fondo;
	}
	
	@Bean
	public Producto BebidaFondo () {
		Producto bebida = new Bebida();	
		return bebida;
	}
}
