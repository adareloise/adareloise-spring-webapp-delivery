package cl.lasdelicias.webapp.models.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.lasdelicias.webapp.models.dao.IProductoDao;

@Repository
@Qualifier("BEBIDA")
public interface IBebidaRepository extends IProductoDao{

}
