package com.proyectofinal.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.CategoriaProductoDao;
import com.proyectofinal.app.dao.PrecioDao;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Producto;

@Service("categoriaProductoService")
@Transactional
public class CategoriaProductoServiceImp implements CategoriaProductoService {
	
    @Autowired
    private CategoriaProductoDao daoCategoriaProducto;
    
    @Autowired
    private PrecioDao daoPrecio;
    
    /*
     * Lista todas las categorias 
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
    public List<CategoriaProducto> findAll() throws DataAccessException, Exception {
    	return this.daoCategoriaProducto.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<CategoriaProducto> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.daoCategoriaProducto.findAllWithoutDate();
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<CategoriaProducto> findProductoGroupByCategoria(Long id) throws DataAccessException,
			Exception {
    	/*
    	 * REVISAR - AGREGA CUALQUIER CATEGORIA
    	 */
    	List<CategoriaProducto> categorias =  this.daoCategoriaProducto.findAllWithoutDate();
    	List<CategoriaProducto> categoriasComercio = new ArrayList<CategoriaProducto>();
    	Iterator<Producto> iterator;
    	CategoriaProducto catePro = new CategoriaProducto();
    	Producto p = new Producto();
    	for(CategoriaProducto cp : categorias) {
    			iterator = cp.getProductos().iterator();
    		    while(iterator.hasNext()) {
    		    	   p = iterator.next();
    		    	   /*
    		    	    * A MODIFICAR POR HISTORIAL DE PRECIO.
    		    	    */
    		    	   p.setUltimoPrecio(this.daoPrecio.findPreciosByProducto(p.getIdProducto()).get(0).getValor());
    		           
    		    	   if(p.getActivo() && p.getFechaBaja() == null && p.getComercio().getIdComercio().equals(id)) {  
    		        	   catePro.setDescripcion(cp.getDescripcion());
    		        	   catePro.setIdCategoria(cp.getIdCategoria());
    		        	   catePro.addProducto(p);
    		           }
    		    }
    		    if(catePro.getProductos().size() > 0) {
    		        categoriasComercio.add(catePro);    		    	
    		    }
    		    catePro = new CategoriaProducto();
    	}
    	return categoriasComercio;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public CategoriaProducto findByName(String descripcion) throws DataAccessException,
			Exception {
 	return this.daoCategoriaProducto.findByName(descripcion);
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public CategoriaProducto findById(Long id) throws DataAccessException, Exception {
		return this.daoCategoriaProducto.findById(id);
	}
    
    @Transactional(readOnly = false)
    public boolean validaSaveCategoria(CategoriaProducto categoria)
	    throws DataAccessException, Exception {
	
    	boolean rta = true;
    	CategoriaProducto cat = this.daoCategoriaProducto.findByName(categoria.getDescripcion());  	
    	if(cat == null) {
    		rta = true;
    	} else {
    		rta = false;
    	}
    	return rta;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void saveCategoria(CategoriaProducto categoria) throws DataAccessException, Exception {
 	    	this.daoCategoriaProducto.save(categoria);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean deleteCategoria(Long id) throws DataAccessException, Exception {
    	return this.daoCategoriaProducto.delete(id);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean updateCategoria(CategoriaProducto categoria) throws DataAccessException, Exception {

    	CategoriaProducto cat = this.findByName(categoria.getDescripcion());
    	
    	/* Valida que no haya otra categoria con la misma descripción, y
    	 * que no se haya encontrado la misma categoria que se va a modificar.
    	 */
    	if(cat == null || cat.getFechaBaja() != null) {

    		this.daoCategoriaProducto.update(categoria);
    		return true;
    	} else {
    		return false;
    	}
    }
}
