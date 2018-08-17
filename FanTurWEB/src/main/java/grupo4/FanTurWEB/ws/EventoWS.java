package grupo4.FanTurWEB.ws;

import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Evento;
import grupo4.FanTurWEB.ws.RESTfulAPI;

@Path("eventos")
public class EventoWS extends RESTfulAPI<Evento> {

	@Override
	protected String getModelId(Evento evento) {
		return String.valueOf(evento.getId());
	}

}
