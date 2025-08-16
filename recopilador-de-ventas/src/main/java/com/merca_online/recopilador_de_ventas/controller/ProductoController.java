package com.merca_online.recopilador_de_ventas.controller;

import com.merca_online.recopilador_de_ventas.dto.ProductoGetDto;
import com.merca_online.recopilador_de_ventas.entity.Producto;
import com.merca_online.recopilador_de_ventas.service.ProductoService;
import com.merca_online.recopilador_de_ventas.service.SinDatosException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping("crear")
    @Transactional
    public ResponseEntity<ProductoGetDto> crearProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.postProducto(producto));
    }
    @GetMapping
    public ResponseEntity<List<ProductoGetDto>> listaProductos(){
        return ResponseEntity.ok(productoService.getListaProductos());
    }
    @GetMapping("{codigo_producto}")
    public ResponseEntity<ProductoGetDto> traerProducto(@PathVariable Long codigo_producto){
        return ResponseEntity.ok(productoService.getProducto(codigo_producto));
    }
    @DeleteMapping("eliminar/{codigo_producto}")
    @Transactional
    public ResponseEntity<String> eliminarProducto(@PathVariable Long codigo_producto){
        return ResponseEntity.ok(productoService.deleteProducto(codigo_producto));
    }
    @PutMapping("editar")
    @Transactional
    public ResponseEntity<ProductoGetDto> eliminarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.putProducto(producto));
    }

    @GetMapping("falta_stock")
    public ResponseEntity<List<ProductoGetDto>> obtenerProductoInventario(){
        return ResponseEntity.ok(productoService.getProductosCantidad());
    }

    @RestControllerAdvice
    public static class ControladorExcepciones{
        @ExceptionHandler(SinDatosException.class)
        public ResponseEntity<String> mensajeExcepcion(SinDatosException mensaje){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje.getMessage());
        }
    }
}
