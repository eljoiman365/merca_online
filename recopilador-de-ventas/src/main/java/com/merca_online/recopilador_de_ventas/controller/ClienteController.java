package com.merca_online.recopilador_de_ventas.controller;

import com.merca_online.recopilador_de_ventas.dto.ClienteGetDto;
import com.merca_online.recopilador_de_ventas.entity.Cliente;
import com.merca_online.recopilador_de_ventas.service.ClienteService;
import com.merca_online.recopilador_de_ventas.service.SinDatosException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("crear")
    @Transactional
    public ResponseEntity<ClienteGetDto> agregarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.postCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteGetDto>> listaClientes(){
        return ResponseEntity.ok(clienteService.getListaClientes());
    }

    @GetMapping("{id_cliente}")
    public ResponseEntity<ClienteGetDto> obtenerCliente(@PathVariable Long id_cliente){
        return ResponseEntity.ok(clienteService.getCliente(id_cliente));
    }

    @DeleteMapping("eliminar/{id_cliente}")
    @Transactional
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id_cliente){
        return ResponseEntity.ok(clienteService.deleteCliente(id_cliente));
    }

    @PutMapping("editar")
    @Transactional
    public ResponseEntity<ClienteGetDto> editarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.putCliente(cliente));
    }

    @RestControllerAdvice
    public static class ControladorExcepciones{
        @ExceptionHandler(SinDatosException.class)
        public ResponseEntity<String> mensajeExcepcion(SinDatosException mensaje){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje.getMessage());
        }
    }
}
