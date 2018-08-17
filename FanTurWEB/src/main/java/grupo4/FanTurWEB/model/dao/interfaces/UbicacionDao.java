package grupo4.FanTurWEB.model.dao.interfaces;

import javax.ejb.Local;

import grupo4.FanTurWEB.model.Ubicacion;

@Local
public interface UbicacionDao extends Dao<Ubicacion, Integer>{

	void create(Ubicacion ubicacion);
	void update(Integer id, Ubicacion ubicacion);
	void delete(Ubicacion ubicacion);
}
