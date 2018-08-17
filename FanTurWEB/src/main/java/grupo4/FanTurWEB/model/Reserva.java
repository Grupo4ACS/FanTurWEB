package grupo4.FanTurWEB.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

public class Reserva {
	
	@Id 
	@GeneratedValue
	private int id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name= "idCliente")
	@JsonBackReference
	private Cliente cliente;
	
	private Date fechaReserva, fechaPago;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PAQUETE_FK")
	private Paquete paquete;
	
	public static Reserva createReserva(Paquete paquete, Cliente cliente) {
		if (paquete.getCantidad() > 0 ) {
			paquete.setCantidad(paquete.getCantidad()-1);
			return new Reserva(paquete, cliente);
		} else {
			return null;
		}
	}
	
	public Reserva(){
		super();
	}

	public Reserva(Paquete paquete, Cliente cliente) {
		this.cliente = cliente;
		this.paquete = paquete;
	}

	public int getId() {
		return id;
	}

	public void setId(int idReserva) {
		this.id = idReserva;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reserva [getId()=" + getId() + ", getCliente()=" + getCliente() + ", getFechaReserva()="
				+ getFechaReserva() + ", getFechaPago()=" + getFechaPago() + ", getPaquete()=" + getPaquete() + "]";
	}
	
}
