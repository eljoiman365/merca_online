package com.merca_online.recopilador_de_ventas.dto;

import com.merca_online.recopilador_de_ventas.entity.Cliente;
import com.merca_online.recopilador_de_ventas.entity.Producto;
import com.merca_online.recopilador_de_ventas.entity.Venta;

import java.time.LocalDate;
import java.util.List;

public record VentaGetDto(
        Long codigo_venta,
        LocalDate fecha_venta,
        Double total,
        List<Producto>listaProductos,
        Cliente cliente
) {
    public VentaGetDto(Venta venta){
     this(venta.getCodigoVenta(), venta.getFechaVenta(), venta.getTotal(), venta.getListaProductos(),
             venta.getCliente());
    }
}
