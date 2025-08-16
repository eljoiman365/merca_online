package com.merca_online.recopilador_de_ventas.service;

import com.merca_online.recopilador_de_ventas.dto.ProductoGetDto;
import com.merca_online.recopilador_de_ventas.entity.Producto;

import java.util.List;

public interface IProductoService {
    public List<ProductoGetDto> getListaProductos();
    public ProductoGetDto postProducto(Producto producto);
    public ProductoGetDto getProducto(Long id);
    public String deleteProducto(Long id);
    public ProductoGetDto putProducto(Producto producto);
    public List<ProductoGetDto> getProductosCantidad();
}
