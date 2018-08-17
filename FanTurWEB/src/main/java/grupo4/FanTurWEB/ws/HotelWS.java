package grupo4.FanTurWEB.ws;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import grupo4.FanTurWEB.model.Hotel;
import grupo4.FanTurWEB.model.dao.interfaces.HotelDao;

@Path("hoteles")
public class HotelWS extends RESTfulAPI<Hotel> {

	@Inject
	private HotelDao dao;
	
	@Override
	protected String getModelId(Hotel hotel) {
		return String.valueOf(hotel.getId());
	}
	
	@GET
	@Path("{nombre}")
	public Response getbyNombre(@PathParam(value = "nombre") String nombre) {
		return Response.ok(dao.findByNombre(nombre)).build();
	}

}
