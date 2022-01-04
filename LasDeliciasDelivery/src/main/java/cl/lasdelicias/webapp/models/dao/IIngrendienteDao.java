package cl.lasdelicias.webapp.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.lasdelicias.webapp.models.entity.Ingrediente;

public interface IIngrendienteDao extends PagingAndSortingRepository<Ingrediente, Long> {

}
