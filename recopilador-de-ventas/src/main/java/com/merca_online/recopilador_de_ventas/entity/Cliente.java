package com.merca_online.recopilador_de_ventas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Cliente {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_cliente")
    private Long idCliente;
    private String nombre;
    private String apellido;
    private BigInteger dni;

    public void editarCliente(Cliente cliente){
        this.idCliente = cliente.getIdCliente();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.dni = cliente.getDni();
    }
}
