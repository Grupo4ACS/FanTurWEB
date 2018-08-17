package grupo4.FanTurWEB.ws;

import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Contacto;

@Path("contactos")
public class ContactoWS extends RESTfulAPI<Contacto> {

	@Override
	protected String getModelId(Contacto contacto) {
		return String.valueOf(contacto.getId());
	}

}
