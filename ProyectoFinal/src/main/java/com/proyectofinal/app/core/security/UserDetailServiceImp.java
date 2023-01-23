package com.proyectofinal.app.core.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.UsuarioDao;
import com.proyectofinal.app.model.RequerimientoPermisoGrupo;
import com.proyectofinal.app.model.Usuario;

@Service("UserDetailServiceImp")
@Transactional
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioDao daoUsuario;

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public Usuario findByUsername(String username) throws DataAccessException,
    Exception {
	return this.daoUsuario.findByUsername(username);
    }

    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {

	Usuario usuario;
	try {
	    usuario = this.findByUsername(username);

	    if (usuario == null) {
		throw new UsernameNotFoundException(String.format(
			"El usuario \"%s\" no existe", username));
	    }

	    return this.customUserDetailsFromUsuario(usuario);

	} catch (DataAccessException e) {
	    throw e;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;

    }

    /**
     * @param usuario
     * @return
     * @throws Exception 
     * @throws DataAccessException 
     */
    public CustomUserDetails customUserDetailsFromUsuario(Usuario usuario) throws DataAccessException, Exception {
	CustomUserDetails customUserDetails = new CustomUserDetails();

	customUserDetails.setIdUsuario(usuario.getIdUsuario());
	customUserDetails.setNombreUsuario(usuario.getNombreUsuario());
	customUserDetails.setApellidoNombre(String.format("%s, %s",
		usuario.getApellido(), usuario.getNombre()));
	customUserDetails.setContrasena(usuario.getContrasena());
	customUserDetails.setActivo(true);
	//customUserDetails.setActivo(usuario.getActivo());

	List<GrantedAuthority> aux = new ArrayList<GrantedAuthority>();

	for (RequerimientoPermisoGrupo rpg : usuario.getGrupo()
		.getRequerimientoPermisoGrupos()) {

	    GrantedAuthority ga = new CustomGrantedAuthority(rpg
		    .getRequerimientoPermiso().getRol());
	    aux.add(ga);

	}
	customUserDetails.setRoles(aux);
	return customUserDetails;
    }
}
