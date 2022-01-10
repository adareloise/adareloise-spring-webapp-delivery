package cl.lasdelicias.webapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.lasdelicias.webapp.models.dao.IIngredienteDao;
import cl.lasdelicias.webapp.models.entity.Ingrediente;

@Service
public class IngredienteServiceImpl implements IIngredienteService {

	@Autowired
	private IIngredienteDao ingredienteDao;	
	
	@Override
	@Transactional(readOnly = true)
	public List<Ingrediente> findAll() {
		return (List<Ingrediente>) ingredienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Ingrediente> findAll(Pageable pageable) {
		return ingredienteDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Ingrediente ingrediente) {
		ingredienteDao.save(ingrediente);
	}

	@Override
	@Transactional(readOnly = true)
	public Ingrediente findOne(Long id) {
		return ingredienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ingredienteDao.deleteById(id);
	}
}
