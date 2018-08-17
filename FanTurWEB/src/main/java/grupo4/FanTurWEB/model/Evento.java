package grupo4.FanTurWEB.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Evento {
	
	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	private int id;
	
	private String descripcion;
	private double precio;
	private int nroEnt;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UBICACION_FK")
	private Ubicacion lugar;
	private Date fecha;
	
	public Evento() {
		super();
	}
	
	public Evento(String descripcion, double precio, int nroEnt, Ubicacion lugar, Date fecha) {
		this.descripcion = descripcion;
		this.precio = precio;
		this.nroEnt = nroEnt;
		this.lugar = lugar;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getNroEnt() {
		return nroEnt;
	}

	public void setNroEnt(int nroEnt) {
		this.nroEnt = nroEnt;
	}

	public Ubicacion getLugar() {
		return lugar;
	}

	public void setLugar(Ubicacion lugar) {
		this.lugar = lugar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
		Evento other = (Evento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Evento [getId()=" + getId() + ", getDescripcion()=" + getDescripcion() + ", getPrecio()=" + getPrecio()
				+ ", getNroEnt()=" + getNroEnt() + ", getLugar()=" + getLugar() + ", getFecha()=" + getFecha() + "]";
	}
	
}
