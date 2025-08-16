package com.merca_online.recopilador_de_ventas.service;

import com.merca_online.recopilador_de_ventas.dto.GetVentaListaProductos;
import com.merca_online.recopilador_de_ventas.dto.GetVentaMasAltaDto;
import com.merca_online.recopilador_de_ventas.dto.VentaGetDto;
import com.merca_online.recopilador_de_ventas.entity.Producto;
import com.merca_online.recopilador_de_ventas.entity.Venta;
import com.merca_online.recopilador_de_ventas.repository.ProductoRepository;
import com.merca_online.recopilador_de_ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<VentaGetDto> getListaVentas() {
        List<Venta> listaVentas = ventaRepository.findAll();
        List<VentaGetDto> listaVentaDto = new ArrayList<>();

        for (Venta v : listaVentas){
            VentaGetDto ventaGetDto = new VentaGetDto (v.getCodigoVenta(), v.getFechaVenta(),
                    v.getTotal(), v.getListaProductos(), v.getCliente());
            listaVentaDto.add(ventaGetDto);
        }
        return listaVentaDto;
    }

    @Override
    public VentaGetDto postVenta(Venta venta) {
        List<Producto> listaProductos = venta.getListaProductos();
        Venta totalCostoVenta = new Venta(venta);
        double totalVenta = 0;

        for (Producto p : listaProductos){

            Producto productoIdentificado = productoRepository.getReferenceById(p.getCodigoProducto());
            if (productoIdentificado.getCantidadDisponible() == 0) {
                throw new SinDatosException("La transacción no es posible ya que no hay stock disponible!!");
            }else{
                int totalInventario = productoIdentificado.getCantidadDisponible() -1;
                p.setCantidadDisponible(totalInventario);
                productoRepository.actualizarProducto(totalInventario,productoIdentificado.getCodigoProducto());
                totalVenta = totalVenta + productoIdentificado.getCosto();
                totalCostoVenta.setTotal(totalVenta);
            }
        }
        ventaRepository.save(totalCostoVenta);
        return new VentaGetDto(totalCostoVenta);
    }

    @Override
    public VentaGetDto getVenta(Long id) {
        Venta ventaEncontrada = ventaRepository.findById(id).orElse(null);
        if (ventaEncontrada == null) {
            throw new SinDatosException("El id indicado no existe!!");
        }else
            return new VentaGetDto(ventaEncontrada);
    }

    @Override
    public String deleteVenta(Long id) {
        Venta ventaEncontrada = ventaRepository.findById(id).orElse(null);
        if (ventaEncontrada == null) {
            throw new SinDatosException("El id indicado no existe!!");
        }else
            ventaRepository.deleteById(id);
        return "El id indicado fué eliminado con exito!!";
    }

    @Override
    public VentaGetDto putVenta(Venta venta) {
        Venta ventaEditada = ventaRepository.getReferenceById(venta.getCodigoVenta());
        ventaEditada.editarVenta(venta);
        ventaRepository.save(ventaEditada);
        return this.getVenta(ventaEditada.getCodigoVenta());
    }

    @Override
    public List<GetVentaListaProductos> getListaProductos(Long codigo_venta) {
        Venta listaVentas = ventaRepository.findById(codigo_venta).orElse(null);

        if (listaVentas == null) {
            throw new SinDatosException("El código de la venta no existe!!");
        }else
            return new ArrayList<>(Collections.singleton(new GetVentaListaProductos(listaVentas)));
    }

    @Override
    public String getTotalVentasPorDia(LocalDate fecha_venta) {
        List<Venta> listaVentasPorDia = ventaRepository.findByFechaVenta(fecha_venta);
        double totalVentas = 0;

        for(Venta v : listaVentasPorDia){
            totalVentas = totalVentas + v.getTotal();
        }
        return "El total de ventas obtenidas para la fecha " + fecha_venta + " es: $" + totalVentas;
    }

    @Override
    public List<GetVentaMasAltaDto>  getVentaMasAlta() {
        List<Venta> listaVentas = ventaRepository.findAll();
        Venta ventaMayor = listaVentas.stream().max(Comparator.comparingDouble(Venta::getTotal)).orElseThrow();
        List<Venta> listaVentasEncontradas = ventaRepository.findByTotal(ventaMayor.getTotal());
        List<GetVentaMasAltaDto> listaVentasMasAltaDto = new ArrayList<>();

        for (Venta v : listaVentasEncontradas){
            GetVentaMasAltaDto getVentaMasAltaDto = new GetVentaMasAltaDto(v,v.getListaProductos().size());
            listaVentasMasAltaDto.add(getVentaMasAltaDto);
        }
        return listaVentasMasAltaDto;
    }


    @RestControllerAdvice
    public static class ControladorExcepciones{
        @ExceptionHandler(SinDatosException.class)
        public ResponseEntity<String> mensajeExcepcion(SinDatosException mensaje){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje.getMessage());
        }
    }
}
