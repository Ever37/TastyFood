package com.proyectofinal.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.ComercioDao;
import com.proyectofinal.app.dao.GrupoDao;
import com.proyectofinal.app.dao.UsuarioDao;
import com.proyectofinal.app.dto.UsuarioDto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Grupo;
import com.proyectofinal.app.util.SingletonConstants;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.util.ClassToDto;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioDao daoUsuario;
    
    @Autowired
    private GrupoDao daoGrupo;
    
    @Autowired
    private ComercioDao daoComercio;
    
	@Autowired
	private PasswordEncoder passwordEncoder;
    
    @Transactional(readOnly = false)
    public boolean validaSaveUsuario(Usuario usuario)
	    throws DataAccessException, Exception {
	
    	boolean rta = true;
    	Usuario usu = this.daoUsuario.findByUsername(usuario.getNombreUsuario());
    	if(usu == null) {
    		rta = true;
    	} else {
    		rta = false;
    	}
    	return rta;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void saveUsuario(Usuario usuario) throws DataAccessException, Exception {
    	Grupo grupo;
    	if(null == usuario.getGrupo())  {
        	grupo = daoGrupo.findByName(SingletonConstants.GRUPO_DEFAULT_CLIENTE);   		
    	} else {
        	grupo = daoGrupo.findById(usuario.getGrupo().getIdGrupo());    		
    	}
    	usuario.setContrasena(this.encodePassword(usuario.getContrasena()));
    	usuario.setGrupo(grupo);    	
    	usuario.setValoracion(0.0);
    	this.daoUsuario.save(usuario);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public List<UsuarioDto> findAllUsuariosWithoutDate() throws DataAccessException, Exception {
    	
    	List<Usuario> usuarios = this.daoUsuario.findAllWithoutDate();
    	List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
    	for (Usuario usuario : usuarios) {
    		usuariosDto.add(ClassToDto.usuarioToDto(usuario));
    	}
    	return usuariosDto;
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public UsuarioDto findDtoByUsername(String nombreUsuario)
	    throws DataAccessException, Exception {
    	
    	Usuario usuario = this.daoUsuario.findByUsername(nombreUsuario);
    	if(usuario != null) {
        	return ClassToDto.usuarioToDto(usuario);   		
    	} else {
    		return null;
    	}

    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Usuario findByUsername(String nombreUsuario)
	    throws DataAccessException, Exception {
    	
    	Usuario usuario = this.daoUsuario.findByUsername(nombreUsuario);
    	if(usuario != null) {
        	return usuario;   		
    	} else {
    		return null;
    	}
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean deleteUsuario(Long id) throws DataAccessException, Exception {
    	Comercio comercio = this.daoComercio.findByUsuario(id);
    	if(comercio == null) {
        	return this.daoUsuario.delete(id);
    	} else {
    		return false;
    	}

    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public UsuarioDto findById(Long id) throws Exception {
    	Usuario usuario = this.daoUsuario.findById(id);
    	return ClassToDto.usuarioToDto(usuario);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean updateUsuario(UsuarioDto usuarioDto) throws DataAccessException, Exception {

    	UsuarioDto dto = this.findDtoByUsername(usuarioDto.getNombreUsuario());
    	
    	/* Valida que no haya otro comercio con el mismo nombre de usuario, y
    	 * que no se haya encontrado el mismo comercio que se va a modificar.
    	 */
    	if(dto == null || dto.getIdUsuario().equals(usuarioDto.getIdUsuario())) {
        	Usuario usuario = this.daoUsuario.findById(usuarioDto.getIdUsuario());
        	usuario.setNombre(usuarioDto.getNombre());
        	usuario.setApellido(usuarioDto.getApellido());
        	usuario.setEmail(usuarioDto.getEmail());
        	usuario.setTelefono(usuarioDto.getTelefono());
        	usuario.setCelular(usuarioDto.getCelular());
        	usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        	usuario.setNotificaciones(usuarioDto.getNotificaciones());
    		this.daoUsuario.update(usuario);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void updateClave(UsuarioDto usuarioDto) throws DataAccessException, Exception {
    	Usuario usuario = this.daoUsuario.findById(usuarioDto.getIdUsuario());
    	usuario.setContrasena(this.encodePassword(usuarioDto.getContrasena()));
    	this.daoUsuario.update(usuario);
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Usuario findUsuarioById(Long id) throws Exception {
    	return this.daoUsuario.findById(id);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
    }

	@Override
	public Boolean validarClaveActual(UsuarioDto usuarioDto, Usuario usuario) 
			throws DataAccessException, Exception {
		
		Boolean rta = false;
		if(this.checkPassword(usuarioDto.getContrasenaAntigua(),usuario.getContrasena())) {			
			rta= true;
		}		
		return rta;
	}
	
	public boolean checkPassword(String password, String hashedPassword) {
	    return passwordEncoder.matches(password, hashedPassword);
	} 
     
}
