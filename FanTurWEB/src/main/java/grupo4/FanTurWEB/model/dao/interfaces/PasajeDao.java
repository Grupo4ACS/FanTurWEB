package grupo4.FanTurWEB.model.dao.interfaces;

import javax.ejb.Local;

import grupo4.FanTurWEB.model.Pasaje;

@Local
public interface PasajeDao extends Dao<Pasaje, Integer> {

	void create(Pasaje p);
	void update(Integer id, Pasaje p);
	
}
