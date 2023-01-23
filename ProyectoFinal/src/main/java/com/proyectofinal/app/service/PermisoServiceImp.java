package com.proyectofinal.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.GrupoDao;
import com.proyectofinal.app.dao.PermisoDao;
import com.proyectofinal.app.dao.RequerimientoDao;
import com.proyectofinal.app.dao.RequerimientoPermisoDao;
import com.proyectofinal.app.dao.RequerimientoPermisoGrupoDao;
import com.proyectofinal.app.dto.GrupoDto;
import com.proyectofinal.app.dto.PermisoDto;
import com.proyectofinal.app.dto.RequerimientoDto;
import com.proyectofinal.app.model.Grupo;
import com.proyectofinal.app.model.Permiso;
import com.proyectofinal.app.model.Permiso.TipoPermiso;
import com.proyectofinal.app.model.Requerimiento;
import com.proyectofinal.app.model.RequerimientoPermisoGrupo;
import com.proyectofinal.app.util.ClassToDto;
import com.proyectofinal.app.model.Grupo.BuilderGrupo;

@Service("permisosService")
@Transactional
public class PermisoServiceImp implements PermisoService {

	@Autowired
	PermisoDao daoPermiso;
	
	@Autowired
	RequerimientoDao daoRequerimiento;
	
	@Autowired
	GrupoDao daoGrupo;
	
	@Autowired
	RequerimientoPermisoDao daoReqPermiso;
	
