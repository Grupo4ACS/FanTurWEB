package grupo4.FanTurWEB.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
public class Paquete {

	@Id
	@GeneratedValue
	private int id;
	
	private double precio;
	
	private int cantidad;
	
	@OneToMany(mappedBy="paquete",fetch  =FetchType.EAGER)
	private Set<Pasaje> pasajes;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ALOJAMIENTO_ID")
	private Alojamiento alojamiento;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="Paquete_Evento",
	 joinColumns=@JoinColumn(name="ID_PAQUETE"),
	 inverseJoinColumns=@JoinColumn(name="ID_EVENTO"))
	private Set<Evento> eventos;
	
	@OneToOne
	@JoinColumn
	private Admin creadoPor;
	
	private String autorizado;
	
	public String getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	public void setCreadoPor(Admin creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Paquete() {
		super();
	}

	public Paquete(int id, double precio, int cantidad, Set<Pasaje> pasajes, Alojamiento alojamiento,
			Set<Evento> eventos, Admin admin) {
		super();
		this.id = id;
		this.precio = precio;
		this.cantidad = cantidad;
		this.pasajes = pasajes;
		this.alojamiento = alojamiento;
		this.eventos = eventos;
		this.creadoPor = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Set<Pasaje> getPasajes() {
		return pasajes;
	}

	public void setPasajes(Set<Pasaje> pasajes) {
		this.pasajes = pasajes;
	}
	
	public void addPasaje(Pasaje pasaje) {
		if (this.pasajes == null) {
			this.pasajes = new HashSet<Pasaje>();
		}
		this.pasajes.add(pasaje);
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public void addEvento(Evento evento) {
		if (this.getEventos() == null) {
			this.setEventos(new HashSet<Evento>());
		}
		this.eventos.add(evento);
	}
	
	public Admin getCreadoPor() {
		return this.creadoPor;
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
		Paquete other = (Paquete) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paquete [getId()=" + getId() + ", getPrecio()=" + getPrecio() + ", getCantidad()=" + getCantidad()
				+ ", getPasajes()=" + getPasajes() + ", getAlojamiento()=" + getAlojamiento() + ", getEvento()="
				+ getEventos() + ", getCreadoPor()=" + getCreadoPor() + "]";
	}

}
