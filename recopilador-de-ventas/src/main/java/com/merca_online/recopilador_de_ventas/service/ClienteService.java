package com.merca_online.recopilador_de_ventas.service;

import com.merca_online.recopilador_de_ventas.dto.ClienteGetDto;
import com.merca_online.recopilador_de_ventas.entity.Cliente;
import com.merca_online.recopilador_de_ventas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<ClienteGetDto> getListaClientes() {
        List<Cliente> listaClientes = clienteRepository.findAll();
        List<ClienteGetDto> listaClientesDto = new ArrayList<>();

        for (Cliente c : listaClientes){
            ClienteGetDto clienteGetDto = new ClienteGetDto(c.getIdCliente(),c.getNombre(),c.getApellido(),c.getDni());
            listaClientesDto.add(clienteGetDto);
        }
        return listaClientesDto;
    }

    @Override
    public ClienteGetDto postCliente(Cliente cliente) {
        Cliente clienteNuevo = clienteRepository.save(cliente);
        return new ClienteGetDto(clienteNuevo);
    }

    @Override
    public ClienteGetDto getCliente(Long id) {
        Cliente clienteEncontrado = clienteRepository.findById(id).orElse(null);
        if (clienteEncontrado == null) {
            throw new SinDatosException("El id indicado no existe!!");
        }else
            return new ClienteGetDto(clienteEncontrado);
    }

    @Override
    public String deleteCliente(Long id) {
        Cliente clienteEncontrado = clienteRepository.findById(id).orElse(null);
        if (clienteEncontrado == null) {
            throw new SinDatosException("El id indicado no existe!!");
        }else
            clienteRepository.deleteById(id);
        return "El id indicado fué eliminado con exito!!";
    }

    @Override
    public ClienteGetDto putCliente(Cliente cliente) {
        Cliente clienteEditado = clienteRepository.getReferenceById(cliente.getIdCliente());
        clienteEditado.editarCliente(cliente);
        clienteRepository.save(clienteEditado);
        return this.getCliente(clienteEditado.getIdCliente());
    }

    @RestControllerAdvice
    public static class ControladorExcepciones{
        @ExceptionHandler(SinDatosException.class)
        public ResponseEntity<String> mensajeExcepcion(SinDatosException mensaje){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje.getMessage());
        }
    }
}
