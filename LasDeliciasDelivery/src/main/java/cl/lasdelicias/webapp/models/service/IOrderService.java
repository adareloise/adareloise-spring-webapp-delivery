package cl.lasdelicias.webapp.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lasdelicias.webapp.models.entity.Order;

public interface IOrderService {
	
	public List<Order> findAll();
	
	public Page<Order> findAll(Pageable pageable);

	public void save(Order order);
	
	public Order findOne(Long id);
		
	public void delete(Long id);

}
