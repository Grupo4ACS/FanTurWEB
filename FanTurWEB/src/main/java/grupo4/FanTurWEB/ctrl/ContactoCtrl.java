package grupo4.FanTurWEB.ctrl;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;

import grupo4.FanTurWEB.model.Ubicacion;

@Named
@ViewScoped
public class ContactoCtrl extends Ctrl<Ubicacion> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	private void init() {
		modelObj =  new Ubicacion();
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/ubicaciones");
	}
	

}
