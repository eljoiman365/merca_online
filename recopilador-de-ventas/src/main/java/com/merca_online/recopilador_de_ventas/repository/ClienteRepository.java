package com.merca_online.recopilador_de_ventas.repository;

import com.merca_online.recopilador_de_ventas.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
