
package grupo4.FanTurWEB.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import grupo4.FanTurWEB.model.Evento;
import grupo4.FanTurWEB.model.Ubicacion;
import grupo4.FanTurWEB.model.dao.interfaces.EventoDao;

@Named("eventoCont")
@ViewScoped
public class EventoCont implements Serializable{

	private static final long serialVersionUID = 1L;
	@EJB
	private EventoDao eventoEJB;
	private Evento evento;
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	private Ubicacion ubicacion;
	
	@PostConstruct
	public void init() {
		
		evento = new Evento();
		ubicacion = new Ubicacion();
		
	}
	
	public String crearEvento() {
		
		evento.setLugar(ubicacion);
		eventoEJB.create(evento);
		return "succes";
		
	}
	
	
	//FIJATE DESPUES NEGRO PORQUE ME DA ERROR.. PIDE CASTEO
	/*
	public List<Evento> encontrarTodos() {
		return eventoEJB.findAll();
	}
	*/
	
}
