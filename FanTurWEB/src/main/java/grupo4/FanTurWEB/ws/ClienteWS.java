package grupo4.FanTurWEB.ws;

import javax.ws.rs.Path;

import grupo4.FanTurWEB.model.Cliente;

@Path("clientes")
public class ClienteWS extends RESTfulAPI<Cliente> {

	@Override
	protected String getModelId(Cliente cliente) {
		return String.valueOf(cliente.getId());
	}

}
