package cl.lasdelicias.webapp.models.dao;

import org.springframework.data.repository.CrudRepository;

import cl.lasdelicias.webapp.models.entity.Usuario;


public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}
