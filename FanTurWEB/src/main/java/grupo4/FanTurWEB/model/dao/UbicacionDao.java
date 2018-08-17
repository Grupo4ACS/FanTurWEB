package grupo4.FanTurWEB.model.dao;

import javax.ejb.Stateless;
import grupo4.FanTurWEB.model.Ubicacion;

@Stateless
public class UbicacionDao extends AbstractDao<Ubicacion, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.UbicacionDao {
	
	@Override
	protected Class<Ubicacion> getClazz() {
		return Ubicacion.class;
	}

	@Override
	public void update(Integer id, Ubicacion nueva) {
		Ubicacion actual = this.findById(id);
		actual.setCalle(nueva.getCalle());
		actual.setAltura(nueva.getAltura());
		actual.setLocalidad(nueva.getLocalidad());
		actual.setProvincia(nueva.getProvincia());
		actual.setPais(nueva.getPais());
		this.update(actual);
	}

}
