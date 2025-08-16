package com.merca_online.recopilador_de_ventas.service;

public class SinDatosException extends RuntimeException{
    public SinDatosException(String mensaje){
        super(mensaje);
    }
}
