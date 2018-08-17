package grupo4.FanTurWEB.ws;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import grupo4.FanTurWEB.model.Admin;
import grupo4.FanTurWEB.model.dao.interfaces.AdminDao;

@Path("admin")
public class AdminWS extends RESTfulAPI<Admin>{

	@Inject
	private AdminDao dao;
	
	@Override
	protected String getModelId(Admin admin) {
		return String.valueOf(admin.getId());
	}
	
	@GET
	@Path("{user}")
	public Response getUser(@PathParam(value = "user") String user) {
		return Response.ok(dao.findByUser(user)).build();
	}

}