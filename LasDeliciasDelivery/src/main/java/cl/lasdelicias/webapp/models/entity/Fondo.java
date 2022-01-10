package cl.lasdelicias.webapp.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

@Entity(name = "Fondo")
@Table(name = "fondos")
public class Fondo extends Producto {
	
	private String descripcion;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ingrediente_id")
	private List<Ingrediente> ingredientes;

	public Fondo() {}
	
	public Fondo(Long id, @NotEmpty String nombre, @NotEmpty String categoria, ProductoType type,
			@Range Integer precio, String disponibilidad, Date createAt, String foto, String descripcion) {
		
		super(id, nombre, categoria, type, precio, disponibilidad, 
				createAt, foto);
		
		this.descripcion = descripcion;
		ingredientes = new ArrayList<Ingrediente>();
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		ingredientes.add(ingrediente);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
