package grupo4.FanTurWEB.ws;

import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Reserva;

@Path("reservas")
public class ReservaWS extends RESTfulAPI<Reserva> {

	@Override
	protected String getModelId(Reserva reserva) {
		return String.valueOf(reserva.getId());
	}

}
