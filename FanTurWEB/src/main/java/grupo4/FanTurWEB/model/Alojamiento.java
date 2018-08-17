package grupo4.FanTurWEB.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Alojamiento {
	
	@Id
	@GeneratedValue
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}

	private String servicio;
	private int noches;
	private double precio;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="Alojamiento_Hotel",
	 joinColumns=@JoinColumn(name="ID_ALOJAMIENTO"),
	 inverseJoinColumns=@JoinColumn(name="ID_HOTEL"))
	private Set<Hotel> hoteles;
	
	public Alojamiento() {
		super();
	}

	public Set<Hotel> getHoteles() {
		return hoteles;
	}

	public void setHoteles(Set<Hotel> hoteles) {
		this.hoteles = hoteles;
	}
	
	public void addHotel(Hotel hotel) {
		if (hoteles == null) {
			this.hoteles = new HashSet<Hotel>();
		}
		this.hoteles.add(hotel);
	}

	public Alojamiento(String servicio, int noches, double precio) {
		super();
		this.servicio = servicio;
		this.noches = noches;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public int getNoches() {
		return noches;
	}

	public void setNoches(int noches) {
		this.noches = noches;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
		Alojamiento other = (Alojamiento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Servicios: ");
		builder.append(getServicio());
		builder.append("Noches: ");
		builder.append(getNoches());
		builder.append("Precio: ");
		builder.append(getPrecio());
		builder.append("Hoteles: ");
		builder.append(getHoteles().size());
		return builder.toString();
	}	
	
}
