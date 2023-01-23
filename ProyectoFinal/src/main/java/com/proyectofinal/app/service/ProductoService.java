package com.proyectofinal.app.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.dto.ProductoDto;
import com.proyectofinal.app.model.Precio;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Salsa;

public interface ProductoService {

	List<Producto> findAll() throws DataAccessException, Exception;
	
	List<Producto> findAllWithoutDate() throws DataAccessException, Exception;	
	
	Producto findByName(String descripcion) throws DataAccessException, Exception;
	
	Producto findById(Long id) throws DataAccessException, Exception;
	
	ProductoDto findDtoById(Long id) throws DataAccessException, Exception;
	
	List<Producto> findByComercio(Long idComercio) throws DataAccessException, Exception;
	
	List<Producto> findByCategoria(Long idCategoria)  throws DataAccessException, Exception;
	
    boolean validaSaveProducto(ProductoDto producto) throws DataAccessException, Exception;

    void saveProducto(ProductoDto producto) throws DataAccessException, Exception;
    
    Boolean deleteProducto(Long id) throws DataAccessException, Exception;
    
    List<Precio> findPreciosByProducto(Long id) throws DataAccessException, Exception;
    
    Boolean updateProducto(ProductoDto producto) throws DataAccessException, Exception;
    
    List<Salsa> findByProducto(Long idProducto) throws DataAccessException, Exception;
    
    Salsa findSalsaById(Long idSalsa) throws DataAccessException, Exception;
    
    Boolean borrarSalsa(Long idSalsa) throws DataAccessException, Exception;
    
    List<Producto> findProductoByNameCategoria(String descripcion) throws DataAccessException, Exception;

    List<Producto> findProductoByNameCategoriaAndComercio(String descripcion, Long idComercio) 
    		throws DataAccessException, Exception;
}
