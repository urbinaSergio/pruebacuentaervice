package com.segundo.micro.cuentasservice.infrastruture.adapter.in.dto;

import java.math.BigDecimal;

public class CrearMovimientoRequest {
    public Long cuentaId;
    public String tipoMovimiento; // DEBITO o CREDITO
    public BigDecimal valor;
}
