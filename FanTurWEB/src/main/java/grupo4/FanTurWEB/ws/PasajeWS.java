package grupo4.FanTurWEB.ws;

import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Pasaje;

@Path("pasajes")
public class PasajeWS extends RESTfulAPI<Pasaje> {

	@Override
	protected String getModelId(Pasaje pasaje) {
		return String.valueOf(pasaje.getId());
	}

}
