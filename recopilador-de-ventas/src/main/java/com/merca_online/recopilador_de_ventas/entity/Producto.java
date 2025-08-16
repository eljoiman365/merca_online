package com.merca_online.recopilador_de_ventas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Producto {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "codigo_producto")
    private Long codigoProducto;
    private String nombre;
    private String marca;
    private Double costo;
    @Column(name = "cantidad_disponible")
    private Integer cantidadDisponible;

    public void editarProducto(Producto producto) {
        this.codigoProducto = producto.getCodigoProducto();
        this.nombre = producto.getNombre();
        this.marca = producto.getMarca();
        this.costo = producto.getCosto();
        this.cantidadDisponible = producto.getCantidadDisponible();
    }
}
