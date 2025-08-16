package com.merca_online.recopilador_de_ventas.controller;

import com.merca_online.recopilador_de_ventas.dto.GetVentaListaProductos;
import com.merca_online.recopilador_de_ventas.dto.GetVentaMasAltaDto;
import com.merca_online.recopilador_de_ventas.dto.VentaGetDto;
import com.merca_online.recopilador_de_ventas.entity.Venta;
import com.merca_online.recopilador_de_ventas.service.SinDatosException;
import com.merca_online.recopilador_de_ventas.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("ventas")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @PostMapping("crear")
    @Transactional
    public ResponseEntity<VentaGetDto> crearVenta(@RequestBody Venta venta){
        return ResponseEntity.ok(ventaService.postVenta(venta));
    }
    @GetMapping
    public ResponseEntity<List<VentaGetDto>> listaVentas(){
        return ResponseEntity.ok(ventaService.getListaVentas());
    }
    @GetMapping("{codigo_venta}")
    public ResponseEntity<VentaGetDto> traerVenta(@PathVariable Long codigo_venta){
        return ResponseEntity.ok(ventaService.getVenta(codigo_venta));
    }
    @DeleteMapping("eliminar/{codigo_venta}")
    @Transactional
    public ResponseEntity<String> eliminarVenta(@PathVariable Long codigo_venta){
        return ResponseEntity.ok(ventaService.deleteVenta(codigo_venta));
    }
    @PutMapping("editar")
    @Transactional
    public ResponseEntity<VentaGetDto> eliminarVenta(@RequestBody Venta venta){
        return ResponseEntity.ok(ventaService.putVenta(venta));
    }
    @GetMapping("productos/{codigo_venta}")
    public ResponseEntity<List<GetVentaListaProductos>> listaProductosVenta(@PathVariable Long codigo_venta){
        return ResponseEntity.ok(ventaService.getListaProductos(codigo_venta));
    }
    @GetMapping("fecha/{fecha_venta}")
        public ResponseEntity<String> totalVentasPordia(@PathVariable LocalDate fecha_venta){
            return ResponseEntity.ok(ventaService.getTotalVentasPorDia(fecha_venta));
    }
    @GetMapping("mayor_venta")
    public ResponseEntity<List<GetVentaMasAltaDto>> obtenerVentaMayor(){
        return ResponseEntity.ok(ventaService.getVentaMasAlta());
    }

    @RestControllerAdvice
    public static class ControladorExcepciones{
        @ExceptionHandler(SinDatosException.class)
        public ResponseEntity<String> mensajeExcepcion(SinDatosException mensaje){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje.getMessage());
        }
    }
}
