package cl.lasdelicias.webapp.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;
	
	private double total;
	
	private int paidStatus;
	    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;   

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
    private List<ItemOrder> orderItems;

    public Order() {
    	this.orderItems = new ArrayList<ItemOrder>();
    }
    
    public Order(Long id, Date createAt, double total, int paidStatus, Cliente cliente, List<ItemOrder> orderItems) {
		super();
		this.id = id;
		this.createAt = createAt;
		this.total = total;
		this.paidStatus = paidStatus;
		this.cliente = cliente;
		this.orderItems = orderItems;
	}

	public void prePersist() {
		createAt = new Date();
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(int paidStatus) {
		this.paidStatus = paidStatus;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemOrder> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<ItemOrder> orderItems) {
		this.orderItems = orderItems;
	}

	private static final long serialVersionUID = 1L;

}
