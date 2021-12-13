package cl.lasdelicias.webapp.models.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bebidas")
@DiscriminatorValue("B")
public class Bebida extends Producto{

	private int cc;
	
	public Bebida() {
		super();
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

}
