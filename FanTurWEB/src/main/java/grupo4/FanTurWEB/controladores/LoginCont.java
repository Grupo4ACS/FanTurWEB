package grupo4.FanTurWEB.controladores;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import grupo4.FanTurWEB.model.Admin;
import grupo4.FanTurWEB.model.Cliente;
import grupo4.FanTurWEB.model.Contacto;
import grupo4.FanTurWEB.model.dao.interfaces.AdminDao;
import grupo4.FanTurWEB.model.dao.interfaces.ClienteDao;
import grupo4.FanTurWEB.model.dao.interfaces.ContactoDao;

@Named("loginBean")
@SessionScoped
public class LoginCont implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LoginCont.class);

	@EJB
	private ClienteDao clienteEJB;

	@EJB
	private ContactoDao contactoEJB;

	@EJB
	private AdminDao adminEJB;

	private Cliente cliente;

	private Contacto contacto;

	private Cliente clienteResguardo;

	private Admin adminResguardo;

	private String usuario;

	private String password;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Cliente getClienteResguardo() {
		return clienteResguardo;
	}

	public void setClienteResguardo(Cliente clienteResguardo) {
		this.clienteResguardo = clienteResguardo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin getAdminResguardo() {
		return adminResguardo;
	}

	public void setAdminResguardo(Admin adminResguardo) {
		this.adminResguardo = adminResguardo;
	}

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		contacto = new Contacto();
		clienteResguardo = new Cliente();
		adminResguardo = new Admin();		
	}
	
	
	public String login() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		//AGREGADO
		String redireccion = null;
		
		
		try {
			
			
			//AGREGADO
			
			//SI HAY UNA SESION ABIERTA Y DE ALGUNA MANERA SE PUDO ACCEDER A LA PANTALLA DE LOGIN, PRIMERO CIERRO LA SESION
			if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
				cerrarSesion();
			}
			
			if(clienteEJB.findByUserLista(usuario).isEmpty() != true) {
				clienteResguardo = clienteEJB.findByUserLista(usuario).get(0);
				request.login(usuario, password);				
				redireccion = "user/index.xhtml?faces-redirect=true";
				return redireccion;
			}else {
				if(adminEJB.findByUserLista(usuario).isEmpty() != true) {
					adminResguardo = adminEJB.findByUserLista(usuario).get(0);
					request.login(usuario, password);					
					redireccion = "admin/index.xhtml?faces-redirect=true";
					return redireccion;
				}else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Usuario no Regitrado"));
				}
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña Incorrecta", ""));
		}
		return redireccion;
	}
	
	
	
	
	public String loginJAAS() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		//AGREGADO
		String redireccion = null;
		
		
		try {
			
			
			//AGREGADO
			
			//SI HAY UNA SESION ABIERTA Y DE ALGUNA MANERA SE PUDO ACCEDER A LA PANTALLA DE LOGIN, PRIMERO CIERRO LA SESION
			if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
				cerrarSesion();
			}
			
			if(clienteEJB.findByUserLista(usuario).isEmpty() != true) {
				clienteResguardo = clienteEJB.findByUserLista(usuario).get(0);
				request.login(usuario, password);				
				redireccion = "user/index.xhtml?faces-redirect=true";
				return redireccion;
			}else {
				if(adminEJB.findByUserLista(usuario).isEmpty() != true) {
					adminResguardo = adminEJB.findByUserLista(usuario).get(0);
					request.login(usuario, password);					
					redireccion = "admin/index.xhtml?faces-redirect=true";
					return redireccion;
				}else {
					redireccion = "error-page.xhtml?faces-redirect=true";
					return redireccion;
				}
			}
			
		} catch (Exception e) {
			redireccion = "error-page.xhtml?faces-redirect=true";
			return redireccion;
		}
	}
	
	
	
	
	public void cerrarSesion() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		try {
			if (request != null) {
				request.logout();
			}
			externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			logger.error("Logout error: " + e.getMessage());
		} catch (ServletException e) {
			logger.error("Logout error: " + e.getMessage());
		}
	}
	
	public void modificarAdmin() {
		adminEJB.update(adminResguardo.getId(), adminResguardo);		
		this.cerrarSesion();
	}
	
	public void modificarCliente() {
		clienteEJB.update(clienteResguardo.getId(), clienteResguardo);		
		this.cerrarSesion();
	}
	
	public void eliminarCliente() {
		clienteEJB.delete(clienteResguardo);
		this.cerrarSesion();
	}
	
	public void eliminarAdmin() {
		adminEJB.delete(adminResguardo);
		this.cerrarSesion();
	}

}
