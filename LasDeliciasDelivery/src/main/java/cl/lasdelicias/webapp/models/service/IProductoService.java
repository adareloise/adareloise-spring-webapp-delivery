package cl.lasdelicias.webapp.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lasdelicias.webapp.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Page<Producto> findAll(Pageable peageble);

	public void save(Producto obra);
	
	public Producto findOne(Long id);
	
	public void delete(Long id);
	
}
