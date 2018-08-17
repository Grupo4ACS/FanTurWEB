package grupo4.FanTurWEB.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;

import grupo4.FanTurWEB.controladores.ClienteCont;
import grupo4.FanTurWEB.model.Alojamiento;
import grupo4.FanTurWEB.model.Evento;
import grupo4.FanTurWEB.model.Hotel;
import grupo4.FanTurWEB.model.Paquete;
import grupo4.FanTurWEB.model.dao.interfaces.EventoDao;
import grupo4.FanTurWEB.model.dao.interfaces.HotelDao;

@Named
@ViewScoped
public class PaqueteCtrl extends Ctrl<Paquete> implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(PaqueteCtrl.class.getName());
	
	
	private Alojamiento alojamiento;
	
	private String nombreHotel;
	
	private int evento_id;
	
	private List<Evento> eventos;
	
	private List<Evento> eventosAsignados;
	
	private List<Hotel>hotelesAsignados2;
	private List<Evento> eventosAsignados2;
	
	//private EventoCtrl eventoCtrl;
	
	@EJB
	private EventoDao eventoEJB;
	
	private List<Hotel> hoteles;
	
	private List<Hotel> hotelesAsignados;
	
	@EJB
	private HotelDao hotelEJB;
	
	
	
	public List<Hotel> getHoteles() {
		return hoteles;
	}
	
	public void setHoteles(List<Hotel> hoteles) {
		this.hoteles = hoteles;
	}
	
	
	
	public List<Hotel> getHotelesAsignados() {
		return hotelesAsignados;
	}
	
	public void setHotelesAsignados(List<Hotel> hotelesAsignados) {
		this.hotelesAsignados = hotelesAsignados;
	}
	
	
	
	public List<Evento> getEventosAsignados() {
		return eventosAsignados;
	}

	public void setEventosAsignados(List<Evento> eventosAsignados) {
		this.eventosAsignados = eventosAsignados;
	}
	
	
	
