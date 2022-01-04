package cl.lasdelicias.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cl.lasdelicias.webapp.models.dao.IProductoDao;
import cl.lasdelicias.webapp.models.repository.IBebidaRepository;
import cl.lasdelicias.webapp.models.repository.IFondoRepository;

@SpringBootApplication
public class LasDeliciasDeliveryApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(LasDeliciasDeliveryApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for(int i=0; i<2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}
}
