package grupo4.FanTurWEB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Contacto {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String email;
	
	private String website;
	
	@NotNull
	@Min(000000)
	@Digits(integer=10, fraction=0)
	private Integer telefono;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Integer getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public int getId() {
		return id;
	}
	
	public Contacto() {
		super();
	}
	
	public Contacto(String email, int telefono, String website) {
		this.setEmail(email);
		this.setTelefono(telefono);
		this.setWebsite(website);
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
		Contacto other = (Contacto) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("E-mail: ");
		builder.append(getEmail());
		builder.append(" Página: ");
		builder.append(getWebsite());
		return builder.toString();
	}
	
	
}