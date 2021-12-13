package cl.lasdelicias.webapp.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "fondos")
@DiscriminatorValue("F")
public class Fondo extends Producto {
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "producto_id")
	private List<Ingrediente> ingredientes;

	public Fondo() {
		super();
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

}
