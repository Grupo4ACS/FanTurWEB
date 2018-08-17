package grupo4.FanTurWEB.ctrl;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import grupo4.FanTurWEB.model.Cliente;
import grupo4.FanTurWEB.model.Contacto;
import grupo4.FanTurWEB.model.Hotel;
import grupo4.FanTurWEB.model.Paquete;
import grupo4.FanTurWEB.model.Reserva;

@Named
@ViewScoped
public class ReservaCtrl extends Ctrl<Reserva> implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private Paquete paquete;
	
	
	
	
		@Override
		protected Class<Reserva> getClase() {
			return Reserva.class;
			}
		
		@PostConstruct
		private void init() {
			afterCreate = "wishlist.xml?face-redirect=true";
			afterUpdate = "../login.xhtml?faces-redirect=true";		
			modelObj = new Reserva();
			client = ClientBuilder.newClient();
			webTarget = client.target("http://localhost:8080/FanTurWEB/rest/reservas");			
			paquete = new Paquete();
			cliente = new Cliente();
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public Paquete getPaquete() {
			return paquete;
		}

		public void setPaquete(Paquete paquete) {
			this.paquete = paquete;
		}

		@Override
		public String getId(Reserva reserva) {
			return String.valueOf(reserva.getId());
		}
		
		public Cliente getClienteByNombre(String nombre) {
			
			client = ClientBuilder.newClient();
			webTarget = client.target("http://localhost:8080/FanTurWEB/rest/clientes");
			invocation = webTarget.path(nombre).request().buildGet();
			
			response = invocation.invoke();
			
			Cliente cli = response.readEntity(Cliente.class);
			
			webTarget = client.target("http://localhost:8080/FanTurWEB/rest/reservas");
			return cli;
		}
		
			public void actualizarCliente(Cliente cli) {
			
			client = ClientBuilder.newClient();
			webTarget = client.target("http://localhost:8080/FanTurWEB/rest/clientes");
			invocation = webTarget.path(String.valueOf(cli.getId())).request().buildPut(Entity.json(cli));
			
			response = invocation.invoke();			
			webTarget = client.target("http://localhost:8080/FanTurWEB/rest/reservas");
			
		}
			
			public void actualizarPaquete(Paquete paquete) {
				
				client = ClientBuilder.newClient();
				webTarget = client.target("http://localhost:8080/FanTurWEB/rest/paquetes");
				invocation = webTarget.path(String.valueOf(paquete.getId())).request().buildPut(Entity.json(paquete));
				
				response = invocation.invoke();			
				webTarget = client.target("http://localhost:8080/FanTurWEB/rest/reservas");
				
			}
		
		public String crearReserva() {
			String nombre = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			cliente = this.getClienteByNombre(nombre);		
			//cliente.reservar(paquete, modelObj);
			modelObj.setCliente(cliente);
			modelObj.setPaquete(paquete);					
			this.create();			
			this.actualizarCliente(cliente);
			return "afterCreate";
		}
		
		
}
