package com.merca_online.recopilador_de_ventas.service;

import com.merca_online.recopilador_de_ventas.dto.ProductoGetDto;
import com.merca_online.recopilador_de_ventas.entity.Producto;
import com.merca_online.recopilador_de_ventas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<ProductoGetDto> getListaProductos() {
        List<Producto> listaProductos = productoRepository.findAll();
        List<ProductoGetDto> listaProductosDto = new ArrayList<>();

        for (Producto p : listaProductos){
            ProductoGetDto productoGetDto = new ProductoGetDto(p.getCodigoProducto(), p.getNombre(),
                    p.getMarca(), p.getCosto(), p.getCantidadDisponible());
            listaProductosDto.add(productoGetDto);
        }
        return listaProductosDto;
    }

    @Override
    public ProductoGetDto postProducto(Producto producto) {
        Producto productoNuevo = productoRepository.save(producto);
        return new ProductoGetDto(productoNuevo);
    }

    @Override
    public ProductoGetDto getProducto(Long id) {
        Producto productoEncontrado = productoRepository.findById(id).orElse(null);
        if (productoEncontrado == null) {
            throw new SinDatosException("El id indicado no existe!!");
        }else
            return new ProductoGetDto(productoEncontrado);
    }

    @Override
    public String deleteProducto(Long id) {
        Producto productoEncontrado = productoRepository.findById(id).orElse(null);
        if (productoEncontrado == null) {
            throw new SinDatosException("El id indicado no existe!!");
        }else
            productoRepository.deleteById(id);
        return "El id indicado fué eliminado con exito!!";
    }

    @Override
    public ProductoGetDto putProducto(Producto producto) {
        Producto clienteEditado = productoRepository.getReferenceById(producto.getCodigoProducto());
        clienteEditado.editarProducto(producto);
        productoRepository.save(clienteEditado);
        return this.getProducto(clienteEditado.getCodigoProducto());
    }

    @Override
    public List<ProductoGetDto> getProductosCantidad() {

        List<Producto> listaEncontrada = productoRepository.findAll();
        List<ProductoGetDto> listaProductoDto = new ArrayList<>();

        for (Producto p : listaEncontrada) {
            if (p.getCantidadDisponible() <= 5) {
                ProductoGetDto productoGetDto = new ProductoGetDto(p.getCodigoProducto(), p.getNombre(), p.getMarca()
                        , p.getCosto(), p.getCantidadDisponible());
                listaProductoDto.add(productoGetDto);
            }
        }
        if (listaProductoDto.isEmpty()) {
            throw new SinDatosException("Ningun producto cuenta con menos de 5 unidades en el stock");
        }else
            return listaProductoDto;
    }
}
