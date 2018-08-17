package grupo4.FanTurWEB.ctrl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;

import grupo4.FanTurWEB.model.Admin;
import grupo4.FanTurWEB.model.Evento;
import grupo4.FanTurWEB.model.Hotel;
import grupo4.FanTurWEB.model.Ubicacion;

@Named
@ViewScoped
public class EventoCtrl extends Ctrl<Evento> implements Serializable{

	private static final long serialVersionUID = 1L;
	//Agrego esa entidad para poder setearle una ubicacion al evento
	private Ubicacion ubicacion;
	
	

	@PostConstruct
	private void init() {
		afterCreate = "gestionarEvento.xhtml?faces-redirect=true";
		afterUpdate = "gestionarEvento.xhtml?faces-redirect=true";
		modelObj =  new Evento();
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/eventos");
		//Inicializo y seteo la ubicacion
		ubicacion = new Ubicacion();
		modelObj.setLugar(ubicacion);
		modelList = new ArrayList<Evento>(this.getAll());
	}	 
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getId(Evento evento) {
		return String.valueOf(evento.getId());
	}

	@Override
	protected Class<Evento> getClase() {
		return Evento.class;
	}
	
	
	
	public Evento getByDescripcion(String descripcion) {
		invocation = webTarget.path(descripcion).request().buildGet();
		response = invocation.invoke();
		Evento event = response.readEntity(Evento.class);		
		return event;
			
	}
	
	
	
	
	
	public String crear() {
		this.create();
		return "admin/gestionarEvento.xhtml?faces-redirect=true";
	}
	
	public String modificar() {
	this.update(id, modelObj);
	return "admin/gestionarEvento.xhtml?faces-redirect=true";
	}
	

     }
