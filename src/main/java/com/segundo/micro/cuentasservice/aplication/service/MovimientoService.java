package com.segundo.micro.cuentasservice.aplication.service;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;
import com.segundo.micro.cuentasservice.domain.model.Movimiento;
import com.segundo.micro.cuentasservice.domain.port.in.CrearMovimientoUseCase;
import com.segundo.micro.cuentasservice.domain.port.out.CuentaRepositoryPort;
import com.segundo.micro.cuentasservice.domain.port.out.MovimientoRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MovimientoService implements CrearMovimientoUseCase {
    private final MovimientoRepositoryPort movimientoRepository;
    private final CuentaRepositoryPort cuentaRepository;

    public MovimientoService(MovimientoRepositoryPort movimientoRepository,
                             CuentaRepositoryPort cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    @Transactional
    public Movimiento crear(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        BigDecimal nuevoSaldo;

        if ("DEBITO".equalsIgnoreCase(movimiento.getTipoMovimiento())) {

            nuevoSaldo = cuenta.getSaldoActual().subtract(movimiento.getValor());

            if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Saldo no disponible");
            }

        } else { // CREDITO
            nuevoSaldo = cuenta.getSaldoActual().add(movimiento.getValor());
        }

        cuenta.setSaldoActual(nuevoSaldo);
        cuentaRepository.save(cuenta);


        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(LocalDateTime.now());


        return movimientoRepository.save(movimiento);
    }
}
