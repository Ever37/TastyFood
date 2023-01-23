package com.proyectofinal.app.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.dto.GrupoDto;
import com.proyectofinal.app.dto.PermisoDto;
import com.proyectofinal.app.model.Grupo;
import com.proyectofinal.app.model.Permiso;
import com.proyectofinal.app.model.Requerimiento;

public interface PermisoService {
	
    GrupoDto createGrupo() throws DataAccessException, Exception;

	List<Requerimiento> findAllRequerimientosComunes() throws DataAccessException, Exception;
	
	List<Requerimiento> findAllRequerimientosEspeciales() throws DataAccessException, Exception;
	
	Boolean saveGrupo(GrupoDto grupoDto) throws DataAccessException, Exception;
    
    List<Permiso> findAllPermisos() throws DataAccessException, Exception;
    
    List<PermisoDto> findAllPermisosDto() throws DataAccessException, Exception;
    
    List<Grupo> findAllGrupos() throws DataAccessException, Exception;
    
    List<GrupoDto> findAllGruposDto() throws DataAccessException, Exception;

    GrupoDto viewGrupo(Long idGrupo) throws DataAccessException, Exception;

    Boolean updateGrupo(GrupoDto grupoDto) throws DataAccessException, Exception;

    Boolean deleteGrupo(Long idGrupo) throws DataAccessException, Exception;
    
    Grupo findById(Long idGrupo) throws DataAccessException, Exception;
    
}
