package cl.lasdelicias.webapp.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.lasdelicias.webapp.models.entity.Order;

public interface IOrderDao  extends PagingAndSortingRepository<Order, Long>{

}
