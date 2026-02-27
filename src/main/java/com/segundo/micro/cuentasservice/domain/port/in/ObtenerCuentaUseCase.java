package com.segundo.micro.cuentasservice.domain.port.in;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;

public interface ObtenerCuentaUseCase {
    Cuenta obtenerPorId(Long id);
}
