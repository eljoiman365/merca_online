package com.merca_online.recopilador_de_ventas.dto;

import com.merca_online.recopilador_de_ventas.entity.Cliente;
import com.merca_online.recopilador_de_ventas.entity.Venta;

public record GetVentaMasAltaDto(
        Long codigo_venta,
        double total,
        int cantidad_productos,
        String nombre,
        String apellido
) {
    public GetVentaMasAltaDto(Venta venta, int cantidad_productos){
        this(venta.getCodigoVenta(), venta.getTotal(), cantidad_productos, venta.getCliente().getNombre(),
                venta.getCliente().getApellido());
    }
}
