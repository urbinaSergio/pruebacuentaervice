package com.segundo.micro.cuentasservice.infrastruture.adapter.in.dto;

import java.math.BigDecimal;

public class ActualizarCuentaRequest {

    public BigDecimal saldoActual;
    public String numeroCuenta;
    public String tipoCuenta;
    public BigDecimal saldoInicial;
    public Boolean estado;
}