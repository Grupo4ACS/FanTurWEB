package grupo4.FanTurWEB.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@Size(min = 2, max = 50)
	private String nombre;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idContacto")
	private Contacto contacto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UBICACION_FK")
	private Ubicacion ubicacion;
	
//	@ManyToOne(cascade=CascadeType.REMOVE)
//	@JoinColumn(name="idAlojamiento")	
//	private Alojamiento aloj;
	
	public Hotel() {
		super();
	}

	public Hotel(String nombre, Contacto contacto, Alojamiento aloj, Ubicacion ubicacion) {
		super();
		this.nombre = nombre;
		this.contacto = contacto;
		//this.aloj = aloj;
		this.ubicacion = ubicacion;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

//	public Alojamiento getAloj() {
//		return aloj;
//	}
//
//	public void setAloj(Alojamiento aloj) {
//		this.aloj = aloj;
//	}
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
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
		Hotel other = (Hotel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("nombre Hotel: ");
		builder.append(getNombre());
		return builder.toString();
	}	
	
}