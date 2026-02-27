package com.segundo.micro.cuentasservice.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstadoCuentaReporte {

    private Long clienteId;
    private List<CuentaReporte> cuentas;

}
