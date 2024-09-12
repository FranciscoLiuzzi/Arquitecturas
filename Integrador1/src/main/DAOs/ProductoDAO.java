package main.DAOs;

import main.DTOs.ProductoMasRecauda;
import main.Objects.Producto;

public interface ProductoDAO extends dbDAO<Producto>{
	ProductoMasRecauda selectProductoQueMasRecauda();
}
