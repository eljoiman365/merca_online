package com.merca_online.recopilador_de_ventas.repository;

import com.merca_online.recopilador_de_ventas.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findByFechaVenta(LocalDate fechaVenta);
    List<Venta> findByTotal(double ventaMasAlta);
}
