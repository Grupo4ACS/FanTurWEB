package grupo4.FanTurWEB.model.dao;

import javax.ejb.Stateless;
import grupo4.FanTurWEB.model.Evento;

@Stateless
public class EventoDao extends AbstractDao<Evento, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.EventoDao {

	@Override
	protected Class<Evento> getClazz() {
		return Evento.class;
	}
	
	@Override
	public void update(Integer id, Evento nuevo) {
		Evento actual = this.findById(id);
		actual.setDescripcion(nuevo.getDescripcion());
		actual.setFecha(nuevo.getFecha());
		actual.setLugar(nuevo.getLugar());
		actual.setNroEnt(nuevo.getNroEnt());
		actual.setPrecio(nuevo.getPrecio());
		this.update(actual);
	}
	
}
