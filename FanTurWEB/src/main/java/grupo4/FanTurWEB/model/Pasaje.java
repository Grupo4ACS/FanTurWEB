package grupo4.FanTurWEB.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pasaje {

	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private TipoVehiculo vehiculo;
	
	@OneToOne
	@JoinColumn(name = "idServicio")
	private Clase clase;
	
	private Date ida;
	
	private Date idaLlegada;
	
	private Date vuelta;
	
	private Date vueltaLlegada;
	
	@OneToOne
	private Ubicacion origen, destino;
	
	private int asiento;
	
	private double precio;
	
	@ManyToOne
	@JoinColumn(name = "idPaquete")
	private Paquete paquete;
	
	public Pasaje() {
		super();
	}
	
	public Pasaje(TipoVehiculo vehiculo, Clase clase, Date ida, Date idaLlegada, Date vuelta, Date vueltaLlegada,
			Ubicacion origen, Ubicacion destino, int asiento, double precio, Paquete paquete) {
		this.vehiculo = vehiculo;
		this.clase = clase;
		this.ida = ida;
		this.idaLlegada = idaLlegada;
		this.vuelta = vuelta;
		this.vueltaLlegada = vueltaLlegada;
		this.origen = origen;
		this.destino = destino;
		this.asiento = asiento;
		this.precio = precio;
		this.paquete = paquete;
	}
	
	
	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public int getId() {
		return id;
	}
	
	public TipoVehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(TipoVehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public Clase getServicio() {
		return clase;
	}
	public void setServicio(Clase servicio) {
		this.clase = servicio;
	}
	
	public Date getIda() {
		return ida;
	}
	public void setIda(Date ida) {
		this.ida = ida;
	}
	
	public Date getIdaLlegada() {
		return idaLlegada;
	}
	public void setIdaLlegada(Date idaLlegada) {
		this.idaLlegada = idaLlegada;
	}
	
	public Date getVuelta() {
		return vuelta;
	}
	public void setVuelta(Date vuelta) {
		this.vuelta = vuelta;
	}
	
	public Date getVueltaLlegada() {
		return vueltaLlegada;
	}
	public void setVueltaLlegada(Date vueltaLlegada) {
		this.vueltaLlegada = vueltaLlegada;
	}
	
	public Ubicacion getOrigen() {
		return origen;
	}
	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}
	
	public Ubicacion getDestino() {
		return destino;
	}
	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}
	
	public int getAsiento() {
		return asiento;
	}
	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
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
		Pasaje other = (Pasaje) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pasaje [getId()=" + getId() + ", getVehiculo()=" + getVehiculo() + ", getServicio()=" + getServicio()
				+ ", getIda()=" + getIda() + ", getIdaLlegada()=" + getIdaLlegada() + ", getVuelta()=" + getVuelta()
				+ ", getVueltaLlegada()=" + getVueltaLlegada() + ", getOrigen()=" + getOrigen() + ", getDestino()="
				+ getDestino() + ", getAsiento()=" + getAsiento() + ", getPrecio()=" + getPrecio() + ", getPaquete()="
				+ getPaquete() + "]";
	}
	
}
