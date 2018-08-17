package grupo4.FanTurWEB.ctrl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import grupo4.FanTurWEB.model.Admin;
import grupo4.FanTurWEB.model.dao.interfaces.AdminDao;

@Named
@ViewScoped
public class AdminCtrl extends Ctrl<Admin> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@PostConstruct
	private void init() {
		afterCreate = "registrationAdmin.xhtml?faces-redirect=true";
		afterUpdate = "../login.xhtml?faces-redirect=true";
		modelObj = new Admin();
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/admin");
		modelObj.setRol("ADMINISTRATOR");
	}

	@Override
	protected Class<Admin> getClase() {
		return Admin.class;
	}

	@Override
	public String getId(Admin admin) {
		return String.valueOf(admin.getId());
	}

	public Admin getByUser(String user) {
		invocation = webTarget.path(user).request().buildGet();
		response = invocation.invoke();
		Admin admin = response.readEntity(Admin.class);		
		return admin;
			
	}
	
	public String crear() {
		String nombre  = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		Admin adminEnSesion = this.getByUser(nombre);
		modelObj.setRegistradoPor(adminEnSesion);
		return this.create();		
	}
	
	public String modificar() {
		String nombre  = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		Admin adminEnSesion = this.getByUser(nombre);
		return this.update(adminEnSesion.getId(), modelObj);	
	}
		
		
	
}
