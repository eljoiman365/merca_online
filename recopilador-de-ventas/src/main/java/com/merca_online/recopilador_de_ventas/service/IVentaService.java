package com.merca_online.recopilador_de_ventas.service;

import com.merca_online.recopilador_de_ventas.dto.GetVentaListaProductos;
import com.merca_online.recopilador_de_ventas.dto.GetVentaMasAltaDto;
import com.merca_online.recopilador_de_ventas.dto.VentaGetDto;
import com.merca_online.recopilador_de_ventas.entity.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    public List<VentaGetDto> getListaVentas();
    public VentaGetDto postVenta(Venta venta);
    public VentaGetDto getVenta(Long codigo_venta);
    public String deleteVenta(Long id);
    public VentaGetDto putVenta(Venta venta);
    public List<GetVentaListaProductos> getListaProductos(Long codigo_venta);
    public String getTotalVentasPorDia(LocalDate fecha_venta);
    public List<GetVentaMasAltaDto> getVentaMasAlta();

}
