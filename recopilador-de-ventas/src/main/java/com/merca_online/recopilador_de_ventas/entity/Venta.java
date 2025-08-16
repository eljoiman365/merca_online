package com.merca_online.recopilador_de_ventas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jdk.jfr.MemoryAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;

import java.time.LocalDate;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Venta {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "codigo_venta")
    private Long codigoVenta;
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;
    private Double total;
    @ManyToMany
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_cliente",
    name = "id_cliente")
    private Cliente cliente;

    public Venta (Venta venta){
        this.codigoVenta = venta.getCodigoVenta();
        this.fechaVenta = venta.getFechaVenta();
        this.listaProductos = venta.getListaProductos();
        this.cliente = venta.getCliente();
    }

    public void editarVenta(Venta venta){
        this.codigoVenta = venta.getCodigoVenta();
        this.fechaVenta = venta.getFechaVenta();
        this.listaProductos = venta.getListaProductos();
        this.cliente = venta.getCliente();
        this.total = venta.getTotal();
    }
}
