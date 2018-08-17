package grupo4.FanTurWEB.ws;

import javax.inject.Inject;
import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Ubicacion;
import grupo4.FanTurWEB.model.dao.interfaces.UbicacionDao;

@Path("ubicaciones")
public class UbicacionWS extends RESTfulAPI<Ubicacion>{

	@Inject
	UbicacionDao dao;
	
	@Override
	public String getModelId(Ubicacion ubicacion) {
		return String.valueOf(ubicacion.getId());
	}
	
}
