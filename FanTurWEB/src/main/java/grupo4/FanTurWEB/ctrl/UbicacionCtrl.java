package grupo4.FanTurWEB.ctrl;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import grupo4.FanTurWEB.model.Ubicacion;

@Named
@ViewScoped
public class UbicacionCtrl extends Ctrl<Ubicacion> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	private void init() {
		modelObj =  new Ubicacion();
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/ubicaciones");
		afterCreate = "pagina adonde ir despues de crear";
		afterUpdate = "pagina adonde ir despues de actualizar";
		afterDelete = "pagina adonde ir despues de borrar";
	}
	
	public void modificar() {
		this.update();
	}
		
}
