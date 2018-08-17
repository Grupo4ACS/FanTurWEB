package grupo4.FanTurWEB.ws;

import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Paquete;
import grupo4.FanTurWEB.ws.RESTfulAPI;

@Path("paquetes")
public class PaqueteWS extends RESTfulAPI<Paquete> {

	@Override
	protected String getModelId(Paquete paquete) {
		return String.valueOf(paquete.getId());
	}

}
