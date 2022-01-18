package cl.lasdelicias.webapp.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import cl.lasdelicias.webapp.models.entity.Bebida;
import cl.lasdelicias.webapp.models.entity.Fondo;
import cl.lasdelicias.webapp.models.entity.Producto;
import org.springframework.data.domain.Pageable;


@NoRepositoryBean
public interface IProductoDao extends PagingAndSortingRepository<Producto, Long>{

	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
 	
	public List<Producto> findByNombreLikeIgnoreCase(String term);
	
	@Query("SELECT p FROM Producto p WHERE p.type = 'FONDO' ")
	public Page<Fondo> findByFondo(Pageable pageable);
	
	@Query("SELECT p FROM Producto p WHERE p.type = 'BEBIDA' ")
	public Page<Bebida> findByBebida(Pageable pageable);
	
	@Query("SELECT p FROM Producto p WHERE p.categoria LIKE '%Vegan%' ")
	public Page<Fondo> findVegan(Pageable pageable); 
	
	@Query("SELECT p FROM Producto p WHERE p.categoria NOT LIKE '%Vegan%' AND p.type = 'FONDO' ")
	public Page<Fondo> findTradicional(Pageable pageable); 

	
}
