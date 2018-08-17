package grupo4.FanTurWEB.model.dao.interfaces;

import java.util.List;

import grupo4.FanTurWEB.model.Contacto;

public interface ContactoDao extends grupo4.FanTurWEB.model.dao.interfaces.Dao<Contacto, Integer> {
	
	List<Contacto> findByEmail(String email);
	
}
