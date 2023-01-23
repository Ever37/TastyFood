package com.proyectofinal.app.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.dto.UsuarioDto;

public interface UsuarioService {

    boolean validaSaveUsuario (Usuario usuario) throws DataAccessException, Exception;

    void saveUsuario(Usuario usuario) throws DataAccessException, Exception;
    
    List<UsuarioDto> findAllUsuariosWithoutDate() throws DataAccessException, Exception;
    
    UsuarioDto findDtoByUsername(String nombreUsuario) throws DataAccessException, Exception;
    
    Usuario findByUsername(String nombreUsuario) throws DataAccessException, Exception;
    
    Boolean deleteUsuario(Long id) throws DataAccessException, Exception;
    
    UsuarioDto findById(Long id) throws DataAccessException, Exception;
    
    Boolean updateUsuario(UsuarioDto usuarioDto) throws DataAccessException, Exception;
    
    void updateClave(UsuarioDto usuarioDto) throws DataAccessException, Exception;
    
    Usuario findUsuarioById(Long id) throws DataAccessException, Exception;
    
    Boolean validarClaveActual(UsuarioDto usuarioDto, Usuario usu) 
    		throws DataAccessException, Exception;
    
}
