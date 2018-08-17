package grupo4.FanTurWEB.ctrl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;


import grupo4.FanTurWEB.model.Clase;
import grupo4.FanTurWEB.model.EnumServicio;

import grupo4.FanTurWEB.model.Pasaje;
import grupo4.FanTurWEB.model.Servicio;
import grupo4.FanTurWEB.model.TipoClase;
import grupo4.FanTurWEB.model.TipoVehiculo;
import grupo4.FanTurWEB.model.Ubicacion;

@Named
@SessionScoped
public class PasajeCtrl extends Ctrl<Pasaje> implements Serializable{

	
	private static final long serialVersionUID = 1L;
	//Atributos agregados
	private Clase clase;
	private TipoVehiculo tipoVehiculo;
	private EnumServicio sv;
	private TipoClase tc;
	private Servicio servicio;
	private Ubicacion ubic;
	private Ubicacion ubic2;
	private Set<Servicio> listaserv;
	private String tipoClase;
	private String ts;
	private String tv;

	private static final Logger logger = Logger.getLogger(PasajeCtrl.class.getName());
	
	
	@PostConstruct
	private void init() {
		modelObj =  new Pasaje();
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/FanTurWEB/rest/pasajes");
		afterCreate = "indexAdmin.xhtml";
		afterUpdate = "indexAdmin.xhtml";
		afterDelete = "indexAdmin.xhtml";
		clase= new Clase();
		ubic = new Ubicacion();
		ubic2 = new Ubicacion();
		listaserv = new HashSet<Servicio>();
		clase.setServicios(listaserv);		
		modelObj.setOrigen(ubic);
		modelObj.setDestino(ubic2);
		modelObj.setClase(clase);
		servicio = new Servicio();			
		
	}
	
	
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}


	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}


	public EnumServicio getSv() {
		return sv;
	}


	public void setSv(EnumServicio sv) {
		this.sv = sv;
	}


	public TipoClase getTc() {
		return tc;
	}


	public void setTc(TipoClase tc) {
		this.tc = tc;
	}


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	public String getTs() {
		return ts;
	}


	public void setTs(String ts) {
		this.ts = ts;
	}


	public Clase getClase() {
		return clase;
	}







	public String getTipoClase() {
		return tipoClase;
	}


	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}


	public void setClase(Clase clase) {
		this.clase = clase;
	}


	public String getTv() {
		return tv;
	}


	public void setTv(String tv) {
		this.tv = tv;
	}





	public Ubicacion getUbic() {
		return ubic;
	}


	public void setUbic(Ubicacion ubic) {
		this.ubic = ubic;
	}


	public Ubicacion getUbic2() {
		return ubic2;
	}


	public void setUbic2(Ubicacion ubic2) {
		this.ubic2 = ubic2;
	}


	public Set<Servicio> getListaserv() {
		return listaserv;
	}


	public void setListaserv(Set<Servicio> listaserv) {
		this.listaserv = listaserv;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String continuar() {
		/*switch(tv) {
		case "auto": tipoVehiculo = TipoVehiculo.Auto;
					 break;
		case "tren": tipoVehiculo = TipoVehiculo.Tren;
					 break;
		case "avion": tipoVehiculo = TipoVehiculo.Avion;
					  break;
		case "colectivo": tipoVehiculo = TipoVehiculo.Colectivo;
						  break;	
		default: break;				  
		}*/
		
		//modelObj.setVehiculo(tipoVehiculo);
		return "CrearPasaje2.xhtml";
	}
	
	public void agregarServicio() {
		//sv.getDetalle().
		
		switch(ts) {
			case "desayuno": sv = EnumServicio.Desayuno;
							 
							 break;
			case "asiento normal": sv = EnumServicio.Asiento_Normal;
			 						
			 						break; 
			case "semicama": sv = EnumServicio.Semicama;
							
							break;
			case "cochecama": sv = EnumServicio.Cochecama;
			 				 
			 				 break;
			case "almuerzo":sv = EnumServicio.Almuerzo;
			 				
			 				break;
			case "cena":sv = EnumServicio.Cena;
						
						break;
			case "azafata": sv = EnumServicio.Azafata;
							
							break;
			case "baño":sv = EnumServicio.Baño;
						
						break;
		}
		servicio.setDetalle(sv);
		modelObj.getClase().addServicio(servicio);
		servicio = new Servicio();
	}
	

	public String crearPasaje() {
		switch(tipoClase) {
		case "turista": tc = TipoClase.turista;
						break;
		case "segunda clase": tc = TipoClase.segundaClase;
							break;
							
		case "primera": tc = TipoClase.primera;
		            	break;
		}
		return this.create();
	}
	
	public String continuar2() {
		logger.info("Se seteó el siguiente vehiculo: " + tv);
		return "CrearPasaje3.xhtml";
	}
	
	
}
