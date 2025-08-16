package com.merca_online.recopilador_de_ventas.repository;

import com.merca_online.recopilador_de_ventas.entity.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    @Modifying @Transactional @Query("UPDATE Producto p SET p.cantidadDisponible = :cantidad WHERE p.codigoProducto = :codigo")
    void actualizarProducto(@Param("cantidad")int cantidadDisponible,
                           @Param("codigo") Long codigoProducto);
}
