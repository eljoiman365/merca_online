package com.merca_online.recopilador_de_ventas.dto;

import com.merca_online.recopilador_de_ventas.entity.Cliente;

import java.math.BigInteger;

public record ClienteGetDto(
        Long id_cliente,
        String nombre,
        String apellido,
        BigInteger dni
) {
    public ClienteGetDto(Cliente cliente){
        this(cliente.getIdCliente(), cliente.getNombre(), cliente.getApellido(), cliente.getDni());
    }
}
