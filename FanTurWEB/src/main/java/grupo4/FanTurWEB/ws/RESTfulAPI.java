package grupo4.FanTurWEB.ws;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import grupo4.FanTurWEB.model.dao.interfaces.Dao;

/**
 * Toda la funcionalidad CRUD se hace en esta clase
 * @author tuto
 *
 * @param <Model> Clase de Modelo, paquete grupo4.FanTurWEB.model
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public abstract class RESTfulAPI<Model> {

	@Inject
	protected Dao<Model, Integer> dao;
	
	@Context
	protected UriInfo uriInfo;
	
	/**
	 * Obtiene todos los elementos de modelo
	 * @return	respuesta HTTP 200 con una lista con todos los objetos 
	 */
	@GET
	public Response getAll() {
		return Response.ok(dao.findAll()).build();
	}
	
	/**
	 * @param	id del objeto
	 * @return	respuesta HTTP 200 con un elemento
	 */
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") int id) {
		return Response.ok(dao.findById(id)).build();
	}
	
	/**
	 * @param	objeto a persistir
	 * @return	respuesta HTTP 201 
	 */
	@POST
	public Response create(Model modelObject) {
		dao.create(modelObject);
		URI modelObjectUri = uriInfo.getAbsolutePathBuilder().path(this.getModelId(modelObject)).build();
		return Response.created(modelObjectUri).build();
	}
	
	/**
	 * Pequeño yeite para que funcione la construcción de URI a partir del id del objeto de modelo.
	 * Todas las clases herederas la implementan.
	 * @param	objeto de modelo
	 * @return	id del objeto de modelo
	 */
	protected abstract String getModelId(Model modelObject);
	
	/**
	 * Actualiza el objeto buscándolo por su id, tomando los valores de modelObject
	 * 
	 * @param	id del objeto a actualizar
	 * @param	objeto con los valores nuevos
	 * @return	respuesta HTTP 202
	 */
	@Path("{id}")
	@PUT
	public Response update(@PathParam("id") int id, Model modelObject) {
		dao.update(id, modelObject);
		return Response.accepted(modelObject).build();
	}
	
	/**
	 * Elimina un objeto buscándolo por su id
	 * 
	 * @param	id del objeto a eliminar 
	 * @return	respuesta HTTP 204
	 */
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		dao.delete(dao.findById(id));
		return Response.noContent().build();
	}
}
