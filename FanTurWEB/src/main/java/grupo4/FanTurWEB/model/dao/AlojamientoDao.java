package grupo4.FanTurWEB.model.dao;

import javax.ejb.Stateless;
import grupo4.FanTurWEB.model.Alojamiento;

@Stateless
public class AlojamientoDao extends AbstractDao<Alojamiento, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.AlojamientoDao {

	@Override
	protected Class<Alojamiento> getClazz() {
		return Alojamiento.class;
	}
	
	@Override
	public void update(Integer id, Alojamiento nuevo) {
		Alojamiento actual = this.findById(id);
		actual.setNoches(nuevo.getNoches());
		actual.setPrecio(nuevo.getPrecio());
		actual.setServicio(nuevo.getServicio());
		this.update(actual);
	}
}
