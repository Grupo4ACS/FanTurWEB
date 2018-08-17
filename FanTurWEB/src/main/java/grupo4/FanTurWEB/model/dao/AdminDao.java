package grupo4.FanTurWEB.model.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import grupo4.FanTurWEB.model.Admin;

import grupo4.FanTurWEB.model.Admin_;
//import grupo4.FanTurWEB.model.Cliente;
//import grupo4.FanTurWEB.model.Cliente_;
import grupo4.FanTurWEB.model.Cliente;
import grupo4.FanTurWEB.model.Cliente_;

@Stateless
public class AdminDao extends AbstractDao<Admin, Integer>
	implements grupo4.FanTurWEB.model.dao.interfaces.AdminDao {

	@Override
	public void update(Integer id, Admin nuevo) {
		Admin actual = this.findById(id);
		actual.setApellido(nuevo.getApellido());
		actual.setNombre(nuevo.getNombre());
		this.update(actual);
	}
	
	@Override
	public List<Admin> findByNombreApellido(String nombre, String apellido) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		Root<Admin> a = cq.from(Admin.class);
		cq.select(a);
		Predicate condicion = cb.or(
				cb.like(a.get(Admin_.nombre), nombre),
				cb.like(a.get(Admin_.apellido), apellido));
		cq.where(condicion);
		TypedQuery<Admin> tq = em.createQuery(cq);	
		return tq.getResultList();
	}

	@Override
	public List<Admin> findByRegistradoPor(Admin admin) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		Root<Admin> a = cq.from(Admin.class);
		cq.select(a).where(cb.equal(a.get("registradoPor"), admin));
		TypedQuery<Admin> tq = em.createQuery(cq);
		return tq.getResultList();
	}

	@Override
	protected Class<Admin> getClazz() {
		return Admin.class;
	}
	
	public Admin findByUser(String user) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		Root<Admin> c = cq.from(Admin.class);
		cq.select(c).where(
				cb.like(c.get(Admin_.user), user
				));
		TypedQuery<Admin> tq = em.createQuery(cq);
		Admin admin = tq.getSingleResult();
		return admin;
		
	}
	
	
	public List<Admin> findByUserLista(String user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		Root<Admin> c = cq.from(Admin.class);
		cq.select(c).where(
				cb.like(c.get(Cliente_.user), user
				));
		TypedQuery<Admin> tq = em.createQuery(cq);
		return tq.getResultList();
	}
	
	public List<Admin> findAllLista() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		Root<Admin> a = cq.from(Admin.class);
		cq.select(a);
		TypedQuery<Admin> tq = em.createQuery(cq);
		List<Admin> aux1 = new ArrayList<Admin>();
		aux1 = tq.getResultList();
		return aux1;
	}

}
