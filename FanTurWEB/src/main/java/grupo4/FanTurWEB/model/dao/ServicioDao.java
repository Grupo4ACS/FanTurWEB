package grupo4.FanTurWEB.model.dao;

import javax.ejb.Stateless;
import grupo4.FanTurWEB.model.Servicio;

@Stateless
public class ServicioDao extends AbstractDao<Servicio, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.ServicioDao {

	@Override
	protected Class<Servicio> getClazz() {
		return Servicio.class;
	}

	@Override
	public void update(Integer id, Servicio nuevo) {
		Servicio actual = this.findById(id);
		actual.setDetalle(nuevo.getDetalle());
		this.update(actual);
	}
	
}
