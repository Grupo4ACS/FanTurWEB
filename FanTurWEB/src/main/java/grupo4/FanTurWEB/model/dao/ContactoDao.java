package grupo4.FanTurWEB.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo4.FanTurWEB.model.Contacto;
import grupo4.FanTurWEB.model.Contacto_;

@Stateless
public class ContactoDao extends AbstractDao<Contacto, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.ContactoDao {

	@Override
	protected Class<Contacto> getClazz() {
		return Contacto.class;
	}
	
	@Override
	public void update(Integer id, Contacto contacto) {
		Contacto actual = this.findById(id);
		actual.setTelefono(contacto.getTelefono());
		actual.setEmail(contacto.getEmail());
		actual.setWebsite(contacto.getWebsite());
		this.update(actual);
	}
	
	
	
	public List<Contacto> findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contacto> cq = cb.createQuery(Contacto.class);
		Root<Contacto> c = cq.from(Contacto.class);
		cq.select(c).where(
				cb.like(c.get(Contacto_.email), email
				));
		TypedQuery<Contacto> tq = em.createQuery(cq);
		return tq.getResultList();
	}
	
	
}
