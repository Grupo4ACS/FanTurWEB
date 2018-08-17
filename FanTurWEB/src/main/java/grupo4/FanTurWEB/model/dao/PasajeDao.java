package grupo4.FanTurWEB.model.dao;

import javax.ejb.Stateless;
import grupo4.FanTurWEB.model.Pasaje;

@Stateless
public class PasajeDao extends AbstractDao<Pasaje, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.PasajeDao {

	@Override
	protected Class<Pasaje> getClazz() {
		return Pasaje.class;
	}

	@Override
	public void update(Integer id, Pasaje nuevo) {
		Pasaje actual = this.findById(id);
		actual.setAsiento(nuevo.getAsiento());
		actual.setDestino(nuevo.getDestino());
		actual.setIda(nuevo.getIda());
		actual.setIdaLlegada(nuevo.getIdaLlegada());
		actual.setVuelta(nuevo.getVuelta());
		actual.setVueltaLlegada(nuevo.getVueltaLlegada());
		actual.setOrigen(nuevo.getOrigen());
		actual.setPaquete(nuevo.getPaquete());
		actual.setPrecio(nuevo.getPrecio());
		actual.setServicio(nuevo.getServicio());
		actual.setVehiculo(nuevo.getVehiculo());
		this.update(actual);
	}
	
}
