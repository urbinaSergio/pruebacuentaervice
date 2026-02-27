package com.segundo.micro.cuentasservice.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CuentaReporte {

    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoActual;
    private List<MovimientoReporte> movimientos;

}
