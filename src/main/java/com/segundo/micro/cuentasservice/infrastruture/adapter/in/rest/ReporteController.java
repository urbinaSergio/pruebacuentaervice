package com.segundo.micro.cuentasservice.infrastruture.adapter.in.rest;

import com.segundo.micro.cuentasservice.domain.dto.EstadoCuentaReporte;
import com.segundo.micro.cuentasservice.domain.port.in.GenerarReporteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final GenerarReporteUseCase generarReporteUseCase;

    public ReporteController(GenerarReporteUseCase generarReporteUseCase) {
        this.generarReporteUseCase = generarReporteUseCase;
    }

    @GetMapping
    public ResponseEntity<EstadoCuentaReporte> generar(
            @RequestParam Long clienteId,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        return ResponseEntity.ok(
                generarReporteUseCase.generar(clienteId, fechaInicio, fechaFin)
        );
    }
}
