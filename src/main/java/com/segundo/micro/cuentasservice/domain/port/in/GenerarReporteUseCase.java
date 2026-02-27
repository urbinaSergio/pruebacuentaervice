package com.segundo.micro.cuentasservice.domain.port.in;

import com.segundo.micro.cuentasservice.domain.dto.EstadoCuentaReporte;

import java.time.LocalDate;

public interface GenerarReporteUseCase {
    EstadoCuentaReporte generar(Long clienteId,
                                LocalDate fechaInicio,
                                LocalDate fechaFin);
}
