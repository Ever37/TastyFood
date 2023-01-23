package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Grupo;

public interface GrupoDao {

    Object findLastId() throws DataAccessException, Exception;

    Long save(Grupo grupo) throws DataAccessException, Exception;

    Grupo findById(Long idGrupo) throws DataAccessException, Exception;
    
    Grupo findFullById(Long idGrupo) throws DataAccessException, Exception;

    List<Grupo> findAll() throws DataAccessException, Exception;

    void update(Grupo grupo) throws DataAccessException, Exception;

    Boolean existPermiso(Long idGrupo, Long idRequerimiento, Integer idPermiso)
	    throws DataAccessException, Exception;

    void delete(Long idGrupo) throws DataAccessException, Exception;
    
	Grupo findByName(String descripcion) throws DataAccessException, Exception;

}
