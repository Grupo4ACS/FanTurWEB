package grupo4.FanTurWEB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Ubicacion {

	@Id
	@GeneratedValue
	private int id;
	
	private String calle;
	
	@Min(0)
	private int altura;
	
	private String localidad;
	
	private String provincia;
	
	private String pais;
	
	public Ubicacion() {
		super();
	}
	
	public Ubicacion( String calle, int altura, String localidad, String provincia, String pais) {
		super();
		this.calle = calle;
		this.altura = altura;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
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
		Ubicacion other = (Ubicacion) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ubicacion [getId()=" + getId() + ", getCalle()=" + getCalle() + ", getAltura()=" + getAltura()
				+ ", getLocalidad()=" + getLocalidad() + ", getProvincia()=" + getProvincia() + ", getPais()="
				+ getPais() + "]";
	}
	
}
