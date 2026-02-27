package com.segundo.micro.cuentasservice.infrastruture.adapter.in.rest;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;
import com.segundo.micro.cuentasservice.domain.port.in.ActualizarCuentaUseCase;
import com.segundo.micro.cuentasservice.domain.port.in.CrearCuentaUseCase;
import com.segundo.micro.cuentasservice.domain.port.in.ListarCuentaUseCase;
import com.segundo.micro.cuentasservice.domain.port.in.ObtenerCuentaUseCase;
import com.segundo.micro.cuentasservice.infrastruture.adapter.in.dto.ActualizarCuentaRequest;
import com.segundo.micro.cuentasservice.infrastruture.adapter.in.dto.CrearCuentaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Cuenta", description = "API para gestión de cuentas")
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CrearCuentaUseCase crearCuentaUseCase;

    private final ActualizarCuentaUseCase actualizarCuentaUseCase;

    private final ListarCuentaUseCase listarCuentaUseCase;

    private final ObtenerCuentaUseCase obtenerCuentaUseCase;

    public CuentaController(CrearCuentaUseCase crearCuentaUseCase, ActualizarCuentaUseCase actualizarCuentaUseCase, ListarCuentaUseCase listarCuentaUseCase, ObtenerCuentaUseCase obtenerCuentaUseCase) {
        this.crearCuentaUseCase = crearCuentaUseCase;
        this.actualizarCuentaUseCase = actualizarCuentaUseCase;
        this.listarCuentaUseCase = listarCuentaUseCase;
        this.obtenerCuentaUseCase = obtenerCuentaUseCase;
    }

    @Operation(
            summary = "Crear una nueva cuenta",
            description = "Crea una cuenta bancaria asociada a un cliente"
    )
    @ApiResponse(responseCode = "200", description = "Cuenta creada correctamente")
    @PostMapping("/crear")
    public ResponseEntity<Cuenta> crear(
            @Valid @RequestBody CrearCuentaRequest request) {

        Cuenta cuenta = mapToDomain(request);

        return ResponseEntity.ok(
                crearCuentaUseCase.crearCuenta(cuenta)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizar(
            @PathVariable Long id,
            @RequestBody ActualizarCuentaRequest request) {

        Cuenta cuenta = new Cuenta(
                id,
                request.tipoCuenta,
                request.numeroCuenta,
                request.saldoInicial,
                request.saldoActual, // saldoActual lo setea el service
                request.estado,
                null

        );

        return ResponseEntity.ok(actualizarCuentaUseCase.actualizar(cuenta));
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listar() {
        return ResponseEntity.ok(listarCuentaUseCase.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(obtenerCuentaUseCase.obtenerPorId(id));
    }


    private Cuenta mapToDomain(CrearCuentaRequest request) {
        return new Cuenta(
                null,
                request.getTipoCuenta(),
                null,
                request.getSaldoInicial(),
                null, // saldoActual lo setea el service
                request.getEstado(),
                request.getClienteId()
        );

    }
}
