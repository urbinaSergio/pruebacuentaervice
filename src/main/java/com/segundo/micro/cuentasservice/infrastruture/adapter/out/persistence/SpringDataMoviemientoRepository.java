package com.segundo.micro.cuentasservice.infrastruture.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataMoviemientoRepository extends JpaRepository<MovimientoEntity, Long> {

    List<MovimientoEntity> findByCuentaId(Long cuentaId);

    List<MovimientoEntity> findByCuenta_IdAndFechaBetween(
            Long cuentaId,
            LocalDateTime inicio,
            LocalDateTime fin);
}
