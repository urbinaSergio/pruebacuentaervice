package com.segundo.micro.cuentasservice.domain.port.in;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;

import java.util.List;

public interface ListarCuentaUseCase {
    List<Cuenta> listar();
}