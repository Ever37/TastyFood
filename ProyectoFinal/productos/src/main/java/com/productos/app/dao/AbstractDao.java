package com.productos.app.dao;



import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {

	private static final String FECHA_BAJA = "fechaBaja";

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * Alta generica.
	 */
	public Long saveObject(Object object) {
		Long id = null;
		id = (Long) this.getSession().save(object);
		return id;
	}

	/**
	 * Lista generica de objetos con fecha de baja.
	 */
	@SuppressWarnings("rawtypes")
	public List findAllObject(Class object) {
		Criteria criteria = this.getSession().createCriteria(object.getName());
		return criteria.list();
	}

	/**
	 * Lista generica de objetos sin fecha de baja.
	 */
	@SuppressWarnings("rawtypes")
	public List findAllObjectWithoutDate(Class object) {
		Criteria criteria = this.getSession().createCriteria(object.getName()).add(Restrictions.isNull(FECHA_BAJA));
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	public List findAllObjectWithoutDateParaList(Class object, String sec) {
		Criteria criteria = this.getSession().createCriteria(object.getName())
				.add(Restrictions.and(Restrictions.isNull(FECHA_BAJA), Restrictions.ne("descripcion", sec)));
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	public List findAllObjectWithoutDateBenef(Class object) {
		Criteria criteria = this.getSession().createCriteria(object.getName())
				.add(Restrictions.and(Restrictions.isNull(FECHA_BAJA), Restrictions.eq("beneficiario", true)));
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	public List findAllObjectWithoutDateProv(Class object) {
		Criteria criteria = this.getSession().createCriteria(object.getName())
				.add(Restrictions.and(Restrictions.isNull(FECHA_BAJA), Restrictions.eq("proveedor", true)));
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	public List findAllDemandaByArticulo(Class object, Long art) {
		Criteria criteria = this.getSession().createCriteria(object.getName()).add(Restrictions.eq("idArticulo", art));
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	public List findAllObjectWithoutDateCli(Class object) {
		Criteria criteria = this.getSession().createCriteria(object.getName())
				.add(Restrictions.and(Restrictions.isNull(FECHA_BAJA), Restrictions.eq("cliente", true)));
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	public List findAllObjectWithoutDateSocio(Class object) {
		Criteria criteria = this.getSession().createCriteria(object.getName())
				.add(Restrictions.and(Restrictions.isNull(FECHA_BAJA), Restrictions.eq("socio", true)));
		return criteria.list();
	}

	/**
	 * Busqueda por id generica.
	 */
	@SuppressWarnings("rawtypes")
	public Object findObjectById(Class object, Long id) {
		return this.getSession().get(object.getName(), id);
	}

	@SuppressWarnings("rawtypes")
	public Object findDemandaSinCerrar(Class object, Long id) {
		Criteria criteria = this.getSession().createCriteria(object.getName())
				.add(Restrictions.and(Restrictions.eq("idArticulo", id), Restrictions.isNull("fechaFin")));

		return criteria.uniqueResult();
	}

	@SuppressWarnings("rawtypes")
	public Object findObjectByNro(Class object, String numero) {
		return this.getSession().get(object.getName(), numero);
	}

	/**
	 * Actualiza los datos generica.
	 */
	public void updateObject(Object object) {
		this.getSession().update(object);
	}

	@SuppressWarnings("rawtypes")
	public Object findObjectByDescripcion(Class object, String sec) {
		Criteria criteria = this.getSession().createCriteria(object.getName()).add(Restrictions.eq("descripcion", sec));
		return criteria.uniqueResult();
	}

	/**
	 * Actualiza o guarda los datos generica.
	 */
	public void saveOrUpdateObject(Object object) {
		this.getSession().saveOrUpdate(object);
	}

	public void deleteObject(Object object) {
		this.getSession().delete(object);
	}

}