//	public EventoCtrl getEventoCtrl() {
//		return eventoCtrl;
//	}
//
//	public void setEventoCtrl(EventoCtrl eventoCtrl) {
//		this.eventoCtrl = eventoCtrl;
//	}
	
	
	
	public List<Evento> getEventos() {
		return eventos;
	}
	
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	
	
	public int getEvento_id() {
		return evento_id;
	}
	
	public void setEvento_id(int evento_id) {
		this.evento_id = evento_id;
	}
	
	
	
	public String getNombreHotel() {
		return nombreHotel;
	}
	
	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}
	
	
	
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}
	
	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}
	
	
	
	
	@PostConstruct
	private void init() {
		//client = ClientBuilder.newClient();
		//webTarget = client.target("http://localhost:8080/FanTurWEB/rest/paquetes");
		webTarget = webTarget.path("paquetes");
		modelObj =  new Paquete();
		alojamiento = new Alojamiento();
		modelObj.setAlojamiento(alojamiento);
		modelList = new ArrayList<Paquete> (this.getAll());
		eventos = new ArrayList<Evento> (eventoEJB.findAll());
		hoteles = new ArrayList<Hotel> (hotelEJB.findAll());
		hotelesAsignados = new ArrayList<Hotel>();
		eventosAsignados = new ArrayList<Evento>();
	}
	
	
	
	@Override
	public String getId(Paquete paquete) {
		return String.valueOf(paquete.getId());
	}
	
	
	
	@Override
	protected Class<Paquete> getClase() {
		return Paquete.class;
	}
	
	
	
	public String crear() {
		
	    for (ListIterator<Evento> iterator = eventosAsignados.listIterator(); iterator
	            .hasNext();) {
	        Evento event = iterator.next();
	        modelObj.addEvento(event);
	    }
	    
	    for (ListIterator<Hotel> iteratorHotel = hotelesAsignados.listIterator(); iteratorHotel
	            .hasNext();) {
	        Hotel hot = iteratorHotel.next();
	        modelObj.getAlojamiento().addHotel(hot);
	    }
		
		this.create();
		
		eventos = new ArrayList<Evento> (eventoEJB.findAll());
		hoteles = new ArrayList<Hotel> (hotelEJB.findAll());
		hotelesAsignados = new ArrayList<Hotel>();
		eventosAsignados = new ArrayList<Evento>();
		modelObj =  new Paquete();
		alojamiento = new Alojamiento();
		modelObj.setAlojamiento(alojamiento);
		
		return "gestionarPaquetes.xhtml?faces-redirect=true";
	}
	
	
	
	
	public Hotel getHotelByNombre(String nombre) {
		logger.info("Entró al método getHotelByNombre");
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/hoteles");
		invocation = webTarget.path(nombre).request().buildGet();
		logger.info("Seteó el invocation");
		response = invocation.invoke();
		logger.info("Seteó response");
		Hotel telo = response.readEntity(Hotel.class);
		logger.info("Seteó telo");
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/paquetes");
		return telo;
	}
	
	
	
	public Evento getEventoById(int id) {
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/eventos");
		invocation = webTarget.path(String.valueOf(id)).request().buildGet();
		response = invocation.invoke();
		Evento event = response.readEntity(Evento.class);
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/paquetes");
		return event;
			
	}
	
	

	
	
	public void agregarHotel() {
		logger.info("El nombre del telo seleccionado: " + nombreHotel );
		
		Hotel hotel = this.getHotelByNombre(nombreHotel);
		
		logger.info("El hotel que trajo el metodo getHotelByNombre tiene el nombre: " + hotel.getNombre());
		
		hotelesAsignados.add(hotel);
		logger.info("Se ha añadido el hotel");
		
	    for (ListIterator<Hotel> iterator = hoteles.listIterator(); iterator
	            .hasNext();) {
	        int z = iterator.next().getId();
	        if (z == hotel.getId()) {
	            iterator.remove();;
	        }
	    }
		
	}
	
	
	
	public void agregarEvento() {
		logger.info("El id del evento seleccionado: " + String.valueOf(evento_id) );
		Evento evento = this.getEventoById(evento_id);
		logger.info("El evento que trajo el metodo getEventoById tiene la descripcion: " + evento.getDescripcion());
		//modelObj.addEvento(evento);
		eventosAsignados.add(evento);
		logger.info("Se ha añadido el evento");
	    
	    for (ListIterator<Evento> iterator = eventos.listIterator(); iterator
	            .hasNext();) {
	        int z = iterator.next().getId();
	        if (z == evento_id) {
	            iterator.remove();;
	        }
	    }
	}
	
	
	
	
	public String borrar(int id) {
		this.delete(id);
		return "gestionarPaquetes.xhtml?faces-redirect=true";
	}
	
	
	
	
	public void removerEvento(int id) {
	    for (ListIterator<Evento> iterator = eventosAsignados.listIterator(); iterator
	            .hasNext();) {
	    	Evento even = iterator.next();
	        int z = even.getId();
	        if (z == id) {
	            iterator.remove();
	            eventos.add(even);
	        }
	        
	        logger.info("El metodo removerEvento() funcó..");
	        
	    }
	}
	
	
	
	public void removerHotel(int id) {
	    for (ListIterator<Hotel> iterator = hotelesAsignados.listIterator(); iterator
	            .hasNext();) {
	    	Hotel hot = iterator.next();
	        int z = hot.getId();
	        if (z == id) {
	            iterator.remove();
	            hoteles.add(hot);
	        }
	        
	        logger.info("El metodo removerHotel() funcó..");
	        
	    }
	}
	
	public void empezarModificacion(Paquete paquete) {
		modelObj = paquete;
		hotelesAsignados2 = new ArrayList<Hotel>(modelObj.getAlojamiento().getHoteles());
		eventosAsignados2 = new ArrayList<Evento>(modelObj.getEventos());
	}
	
}
