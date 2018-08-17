package grupo4.FanTurWEB.model.dao.interfaces;

import javax.ejb.Local;

import grupo4.FanTurWEB.model.Hotel;

@Local
public interface HotelDao extends grupo4.FanTurWEB.model.dao.interfaces.Dao<Hotel, Integer> {
	Hotel findByNombre(String nombre);
}
