package com.segundo.micro.cuentasservice.infrastruture.adapter.out.persistence;

import com.segundo.micro.cuentasservice.domain.model.Cuenta;
import com.segundo.micro.cuentasservice.domain.port.out.CuentaRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CuentaRepositoryAdapter implements CuentaRepositoryPort {

    private final SpringDataCuentaRepository repository;

    public CuentaRepositoryAdapter(SpringDataCuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        CuentaEntity entity;

        if(cuenta.getId() == null){
            entity = mapToEntity(cuenta);
        }else{
            entity = repository.findById(cuenta.getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        }

        //Actualiza campos
        entity.setNumeroCuenta(cuenta.getNumeroCuenta());
        entity.setTipoCuenta(cuenta.getTipoCuenta());
        entity.setSaldoActual(cuenta.getSaldoActual());
        entity.setEstado(cuenta.getEstado());
        entity.setSaldoInicial(cuenta.getSaldoInicial());


        CuentaEntity saved = repository.save(entity);

        return mapToDomain(saved);
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public List<Cuenta> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String identificacion) {
        return repository.findByNumeroCuenta(identificacion)
                .map(this::mapToDomain);
    }

    @Override
    public List<Cuenta> findByClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId)
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    private CuentaEntity mapToEntity(Cuenta cuenta) {
        CuentaEntity entity = new CuentaEntity();
        entity.setId(cuenta.getId());
        entity.setTipoCuenta(cuenta.getTipoCuenta());
        entity.setNumeroCuenta(cuenta.getNumeroCuenta());
        entity.setSaldoInicial(cuenta.getSaldoInicial());

        entity.setSaldoActual(cuenta.getSaldoActual());
        entity.setEstado(cuenta.getEstado());
        entity.setClienteId(cuenta.getClienteId());
        return entity;
    }

    private Cuenta mapToDomain(CuentaEntity entity) {
        return new Cuenta(
                entity.getId(),
                entity.getTipoCuenta(),
                entity.getNumeroCuenta(),
                entity.getSaldoInicial(),
                entity.getSaldoActual(),
                entity.getEstado(),
                entity.getClienteId()
        );

    }
}
