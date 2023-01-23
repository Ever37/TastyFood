package com.productos.app.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.productos.app.dao.ProductoDao;
import com.productos.app.model.Producto;

@Service("productoService")
@Transactional
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoDao daoProducto;

	/*
	 * Lista todos los productos
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public List<Producto> findAll() {
		return this.daoProducto.findAll();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public Producto findById(Long id) {
		return this.daoProducto.findById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public List<Producto> findAllWithoutDate() {
		return this.daoProducto.findAllWithoutDate();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public Producto findByName(String descripcion) {
		return this.daoProducto.findByName(descripcion);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public void saveProducto(Producto producto) {
		this.daoProducto.save(producto);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public Boolean deleteProducto(Long id) {
		return this.daoProducto.delete(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public Boolean updateProducto(Producto producto) {

		Producto pro = this.findByName(producto.getDescripcion());

		/*
		 * Valida que no haya otro producto con el mismo nombre, y que no se haya
		 * encontrado el mismo producto que se va a modificar.
		 */
		if (pro == null || pro.getIdProducto().equals(producto.getIdProducto())) {

			Producto productoUpdate = this.daoProducto.findById(producto.getIdProducto());
			productoUpdate.setDescripcion(producto.getDescripcion());
			productoUpdate.setObservaciones(producto.getObservaciones());
			productoUpdate.setActivo(producto.getActivo());

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
}
