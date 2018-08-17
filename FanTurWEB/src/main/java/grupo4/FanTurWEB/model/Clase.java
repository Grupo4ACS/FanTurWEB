package grupo4.FanTurWEB.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Clase {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoClase clase;
	
	@OneToMany
	private Set<Servicio> servicios;

	public Clase() {
		super();
	}
	
	public Clase (TipoClase clase) {
		this.setClase(clase);
	}
	
	public int getId() {
		return this.id;
	}
	
	public TipoClase getClase() {
		return clase;
	}

	public void setClase(TipoClase clase) {
		this.clase = clase;
	}
	
	public Set<Servicio> getServicios(){
		return servicios;
	}
	
	
	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}

	public void addServicio(Servicio servicio) {
		if (this.servicios == null) {
			this.servicios = new HashSet<Servicio>();
		}
		this.servicios.add(servicio);
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
		Clase other = (Clase) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clase [getId()=" + getId() + ", getClase()=" + getClase() + ", getServicios()=" + getServicios() + "]";
	}
	
}
