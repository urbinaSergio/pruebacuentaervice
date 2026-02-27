package com.segundo.micro.cuentasservice.aplication.service;

import com.segundo.micro.cuentasservice.domain.dto.CuentaReporte;
import com.segundo.micro.cuentasservice.domain.dto.EstadoCuentaReporte;
import com.segundo.micro.cuentasservice.domain.dto.MovimientoReporte;
import com.segundo.micro.cuentasservice.domain.model.Cuenta;
import com.segundo.micro.cuentasservice.domain.model.Movimiento;
import com.segundo.micro.cuentasservice.domain.port.in.GenerarReporteUseCase;
import com.segundo.micro.cuentasservice.domain.port.out.CuentaRepositoryPort;
import com.segundo.micro.cuentasservice.domain.port.out.MovimientoRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReporteService implements GenerarReporteUseCase {
    private final CuentaRepositoryPort cuentaRepository;
    private final MovimientoRepositoryPort movimientoRepository;

    public ReporteService(CuentaRepositoryPort cuentaRepository,
                          MovimientoRepositoryPort movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public EstadoCuentaReporte generar(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        List<CuentaReporte> cuentasReporte = cuentas.stream().map(cuenta -> {
            List<Movimiento> movimientos =
                    movimientoRepository.findByCuentaIdAndFechaBetween(
                            cuenta.getId(),
                            fechaInicio.atStartOfDay(),
                            fechaFin.atTime(23, 59, 59)
                    );

            List<MovimientoReporte> movimientosReporte =
                    movimientos.stream().map(mov -> {
                        MovimientoReporte m = new MovimientoReporte();
                        m.setFecha(mov.getFecha());
                        m.setTipoMovimiento(mov.getTipoMovimiento());
                        m.setValor(mov.getValor());
                        m.setSaldo(mov.getSaldo());
                        return m;
                    }).toList();

            CuentaReporte cuentaReporte = new CuentaReporte();
            cuentaReporte.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaReporte.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaReporte.setSaldoActual(cuenta.getSaldoActual());
            cuentaReporte.setMovimientos(movimientosReporte);

            return cuentaReporte;


        }).toList();

        EstadoCuentaReporte reporte = new EstadoCuentaReporte();
        reporte.setClienteId(clienteId);
        reporte.setCuentas(cuentasReporte);

        return reporte;
    }
}
