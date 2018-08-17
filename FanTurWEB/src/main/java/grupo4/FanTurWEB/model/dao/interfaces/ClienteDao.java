package grupo4.FanTurWEB.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import grupo4.FanTurWEB.model.Cliente;

@Local
public interface ClienteDao extends grupo4.FanTurWEB.model.dao.interfaces.Dao<Cliente, Integer>{
	
	List<Cliente> findByNombreApellido(String nombre, String apellido);
	
	List<Cliente> findByUserLista(String user);
	
	Cliente findByUser(String user);
	
}
