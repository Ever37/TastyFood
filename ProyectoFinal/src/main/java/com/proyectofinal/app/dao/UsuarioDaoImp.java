package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Usuario;

@Repository("usuarioDao")
public class UsuarioDaoImp extends AbstractDao implements UsuarioDao{

    /**
     * Busca usuario por nombreUsuario.
     */
    public Usuario findByUsername(String nombreUsuario)
	    throws DataAccessException, Exception {
	Query query = this.getSession().createQuery(
		"" + "select u " + "from  Usuario u "
			+ "where nombreUsuario = :nombreUsuario "
			+ "and u.fechaBaja = null").setParameter(
				"nombreUsuario", nombreUsuario);
	return (Usuario) query.uniqueResult();
    }
    
    /**
     * Alta de usuario.
     */
    public Long save(Usuario usuario) throws DataAccessException, Exception {
    	return this.saveObject(usuario);
    }
    
    /**
     * Lista todos los usuarios con fecha de baja.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List findAllWithoutDate() throws DataAccessException, Exception {
	Query query = this.getSession().createQuery(
		"" + "select u " + "from  Usuario u "
			+ "where u.fechaBaja = null "
			+ "order by nombreUsuario ");
	return query.list();
    }
    
    /*
     * Agrega fecha_baja cuando se da de baja un usuario.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	Usuario usuario = (Usuario) this.findObjectById(Usuario.class, id);

    	if (usuario != null && usuario.getFechaBaja() == null) {
    		usuario.setFechaBaja(date);
    		this.updateObject(usuario);
    		return true;
		} else {
			return false;
		}
    }
    
    /**
     * Busca usuario por id.
     */
    public Usuario findById(Long id) throws DataAccessException, Exception {
    	return (Usuario) this.findObjectById(Usuario.class, id);
    }
    
    /**
     * Actualiza los datos de un usuario.
     */
    public void update(Usuario usuario) throws DataAccessException, Exception {    	   	
    	this.updateObject(usuario);
    }
}
