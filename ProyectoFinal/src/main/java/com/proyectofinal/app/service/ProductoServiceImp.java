package com.proyectofinal.app.service;

import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.CategoriaProductoDao;
import com.proyectofinal.app.dao.PrecioDao;
import com.proyectofinal.app.dao.ProductoDao;
import com.proyectofinal.app.dao.SalsaDao;
import com.proyectofinal.app.dto.ProductoDto;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Precio;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Salsa;
import com.proyectofinal.app.util.ClassToDto;

@Service("productoService")
@Transactional
public class ProductoServiceImp implements ProductoService {

	@Autowired
	ProductoDao daoProducto;
	
	@Autowired
	CategoriaProductoDao daoCategoria;
	
	@Autowired
	PrecioDao daoPrecio;
	
	@Autowired
	SalsaDao daoSalsa;
	
    /*
     * Lista todos los productos 
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
    public List<Producto> findAll() throws DataAccessException, Exception {
    	return this.daoProducto.findAll();
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Producto> findAllWithoutDate() throws DataAccessException,
			Exception {
 	return this.daoProducto.findAllWithoutDate();
	}
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Producto findByName(String descripcion) throws DataAccessException,
			Exception {
	return this.daoProducto.findByName(descripcion);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Producto findByNameAndComercio(String descripcion, Long idComercio) throws DataAccessException,
			Exception {
	return this.daoProducto.findByNameAndComercio(descripcion, idComercio);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Producto findById(Long id) throws DataAccessException, Exception {
    	
    	Producto p = this.daoProducto.findById(id);
    	
    	if(p.getCategoria().getDescripcion().equals("Pastas"))  {
    		/*
    		 * Si el producto no tiene cargado salsas, nunca se inicializa en el ClassToDto.
    		 */
    		Hibernate.initialize(p.getSalsas());
    		
    		List<Salsa> salsas = this.findByProducto(id);    		
    		for(Salsa s: salsas) {
    			p.getSalsas().add(s);
    		}		
    	} 
    	
    	/*
    	 * Traemos los precios del producto, para poder mostrar el precio unitario en el carrito.
    	 */
    	List<Precio> precios = this.daoPrecio.findPreciosByProducto(p.getIdProducto());
    	p.getPrecios().addAll(precios);  		
    	return p;
	}	
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public ProductoDto findDtoById(Long id) throws DataAccessException, Exception {
    	Producto pro = this.daoProducto.findById(id);
    	//Buscar precio del producto.
    	//
		
		return ClassToDto.productoToDto(pro);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Producto> findByComercio(Long idComercio) throws DataAccessException, Exception {
		return this.daoProducto.findByComercio(idComercio);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Producto> findByCategoria(Long idCategoria) throws DataAccessException, Exception {
		return this.daoProducto.findByCategoria(idCategoria);
	}
    
    @Transactional(readOnly = false)
    public boolean validaSaveProducto(ProductoDto producto)
	    throws DataAccessException, Exception {
	
    	boolean rta = true;
    	Producto prod = this.daoProducto.findByNameAndComercio(producto.getDescripcion(), producto.getComercio().getIdComercio());
    	if(prod == null) {
    		rta = true;
    	} else {
    		rta = false;
    	}
    	return rta;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void saveProducto(ProductoDto producto) throws DataAccessException, Exception {
    	/*
    	 * Desde el jsp se usa la descripción de la categoria (en el select) y
    	 * luego busco la categoria por descripción para obtener el ID.
    	 */
    	CategoriaProducto categoria = this.daoCategoria.findByName(producto.getCategoria().getDescripcion());
    	producto.setCategoria(categoria);
    	
    	/* Transformo dto a model. */
    	Producto pro = ClassToDto.dtoToProducto(producto);
    	
    	/* Guardar todas las salsas del producto */
    	if(producto.getCategoria().equals("Pastas")) {
    		for(Salsa s: producto.getSalsas()) {
    			s.setProducto(pro);
    		}
    	}
    	pro.getSalsas().addAll(producto.getSalsas());
    	
    	this.daoProducto.save(pro);
    }
 
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean deleteProducto(Long id) throws DataAccessException, Exception {
    	return this.daoProducto.delete(id);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Precio> findPreciosByProducto(Long idProducto) throws DataAccessException, Exception {
		return this.daoPrecio.findPreciosByProducto(idProducto);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean updateProducto(ProductoDto producto) throws DataAccessException, Exception {

    	Producto pro = this.findByNameAndComercio(producto.getDescripcion(), producto.getComercio().getIdComercio());
    	
    	/* Valida que no haya otro producto con el mismo nombre, y
    	 * que no se haya encontrado el mismo producto que se va a modificar.
    	 */
    	if(pro == null || pro.getIdProducto().equals(producto.getIdProducto())) {
    		
    		Producto productoUpdate = this.daoProducto.findById(producto.getIdProducto());
    		productoUpdate.setComercio(producto.getComercio());
    		productoUpdate.setDescripcion(producto.getDescripcion());
    		productoUpdate.setObservaciones(producto.getObservaciones());
    		productoUpdate.setActivo(producto.getActivo());
    		
    		if(producto.getPrecios().size() > 0) {
    			Precio precio = producto.getPrecios().get(0);
    			precio.setProducto(productoUpdate);
    			productoUpdate.setUltimoPrecio(precio.getValor());
        		productoUpdate.getPrecios().clear();
        		productoUpdate.getPrecios().add(producto.getPrecios().get(0));
        		
    		}

        	/*Guardar las salsas nuevas al producto*/
    		if(productoUpdate.getCategoria().getDescripcion().equals("Pastas")) {
            	for(Salsa sal : producto.getSalsas()) {
            		sal.setProducto(productoUpdate);
            	}
            	productoUpdate.getSalsas().addAll(producto.getSalsas());   			
    		} else if(productoUpdate.getCategoria().getDescripcion().equals("Helados")) {
    			productoUpdate.setCantidadGustos(producto.getCantidadGustos());
    		} else if(productoUpdate.getCategoria().getDescripcion().equals("Pizza")) {
    			/*
    			 * EN DESARROLLO.
    			 */
    		} 
    		try {
        		this.daoProducto.update(productoUpdate);    			
    		} catch (Exception e) {
    			e.getMessage();
    			return false;
    		}
  		
    		return true;
    	} else {
    		return false;
    	}
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Salsa> findByProducto(Long idProducto) throws DataAccessException, Exception {
		return this.daoSalsa.findByProducto(idProducto);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Producto> findProductoByNameCategoria(String descripcion) throws DataAccessException, Exception {
		return this.daoProducto.findByNameCategoria(descripcion);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Producto> findProductoByNameCategoriaAndComercio(String descripcion, Long idComercio)
			throws DataAccessException, Exception {
		return this.daoProducto.findByNameCategoriaAndComercio(descripcion, idComercio);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Salsa findSalsaById(Long id) throws DataAccessException, Exception { 	
    	 return this.daoSalsa.findSalsaById(id);
	}	
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Boolean borrarSalsa(Long id) throws DataAccessException, Exception { 	
 	 return this.daoSalsa.delete(id);
	}
    
    
}
