package com.merca_online.recopilador_de_ventas.dto;

import com.merca_online.recopilador_de_ventas.entity.Producto;
import com.merca_online.recopilador_de_ventas.entity.Venta;

import java.util.List;

public record GetVentaListaProductos(
        List<Producto> listaProductos
) {
    public GetVentaListaProductos(Venta venta){
        this(venta.getListaProductos());
    }
}
