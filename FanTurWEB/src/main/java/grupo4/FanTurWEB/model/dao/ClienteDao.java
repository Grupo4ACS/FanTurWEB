package grupo4.FanTurWEB.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo4.FanTurWEB.model.Admin;
import grupo4.FanTurWEB.model.Admin_;
import grupo4.FanTurWEB.model.Cliente;
import grupo4.FanTurWEB.model.Cliente_;

@Stateless
public class ClienteDao extends AbstractDao<Cliente, Integer> implements grupo4.FanTurWEB.model.dao.interfaces.ClienteDao {

	@Override
	public void update(Integer id, Cliente nuevo) {
		Cliente actual = this.findById(id);
		actual.setApellido(nuevo.getApellido());
		actual.setNombre(nuevo.getNombre());
		actual.setContacto(nuevo.getContacto());
		actual.setUser(nuevo.getUser());
		this.update(actual);
	}

	@Override
	public List<Cliente> findByNombreApellido(String nombre, String apellido) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> c = cq.from(Cliente.class);
		cq.select(c).where(cb.or(
				cb.like(c.get(Cliente_.nombre), nombre),
				cb.like(c.get(Cliente_.apellido), apellido)
				));
		TypedQuery<Cliente> tq = em.createQuery(cq);
		return tq.getResultList();
	}
	
	@Override
	protected Class<Cliente> getClazz() {
		return Cliente.class;
	}
	
	
	public List<Cliente> findByUserLista(String user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> c = cq.from(Cliente.class);
		cq.select(c).where(
				cb.like(c.get(Cliente_.user), user
				));
		TypedQuery<Cliente> tq = em.createQuery(cq);
		return tq.getResultList();
	}
	
	
	
	public Cliente findByUser(String user) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> c = cq.from(Cliente.class);
		cq.select(c).where(
				cb.like(c.get(Cliente_.user), user
				));
		TypedQuery<Cliente> tq = em.createQuery(cq);
		Cliente clien = tq.getSingleResult();
		return clien;
		
	}
	
}
