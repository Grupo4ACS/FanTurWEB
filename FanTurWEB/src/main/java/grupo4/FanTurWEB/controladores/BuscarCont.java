package grupo4.FanTurWEB.controladores;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import grupo4.FanTurWEB.model.Admin;
import grupo4.FanTurWEB.model.dao.interfaces.AdminDao;

@Named("buscarBean")
@ViewScoped
public class BuscarCont implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AdminDao adminEJB;
	
//	private List<Admin> listaAdmin;
//	
//	
//	
//	public List<Admin> getListaAdmin() {
//		return listaAdmin;
//	}
//	
//	public void setListaAdmin(List<Admin> listaAdmin) {
//		this.listaAdmin = listaAdmin;
//	}
	
	
	private Set<Admin> listaAdmin;
	
	
	public Set<Admin> getListaAdmin() {
		return listaAdmin;
	}

	public void setListaAdmin(Set<Admin> listaAdmin) {
		this.listaAdmin = listaAdmin;
	}




	@PostConstruct
	public void init() {
		listaAdmin = adminEJB.findAll();
	}
	
	
	
}