	@Autowired
	RequerimientoPermisoGrupoDao daoReqPermGrupo;
	
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, 
    		rollbackFor = DataAccessException.class)
    public List<Permiso> findAllPermisos() throws DataAccessException,
    Exception {
    	return this.daoPermiso.findAllPermisos();
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, 
    		rollbackFor = DataAccessException.class)
    public List<Requerimiento> findAllRequerimientosComunes() throws DataAccessException, Exception {	
	    /*Busca requerimientos sin Workflow*/
	    List<Requerimiento> reqComunes = this.daoRequerimiento.findAllByWorkflow(false);  
	    return reqComunes;
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, 
    		rollbackFor = DataAccessException.class)
    public List<Requerimiento> findAllRequerimientosEspeciales() throws DataAccessException, Exception {	    
	    /*Busca requerimientos con Workflow*/
	    List<Requerimiento> reqEspeciales = this.daoRequerimiento.findAllByWorkflow(true);
	    return reqEspeciales;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, 
    		rollbackFor = DataAccessException.class)
    public List<PermisoDto> findAllPermisosDto() throws DataAccessException, Exception {
    		
    	List<Permiso> permisos = this.findAllPermisos();
    	List<PermisoDto> dtos = new ArrayList<PermisoDto>();
    	for (Permiso p : permisos) {
    		dtos.add(ClassToDto.permisoToDto(p));
    	}
    	return dtos;
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, 
    		rollbackFor = DataAccessException.class)
    public GrupoDto createGrupo() throws DataAccessException, Exception {
	
		GrupoDto grupoDto = new GrupoDto();
		
	    // BUSCA REQ SIN WORKFLOW
	    List<Requerimiento> reqComunes = this.findAllRequerimientosComunes();
	    RequerimientoDto r;
	    for (Requerimiento requerimiento : reqComunes) {
	    	r = ClassToDto.requerimientoToDto(requerimiento);
	    	r.setPermiso(0);
		    grupoDto.getrComunes().add(r);
	    }

	    // BUSCA REQ CON WORKFLOW
	    List<Requerimiento> reqEspeciales = this.findAllRequerimientosEspeciales();
	    for (Requerimiento requerimiento : reqEspeciales) {
	    	r = ClassToDto.requerimientoToDto(requerimiento);
	    	r.setPermiso(0);
		    grupoDto.getrEspeciales().add(r);
	    }
	    
		return grupoDto;
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
    		rollbackFor = DataAccessException.class)
    public Boolean saveGrupo(GrupoDto grupoDto) throws DataAccessException, Exception {

    	Grupo validaGrupo = this.daoGrupo.findByName(grupoDto.getDescripcion());
    	if(validaGrupo == null) {
        	Set<RequerimientoPermisoGrupo> permisosGrupo = new HashSet<RequerimientoPermisoGrupo>();

        	permisosGrupo.addAll(this.createPermisosGrupo(permisosGrupo, grupoDto));


        	Grupo grupo = new BuilderGrupo()
    			.hasIdGrupo((Long) this.daoGrupo.findLastId() + 1)
    			.hasDescripcion(grupoDto.getDescripcion())
    			.hasRequerimientoPermisoGrupos(permisosGrupo).build();

        	this.daoGrupo.save(grupo);    		
        	return true;
    	} else {
    		return false;
    	}

    }
    
    @Transactional(readOnly = false, propagation = Propagation.MANDATORY, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Collection<? extends RequerimientoPermisoGrupo> createPermisosGrupo(
	    Set<RequerimientoPermisoGrupo> permisosGrupo, GrupoDto grupoDto) throws DataAccessException, Exception {
	
    	Iterator<RequerimientoDto> iComunesDto = grupoDto.getrComunes().iterator();
	
    	while (iComunesDto.hasNext()) {
    		RequerimientoDto reqDto = iComunesDto.next();
    		RequerimientoPermisoGrupo permGrup = new RequerimientoPermisoGrupo();
    		
    		permGrup.setRequerimientoPermiso(this.daoReqPermiso
    				.findByPermisoAndRequerimiento(reqDto.getPermiso(), reqDto.getIdRequerimiento()));
    		
    		permisosGrupo.add(permGrup);
    	}

    	Iterator<RequerimientoDto> iEspecialesDto = grupoDto.getrEspeciales().iterator();
	
		while (iEspecialesDto.hasNext()) {
			RequerimientoDto reqDto = iEspecialesDto.next();
			if (reqDto.getPermiso() != null) {
				RequerimientoPermisoGrupo permGrup = new RequerimientoPermisoGrupo();
				permGrup.setRequerimientoPermiso(this.daoReqPermiso
						.findByPermisoAndRequerimiento(reqDto.getPermiso(),
								reqDto.getIdRequerimiento()));
				permisosGrupo.add(permGrup);
			} else {
				RequerimientoPermisoGrupo permGrup = new RequerimientoPermisoGrupo();
				permGrup.setRequerimientoPermiso(this.daoReqPermiso
						.findByPermisoAndRequerimiento(TipoPermiso.SA.getIdPermiso(), reqDto.getIdRequerimiento()));
				permisosGrupo.add(permGrup);
			}
	}
	
	return permisosGrupo;
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public List<Grupo> findAllGrupos() throws DataAccessException, Exception {
    	return this.daoGrupo.findAll();
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public List<GrupoDto> findAllGruposDto() throws DataAccessException,
    Exception {
    	List<Grupo> grupos = this.findAllGrupos();
    	List<GrupoDto> dtos = new ArrayList<GrupoDto>();
    	for (Grupo g : grupos) {
    		dtos.add(ClassToDto.grupoToDto(g));
    	}
    	return dtos;
    }
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public GrupoDto viewGrupo(Long idGrupo) throws DataAccessException,
    Exception {
	
    	Grupo grupo = this.daoGrupo.findFullById(idGrupo);
    	GrupoDto grupoDto = new GrupoDto(grupo.getIdGrupo(), grupo.getDescripcion());
    	
    	Iterator<RequerimientoPermisoGrupo> iReqsGrupo = grupo.getRequerimientoPermisoGrupos().iterator();
    	
    	while (iReqsGrupo.hasNext()) {
    	    RequerimientoPermisoGrupo reqGrupo = iReqsGrupo.next();
    	    RequerimientoDto reqDto = ClassToDto.requerimientoToDto(reqGrupo);	    
    	    if (reqDto.getWorkflow() == true) {
    			grupoDto.getrEspeciales().add(reqDto);
    		} else {
    			grupoDto.getrComunes().add(reqDto);
    		}    	    
    	}
    	
    	return grupoDto;
    }   
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean updateGrupo(GrupoDto grupoDto) throws DataAccessException,
    Exception {

    	Grupo validaGrupo = this.daoGrupo.findByName(grupoDto.getDescripcion());
    	if(validaGrupo == null || grupoDto.getDescripcion().equals(validaGrupo.getDescripcion())) {
    		Grupo grupo = this.daoGrupo.findById(grupoDto.getIdGrupo());
    		grupo.setDescripcion(grupoDto.getDescripcion());


    		    Iterator<RequerimientoDto> iComunesDto = grupoDto.getrComunes().iterator();
    		    while (iComunesDto.hasNext()) {
    		    	RequerimientoDto reqDto = iComunesDto.next();
    		    	RequerimientoPermisoGrupo permGrupo = this.daoReqPermGrupo.findById(reqDto.getIdRequerimientoPermisoGrupo());
    		    	permGrupo.setRequerimientoPermiso(this.daoReqPermiso.findByPermisoAndRequerimiento(reqDto.getPermiso(),
    					reqDto.getIdRequerimiento()));
    		    	grupo.getRequerimientoPermisoGrupos().add(permGrupo);
    		    }

    		    Iterator<RequerimientoDto> iEspecialesDto = grupoDto.getrEspeciales().iterator();
    		    
    		    while (iEspecialesDto.hasNext()) {
    			RequerimientoDto reqDto = iEspecialesDto.next();
    			RequerimientoPermisoGrupo permGrupo = this.daoReqPermGrupo.findById(reqDto.getIdRequerimientoPermisoGrupo());
    			if (reqDto.getPermiso() != null) {
    			    permGrupo.setRequerimientoPermiso(this.daoReqPermiso
    				    .findByPermisoAndRequerimiento(reqDto.getPermiso(),
    					    reqDto.getIdRequerimiento()));
    			} else {
    			    permGrupo.setRequerimientoPermiso(this.daoReqPermiso
    				    .findByPermisoAndRequerimiento(
    					    TipoPermiso.SA.getIdPermiso(),
    					    reqDto.getIdRequerimiento()));
    			}
    			
    	    	grupo.getRequerimientoPermisoGrupos().add(permGrupo);
    		    }

    		    this.daoGrupo.update(grupo);
    		
    		    return true;
    	} else {   		
    			return false;
    	}

    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean deleteGrupo(Long idGrupo) throws DataAccessException,
    Exception {
    	
    	if (this.daoGrupo.findById(idGrupo).getUsuarios().isEmpty()) {
    		this.daoGrupo.delete(idGrupo);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Grupo findById(Long id) throws DataAccessException, Exception {
		return this.daoGrupo.findById(id);
	}


}
