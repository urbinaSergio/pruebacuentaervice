package com.segundo.micro.cuentasservice.aplication.service;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;
import com.segundo.micro.cuentasservice.domain.port.in.ActualizarCuentaUseCase;
import com.segundo.micro.cuentasservice.domain.port.in.CrearCuentaUseCase;
import com.segundo.micro.cuentasservice.domain.port.in.ListarCuentaUseCase;
import com.segundo.micro.cuentasservice.domain.port.in.ObtenerCuentaUseCase;
import com.segundo.micro.cuentasservice.domain.port.out.CuentaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
@Service
public class CuentaService implements CrearCuentaUseCase, ActualizarCuentaUseCase, ListarCuentaUseCase, ObtenerCuentaUseCase {
    private final CuentaRepositoryPort repository;

    public CuentaService(CuentaRepositoryPort repository) {
        this.repository = repository;
    }
    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        cuenta.setSaldoActual(cuenta.getSaldoInicial());
        cuenta.setNumeroCuenta(generarNumeroCuenta());

        return repository.save(cuenta);
    }

    private String generarNumeroCuenta() {
        Random random = new Random();
        long numero = 1000000000L +
                (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(numero);
    }

    @Override
    public Cuenta actualizar(Cuenta cuenta) {
        return repository.save(cuenta);
    }

    @Override
    public List<Cuenta> listar() {
        List<Cuenta> cuentas = repository.findAll();

        if (cuentas.isEmpty()) {
            throw new RuntimeException("No existen clientes");
        }

        return cuentas;
    }

    @Override
    public Cuenta obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CuentaNoEncontradoException(id));
    }

    public class CuentaNoEncontradoException extends RuntimeException {
        public CuentaNoEncontradoException(Long id) {
            super("Cliente no encontrado con id: " + id);
        }
    }
}
