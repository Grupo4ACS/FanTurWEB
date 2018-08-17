package grupo4.FanTurWEB.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public abstract class User {

	@Id
	@GeneratedValue
	protected int id;
	
	private String nombre;
	private String apellido;
	
	@Column(unique=true)
	private String user;
	
	@NotNull @Size(min = 8)
	private String password;
	
	@NotNull
	private String rol;
	
	
	
	public User() {
		super();
	}
	
	
	public User(String nombre, String apellido, String user, String password, String rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.user = user;
		this.password = password;
		this.rol = rol;
	}
	
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
		
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", user=" + user + ", password="
				+ password + ", getId()=" + getId() + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getUser()=" + getUser() + ", getPassword()=" + getPassword() + "]";
	}	
	
}
