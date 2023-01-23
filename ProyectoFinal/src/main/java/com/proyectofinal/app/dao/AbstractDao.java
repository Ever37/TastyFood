package com.proyectofinal.app.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public abstract class AbstractDao {

    @Autowired(required=true)
    private SessionFactory sessionFactory;

    protected SessionFactory getSessionFactory() throws Exception {
	return this.sessionFactory;
    }

    protected Session getSession() throws Exception {
	return this.sessionFactory.getCurrentSession();
    }

    /**
     * Alta generica.
     */
    public Long saveObject(Object object) throws DataAccessException, Exception {
    	Long id = null;
    	id = (Long) this.getSession().save(object);
    	return id;
    }

    /**
     * Lista generica de objetos con fecha de baja.
     */
    @SuppressWarnings("rawtypes")
    public List findAllObject(Class object) throws DataAccessException,	Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName());
	return criteria.list();
    }

    /**
     * Lista generica de objetos sin fecha de baja.
     */
    @SuppressWarnings("rawtypes")
    public List findAllObjectWithoutDate(Class object) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.isNull("fechaBaja"));
	return criteria.list();
    }

    @SuppressWarnings("rawtypes")
    public List findAllObjectWithoutDateParaList(Class object, String sec) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.and(Restrictions.isNull("fechaBaja"),Restrictions.ne("descripcion",sec)));
	return criteria.list();
    }
    @SuppressWarnings("rawtypes")
    public List findAllObjectWithoutDateBenef(Class object) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.and(Restrictions.isNull("fechaBaja"),Restrictions.eq("beneficiario",true)));
	return criteria.list();
    }

    @SuppressWarnings("rawtypes")
    public List findAllObjectWithoutDateProv(Class object) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.and(Restrictions.isNull("fechaBaja"),Restrictions.eq("proveedor",true)));
	return criteria.list();
    }
    
    @SuppressWarnings("rawtypes")
    public List findAllDemandaByArticulo(Class object, Long art) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.eq("idArticulo",art));
	return criteria.list();
    }
    
    @SuppressWarnings("rawtypes")
    public List findAllObjectWithoutDateCli(Class object) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.and(Restrictions.isNull("fechaBaja"),Restrictions.eq("cliente",true)));
	return criteria.list();
    }

    
    @SuppressWarnings("rawtypes")
    public List findAllObjectWithoutDateSocio(Class object) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.and(Restrictions.isNull("fechaBaja"),Restrictions.eq("socio",true)));
	return criteria.list();
    }
    /**
     * Busqueda por id generica.
     */
    @SuppressWarnings("rawtypes")
    public Object findObjectById(Class object, Long id)	throws DataAccessException, Exception {
	Object obj = this.getSession().get(object.getName(), id);
	return obj;
    }
    
    @SuppressWarnings("rawtypes")
    public Object findDemandaSinCerrar(Class object, Long id) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.and(Restrictions.eq("idArticulo",id),Restrictions.isNull("fechaFin")));
		
	return criteria.uniqueResult();
    }

    @SuppressWarnings("rawtypes")
    public Object findObjectByNro(Class object, String numero)	throws DataAccessException, Exception {
	Object obj = this.getSession().get(object.getName(), numero);
	return obj;
    }

    /**
     * Actualiza los datos generica.
     */
    public void updateObject(Object object) throws DataAccessException,	Exception {
	this.getSession().update(object);
    }
    
    @SuppressWarnings("rawtypes")
    public Object findObjectByDescripcion(Class object, String sec) throws DataAccessException, Exception {
	Criteria criteria = this.getSession().createCriteria(object.getName()).add(
		Restrictions.eq("descripcion",sec));
	return criteria.uniqueResult();
    }

    /**
     * Actualiza o guarda los datos generica.
     */
    public void saveOrUpdateObject(Object object) throws DataAccessException, Exception {
	this.getSession().saveOrUpdate(object);
    }

    public void deleteObject(Object object) throws DataAccessException, Exception {
	this.getSession().delete(object);
    }
   
}
