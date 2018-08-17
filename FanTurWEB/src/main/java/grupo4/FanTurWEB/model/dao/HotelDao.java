package grupo4.FanTurWEB.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo4.FanTurWEB.model.Hotel;
import grupo4.FanTurWEB.model.Hotel_;

@Stateless
public class HotelDao extends AbstractDao<Hotel, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.HotelDao {

	@Override
	protected Class<Hotel> getClazz() {
		return Hotel.class;
	}
	
	@Override
	public void update(Integer id, Hotel nuevo) {
		Hotel actual = this.findById(id);
		
		actual.setContacto(nuevo.getContacto());
		actual.setNombre(nuevo.getNombre());
		actual.setUbicacion(nuevo.getUbicacion());
		this.update(actual);
	}
	
	public Hotel findByNombre(String nombre) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Hotel> cq = cb.createQuery(Hotel.class);
		Root<Hotel> c = cq.from(Hotel.class);
		cq.select(c).where(
				cb.like(c.get(Hotel_.nombre), nombre
				));
		TypedQuery<Hotel> tq = em.createQuery(cq);
		return tq.getSingleResult();
	}

	
	
	
}
