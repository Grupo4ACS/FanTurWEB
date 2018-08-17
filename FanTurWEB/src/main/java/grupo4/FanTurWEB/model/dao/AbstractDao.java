package grupo4.FanTurWEB.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import grupo4.FanTurWEB.model.dao.interfaces.Dao;

@Stateless
public abstract class AbstractDao<T, Id extends Serializable> implements Dao<T, Id>  {
	
	
	@PersistenceContext(unitName = "pu1")
	protected EntityManager em;

	
	@Override
	public void create(T obj) {
		em.persist(obj);
	}

	@Override
	public void update(Id id, T obj) {
		
	}
	
	public void update(T obj) {
		em.merge(obj);
	}

	@Override
	public void delete(T obj) {
		obj = em.merge(obj);
		em.remove(obj);
	}

	@Override
	public T findById(Id id) {
		return em.find(getClazz(), id);
	}

	@Override
	public Set<T> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(this.getClazz());
		Root<T> a = cq.from(this.getClazz());
		cq.select(a);
		TypedQuery<T> tq = em.createQuery(cq);
		List<T> aux1 = new ArrayList<T>();
		aux1 = tq.getResultList();
		Set<T> aux2 = new HashSet<T>(aux1);		
		return aux2;
	}
	
	/* Pequeño yeite para que funcione el criteria query.
	 * Cada DAO lo redefine
	 */
	protected abstract Class<T> getClazz();

}
