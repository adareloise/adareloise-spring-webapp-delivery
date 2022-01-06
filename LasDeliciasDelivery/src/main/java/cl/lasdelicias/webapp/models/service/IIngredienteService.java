package cl.lasdelicias.webapp.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lasdelicias.webapp.models.entity.Ingrediente;

public interface IIngredienteService {
	
	public List<Ingrediente> findAll();
	
	public Page<Ingrediente> findAll(Pageable pageable);

	public void save(Ingrediente ingrediente);
	
	public Ingrediente findOne(Long id);
		
	public void delete(Long id);

}
