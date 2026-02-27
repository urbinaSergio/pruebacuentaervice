package com.segundo.micro.cuentasservice.domain.port.out;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;
import com.segundo.micro.cuentasservice.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepositoryPort {

    Movimiento save(Movimiento movimiento);

    Optional<Movimiento> findById(Long id);

    List<Movimiento> findByCuentaId(Long cuentaId);

    List<Movimiento> findByCuentaIdAndFechaBetween(
            Long cuentaId,
            LocalDateTime inicio,
            LocalDateTime fin);
}
