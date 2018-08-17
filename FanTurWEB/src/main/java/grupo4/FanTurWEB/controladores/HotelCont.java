package grupo4.FanTurWEB.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import grupo4.FanTurWEB.model.Contacto;
import grupo4.FanTurWEB.model.Hotel;
import grupo4.FanTurWEB.model.Ubicacion;
import grupo4.FanTurWEB.model.dao.interfaces.HotelDao;

@Named("hotelCont")
@ViewScoped
public class HotelCont implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@EJB
	private HotelDao hotelEJB;
	private Hotel hotel;
	private Ubicacion ubicacion;
	private Contacto contacto;
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@PostConstruct
	public void init() {
		hotel = new Hotel();
		ubicacion = new Ubicacion();
		contacto = new Contacto();
	}
	
	public String crearHotel() {
		
		hotel.setContacto(contacto);
		hotel.setUbicacion(ubicacion);
		hotelEJB.create(hotel);
		return "indexAdmin.xhtml";
		
	}
	

}
