package cl.lasdelicias.webapp.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "productos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String categoria;
	
	@Enumerated(value = EnumType.STRING)
 	private ProductoType type;
	
	@Range(min=0, max=100000)
	private Integer precio;
	
	private String disponibilidad;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;
	
	private String foto;
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(Long id, @NotEmpty String nombre, @NotEmpty String categoria, ProductoType type,
			@Range Integer precio, String disponibilidad, Date createAt, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.type = type;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
		this.createAt = createAt;
		this.foto = foto;
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ProductoType getType() {
		return type;
	}

	public void setType(ProductoType type) {
		this.type = type;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	private static final long serialVersionUID = 1L;
}
