package com.segundo.micro.cuentasservice.domain.port.out;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepositoryPort {
    Cuenta save(Cuenta cuenta);

    Optional<Cuenta> findById(Long id);

    List<Cuenta> findAll();

    Optional<Cuenta> findByNumeroCuenta(String identificacion);

    List<Cuenta> findByClienteId(Long clienteId);
}
