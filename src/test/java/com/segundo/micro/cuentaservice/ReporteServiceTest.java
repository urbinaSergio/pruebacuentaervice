package com.segundo.micro.cuentaservice;

import com.segundo.micro.cuentasservice.aplication.service.CuentaService;
import com.segundo.micro.cuentasservice.aplication.service.ReporteService;
import com.segundo.micro.cuentasservice.domain.dto.EstadoCuentaReporte;
import com.segundo.micro.cuentasservice.domain.model.Cuenta;
import com.segundo.micro.cuentasservice.domain.model.Movimiento;
import com.segundo.micro.cuentasservice.domain.port.out.CuentaRepositoryPort;
import com.segundo.micro.cuentasservice.domain.port.out.MovimientoRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {

    @Mock
    private CuentaRepositoryPort cuentaRepository;

    @Mock
    private MovimientoRepositoryPort movimientoRepository;

    @InjectMocks
    private ReporteService reporteService;

    @Test
    void generarReporte_debeRetornarReporte() {

        // Datos básicos
        Long clienteId = 1L;
        LocalDate inicio = LocalDate.of(2026, 1, 25);
        LocalDate fin = LocalDate.of(2026, 2, 27);

        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("12345");
        cuenta.setSaldoInicial(BigDecimal.valueOf(800.00));
        cuenta.setClienteId(clienteId);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuentaId(1L);

        // Simulamos repositorios
        when(cuentaRepository.findByClienteId(clienteId))
                .thenReturn(List.of(cuenta));

        when(movimientoRepository.findByCuentaIdAndFechaBetween(
                anyLong(),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        )).thenReturn(List.of(movimiento));

        // Ejecutamos
        EstadoCuentaReporte reporte =
                reporteService.generar(clienteId, inicio, fin);

        // Verificamos algo básico
        assertNotNull(reporte);
        assertFalse(reporte.getCuentas().isEmpty());
    }
}
