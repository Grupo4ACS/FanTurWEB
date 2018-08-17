package grupo4.FanTurWEB.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import grupo4.FanTurWEB.model.Admin;

@Local
public interface AdminDao extends Dao<Admin, Integer> {

	List<Admin> findByNombreApellido(String nombre, String apellido);
	List<Admin> findByRegistradoPor(Admin admin);
	
	Admin findByUser(String user);
	
	List<Admin> findByUserLista(String user);
	
	List<Admin> findAllLista();
	
}
