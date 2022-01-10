package cl.lasdelicias.webapp.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

@Entity(name = "Bebida")
@Table(name = "bebidas")
public class Bebida extends Producto{
	
	private String sabor;
	private int cc;
	
	public Bebida() {}
	
	public Bebida(Long id, @NotEmpty String nombre, @NotEmpty String categoria, ProductoType type,
			@Range Integer precio, String disponibilidad, Date createAt, String foto, int cc, String sabor) {
		super(id, nombre, categoria, type, precio, disponibilidad, createAt, foto);
		
		this.sabor = sabor;
		this.cc = cc;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}
}
