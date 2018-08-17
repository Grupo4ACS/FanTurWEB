package grupo4.FanTurWEB.ws;

import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Alojamiento;

@Path("alojamientos")
public class AlojamientoWS extends RESTfulAPI<Alojamiento> {

	@Override
	public String getModelId(Alojamiento alojamiento) {
		return String.valueOf(alojamiento.getId());
	}
}
