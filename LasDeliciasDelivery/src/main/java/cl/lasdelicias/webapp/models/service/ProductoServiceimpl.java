package cl.lasdelicias.webapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.lasdelicias.webapp.models.dao.IProductoDao;
import cl.lasdelicias.webapp.models.entity.Bebida;
import cl.lasdelicias.webapp.models.entity.Fondo;
import cl.lasdelicias.webapp.models.entity.Producto;
import cl.lasdelicias.webapp.models.repository.IBebidaRepository;
import cl.lasdelicias.webapp.models.repository.IFondoRepository;

@Service
public class ProductoServiceimpl implements IProductoService {
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IFondoRepository fondoRepo;
	
	@Autowired
	private IBebidaRepository bebidaRepo;
		
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable peageble) {
		return productoDao.findAll(peageble);
	}
		
	@Override
	@Transactional
	public void save(Producto producto) {
		productoDao.save(producto);
	}
	
	@Override
	@Transactional
	public void saveFondo(Producto fondo) {
		fondoRepo.save(fondo);
	}
	
	@Override
	@Transactional
	public void saveBebida(Producto bebida) {
		bebidaRepo.save(bebida);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Fondo> findByFondo() {
		return fondoRepo.findByFondo();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Bebida> findByBebida() {
		return bebidaRepo.findByBebida();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Fondo> findAllFondo(Pageable peageble) {
		List<Fondo> fondos = findByFondo();
		Page<Fondo> page = new PageImpl<>(fondos, peageble, 0);
		return page;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Bebida> findAllBebida(Pageable peageble) {
		List<Bebida> bebidas = findByBebida();
		Page<Bebida> page = new PageImpl<>(bebidas, peageble, 0);		
		return page;
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOneFondo(Long id) {
		return fondoRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOneBebida(Long id) {
		return bebidaRepo.findById(id).orElse(null);
	}
	
}
