package cl.lasdelicias.webapp.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lasdelicias.webapp.models.entity.Bebida;
import cl.lasdelicias.webapp.models.entity.Fondo;
import cl.lasdelicias.webapp.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Page<Producto> findAll(Pageable peageble);

	public void save(Producto producto);

	public void saveFondo(Producto fondo);
	
	public void saveBebida(Producto bebida);
		
	public Producto findOne(Long id);
	
	public Producto findOneFondo(Long id);
	
	public Producto findOneBebida(Long id);
	
	public void delete(Long id);
	
	public Page<Fondo> findByFondo(Pageable peageble);
	
	public Page<Bebida> findByBebida(Pageable peageble);
	
}
