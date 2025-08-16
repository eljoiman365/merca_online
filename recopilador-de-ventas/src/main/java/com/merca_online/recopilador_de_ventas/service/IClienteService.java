package com.merca_online.recopilador_de_ventas.service;

import com.merca_online.recopilador_de_ventas.dto.ClienteGetDto;
import com.merca_online.recopilador_de_ventas.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<ClienteGetDto> getListaClientes();
    public ClienteGetDto postCliente(Cliente cliente);
    public ClienteGetDto getCliente(Long id);
    public String deleteCliente(Long id);
    public ClienteGetDto putCliente(Cliente cliente);
}
