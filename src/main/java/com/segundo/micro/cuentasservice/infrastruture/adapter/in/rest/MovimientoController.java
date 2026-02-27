package com.segundo.micro.cuentasservice.infrastruture.adapter.in.rest;

import com.segundo.micro.cuentasservice.domain.model.Movimiento;
import com.segundo.micro.cuentasservice.domain.port.in.CrearMovimientoUseCase;
import com.segundo.micro.cuentasservice.infrastruture.adapter.in.dto.CrearMovimientoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final CrearMovimientoUseCase crearMovimientoUseCase;

    public MovimientoController(CrearMovimientoUseCase crearMovimientoUseCase) {
        this.crearMovimientoUseCase = crearMovimientoUseCase;
    }


    @Operation(
            summary = "Crear un nuevo movimiento",
            description = "Crea un movimiento bancario asociada a una cuenta"
    )
    @ApiResponse(responseCode = "200", description = "Cuenta creada correctamente")
    @PostMapping("/crear")
    public ResponseEntity<Movimiento> crear(
            @RequestBody CrearMovimientoRequest request) {

        Movimiento movimiento = new Movimiento();
        movimiento.setCuentaId(request.cuentaId);
        movimiento.setTipoMovimiento(request.tipoMovimiento);
        movimiento.setValor(request.valor);

        return ResponseEntity.ok(
                crearMovimientoUseCase.crear(movimiento)
        );
    }


}
