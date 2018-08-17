package grupo4.FanTurWEB.ws;

import javax.inject.Inject;
import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Servicio;
import grupo4.FanTurWEB.model.dao.interfaces.ServicioDao;

@Path("servicios")
public class ServicioWS extends RESTfulAPI<Servicio> {

	@Inject
	ServicioDao dao;
	
	@Override
	protected String getModelId(Servicio servicio) {
		return String.valueOf(servicio.getId());
	}

}
