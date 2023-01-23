package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Usuario;

public interface UsuarioDao {

    Usuario findByUsername(String nombreUsuario) throws DataAccessException, Exception;
    
    Long save(Usuario usuario) throws DataAccessException, Exception;
    
    List<Usuario> findAllWithoutDate() throws DataAccessException, Exception;
    
    Boolean delete(Long id) throws DataAccessException, Exception;
    
    Usuario findById(Long id) throws DataAccessException, Exception;
    
    void update(Usuario usuario) throws DataAccessException, Exception;
}
