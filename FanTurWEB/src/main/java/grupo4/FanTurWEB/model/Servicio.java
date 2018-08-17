package grupo4.FanTurWEB.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Servicio {

	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private EnumServicio detalle;

	public int getId() {
		return id;
	}

	public EnumServicio getDetalle() {
		return detalle;
	}

	public void setDetalle(EnumServicio detalle) {
		this.detalle = detalle;
	}
	
	public Servicio() {
		super();
	}
	
	public Servicio(EnumServicio detalle) {
		super();
		this.detalle = detalle;
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
		Servicio other = (Servicio) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Servicio [getId()=" + getId() + ", getDetalle()=" + getDetalle() + "]";
	}
	
}
