package com.segundo.micro.cuentasservice.domain.port.in;

import com.segundo.micro.cuentasservice.domain.model.Movimiento;

public interface CrearMovimientoUseCase {
    Movimiento crear(Movimiento movimiento);
}
