package cl.lasdelicias.webapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.lasdelicias.webapp.models.dao.IOrderDao;
import cl.lasdelicias.webapp.models.entity.Order;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao iorder;
	
	@Override
	@Transactional(readOnly = true)
	public List<Order> findAll() {
		return (List<Order>) iorder.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Order> findAll(Pageable pageable) {
			return iorder.findAll(pageable);
	}

	@Override
	@Transactional()
	public void save(Order order) {
		iorder.save(order);
	}

	@Override
	@Transactional(readOnly = true)
	public Order findOne(Long id) {
		return iorder.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		iorder.deleteById(id);
	}

}
