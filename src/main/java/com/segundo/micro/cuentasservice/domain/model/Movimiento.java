package com.segundo.micro.cuentasservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

    private Long id;
    private Long cuentaId;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    private LocalDateTime fecha;
}
