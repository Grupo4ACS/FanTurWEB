package grupo4.FanTurWEB.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import grupo4.FanTurWEB.model.Alojamiento;
import grupo4.FanTurWEB.model.Evento;
import grupo4.FanTurWEB.model.Hotel;
import grupo4.FanTurWEB.model.Paquete;
import grupo4.FanTurWEB.model.dao.interfaces.AlojamientoDao;
import grupo4.FanTurWEB.model.dao.interfaces.EventoDao;
import grupo4.FanTurWEB.model.dao.interfaces.HotelDao;
import grupo4.FanTurWEB.model.dao.interfaces.PaqueteDao;

@Named("paqueteCont")
@ViewScoped
public class PaqueteCont implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private PaqueteDao paqueteEJB;
	@EJB
	private HotelDao hotelEJB;
	@EJB
	private EventoDao eventoEJB;
	@EJB
	private AlojamientoDao alojamientoEJB;
	private int id;
	private List<Paquete> todoslospaquetes;
	
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public int getNoches() {
		return noches;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	private String servicio;
	private int noches;
	private double precio;
	private Paquete paquete;
	private Alojamiento alojamiento;	
	private Set<Evento> eventos;
	private Set<Evento> eventos2;
	private Set<Hotel> hoteles;
	private Set<Hotel> hoteles2;
	private static final Logger logger = Logger.getLogger(ClienteCont.class.getName());
	
	@PostConstruct
	public void init() {
		paquete = new Paquete();
		alojamiento = new Alojamiento();
		paquete.setAlojamiento(alojamiento);
		hoteles = new HashSet<Hotel>();	
		hoteles2 = new HashSet<Hotel>();
		eventos = new HashSet<Evento>();
		eventos2 = new HashSet<Evento>();
		hoteles = hotelEJB.findAll();
		eventos = eventoEJB.findAll();
		todoslospaquetes = new ArrayList<Paquete>(paqueteEJB.findAll());
	}
	
	public Paquete getPaquete() {
		return paquete;
	}

	public List<Paquete> getTodoslospaquetes() {
		return todoslospaquetes;
	}

	public void setTodoslospaquetes(List<Paquete> todoslospaquetes) {
		this.todoslospaquetes = todoslospaquetes;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
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

	public Set<Evento> getEventos2() {
		return eventos2;
	}

	public void setEventos2(Set<Evento> eventos2) {
		this.eventos2 = eventos2;
	}

	public Set<Hotel> getHoteles() {
		return hoteles;
	}

	public void setHoteles(Set<Hotel> hoteles) {
		this.hoteles = hoteles;
	}

	public Set<Hotel> getHoteles2() {
		return hoteles2;
	}

	public void setHoteles2(Set<Hotel> hoteles2) {
		this.hoteles2 = hoteles2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//A partir de aca creo el paquete
	
	public String continuar() {		
		paquete.getAlojamiento().setServicio(servicio);
		paquete.getAlojamiento().setNoches(noches);
		paquete.getAlojamiento().setPrecio(precio);
		paqueteEJB.create(paquete);
		return "AltaPaquete2.xhtml";
	}
	
	public void cargarHotel(Hotel hotelSel) {
		hotelSel.setAloj(paquete.getAlojamiento());
		hotelEJB.update(hotelSel.getId(), hotelSel);
		hoteles2.add(hotelSel);			
		hoteles.remove(hotelSel);
	}
	
	public String continuarCreacion() {		
		
		paquete.getAlojamiento().setHoteles(hoteles2);		
		paqueteEJB.update(paquete.getId(), paquete);
		logger.info("Se seteó el siguiente alojamiento: " + paquete.getAlojamiento() +" y este hotel: " + paquete.getAlojamiento().getHoteles());
			
		return "AltaPaquete3.xhtml";
	}
	
	public void cargarEvento(Evento ev) {
		
		eventos2.add(ev);
		eventos.remove(ev);
	}
	
	public void buscar() {
		paquete = paqueteEJB.findById(id);
	}
	public String crearPaquete() {
		paquete.setEventos(eventos2);
		paqueteEJB.update(paquete.getId(),paquete);
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		todoslospaquetes = new ArrayList<Paquete>(paqueteEJB.findAll());
		return "MostrarHoteles.xhtml";
	}
}
