package com.proyectofinal.app.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Grupo;

@Repository("grupoDao")
public class GrupoDaoImp extends AbstractDao implements GrupoDao {

    public Long save(Grupo grupo) throws DataAccessException, Exception {
	return this.saveObject(grupo);
    }

    public Grupo findById(Long idGrupo) throws DataAccessException, Exception {
	return (Grupo) this.findObjectById(Grupo.class, idGrupo);
    }

    @SuppressWarnings("unchecked")
    public List<Grupo> findAll() throws DataAccessException, Exception {
	return this.findAllObject(Grupo.class);
    }

    public Object findLastId() throws DataAccessException, Exception {
	Query query = this.getSession().createQuery(
		"" + "select max(g.idGrupo) " + "from Grupo g ");
	return query.uniqueResult();
    }

    public void update(Grupo grupo) throws DataAccessException, Exception {
	this.updateObject(grupo);
    }

    public Boolean existPermiso(Long idGrupo, Long idRequerimiento,
	    Integer idPermiso) throws DataAccessException, Exception {
	Query query = this.getSession()
		.createQuery(
			"" + "select max(g.idGrupo) " + "from Grupo g "
				+ "inner join g.requerimientoPermisoGrupos rg "
				+ "inner join rg.requerimientoPermiso rp "
				+ "where rp.requerimiento = :idRequerimiento "
				+ "and rp.permiso = :idPermiso "
				+ "and g.idGrupo = :idGrupo ")
		.setParameter("idGrupo", idGrupo)
		.setParameter("idRequerimiento", idRequerimiento)
		.setParameter("idPermiso", idPermiso);

	if (query.uniqueResult() != null) {
	    return true;
	} else {
	    return false;
	}
    }

    public void delete(Long idGrupo) throws DataAccessException, Exception {
	Query query = this.getSession().createQuery(
		"" + "delete from Grupo where idGrupo = :idGrupo ")
		.setParameter("idGrupo", idGrupo);
	query.executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
	public Grupo findByName(String descripcion) throws DataAccessException,
			Exception {
    	List<Grupo> grupos = this.getSession().createCriteria(Grupo.class)
    			.add(Restrictions.like("descripcion", descripcion)).list();
    	if (grupos.size() > 0) {
    	    return grupos.get(0);
    	} else {
    	    return null;
    	}
	}
    
    public Grupo findFullById(Long idGrupo) throws DataAccessException,
    Exception {
	Query query = this.getSession().createQuery(
		"" + "select g " + "from Grupo g "
			+ "inner join g.requerimientoPermisoGrupos rg "
			+ "inner join rg.requerimientoPermiso rp "
			+ "inner join rp.requerimiento r "
			+ "where g.idGrupo = :idGrupo")
		.setParameter("idGrupo", idGrupo);

	return (Grupo) query.uniqueResult();
    }
}

