package grupo4.FanTurWEB.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;

import grupo4.FanTurWEB.model.Admin;
import grupo4.FanTurWEB.model.Cliente;
import grupo4.FanTurWEB.model.Contacto;
import grupo4.FanTurWEB.model.Paquete;
import grupo4.FanTurWEB.model.Reserva;

@Named
@ViewScoped
public class ClienteCtrl extends Ctrl<Cliente> implements Serializable{

	private static final long serialVersionUID = 1L;
	private Contacto contacto;
	
		@Override
		protected Class<Cliente> getClase() {
			return Cliente.class;
			}
		
		@PostConstruct
		private void init() {
			afterCreate = "../index.xhtml?face-redirect=true";
			afterUpdate = "../login.xhtml?faces-redirect=true";		
			modelObj = new Cliente();
			client = ClientBuilder.newClient();
			webTarget = client.target("http://localhost:8080/FanTurWEB/rest/clientes");
			modelObj.setRol("USER");
			contacto = new Contacto();
			modelObj.setContacto(contacto);			
		}

	

		@Override
		public String getId(Cliente cliente) {
			return String.valueOf(cliente.getId());
		}
	

		public Contacto getContacto() {
			return contacto;
		}

		public void setContacto(Contacto contacto) {
			this.contacto = contacto;
		}		
		
		public Cliente getByUser(String user) {
			invocation = webTarget.path(user).request().buildGet();
			response = invocation.invoke();
			Cliente cli = response.readEntity(Cliente.class);		
			return cli;
				
		}
		
	
			
		
		
		
		
		
		
		
}
