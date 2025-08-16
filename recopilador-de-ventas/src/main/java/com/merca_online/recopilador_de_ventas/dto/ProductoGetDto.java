package com.merca_online.recopilador_de_ventas.dto;

import com.merca_online.recopilador_de_ventas.entity.Producto;

public record ProductoGetDto(
        Long codigo_producto,
        String nombre,
        String marca,
        Double costo,
        Integer cantidad_disponible
){
    public ProductoGetDto(Producto producto){
        this(producto.getCodigoProducto(), producto.getNombre(), producto.getMarca(), producto.getCosto(),
                producto.getCantidadDisponible());
    }
}
