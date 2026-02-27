package com.segundo.micro.cuentasservice.infrastruture.adapter.out.persistence;

import com.segundo.micro.cuentasservice.domain.model.Movimiento;
import com.segundo.micro.cuentasservice.domain.port.out.MovimientoRepositoryPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class MovimientoRepositoryAdapter implements MovimientoRepositoryPort {

    private final SpringDataMoviemientoRepository repository;

    private final SpringDataCuentaRepository springDataCuentaRepository;

    public MovimientoRepositoryAdapter(SpringDataMoviemientoRepository repository, SpringDataCuentaRepository springDataCuentaRepository) {
        this.repository = repository;
        this.springDataCuentaRepository = springDataCuentaRepository;
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        MovimientoEntity entity = mapToEntity(movimiento);
        MovimientoEntity saved = repository.save(entity);

        return mapToDomain(saved);
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDomain);
    }


    private MovimientoEntity mapToEntity(Movimiento movimiento) {

        MovimientoEntity entity = new MovimientoEntity();


        entity.setId(movimiento.getId());

        CuentaEntity cuentaEntity = springDataCuentaRepository
                .getReferenceById(movimiento.getCuentaId());

        entity.setCuenta(cuentaEntity);

        entity.setId(movimiento.getId());
        entity.setTipoMovimiento(movimiento.getTipoMovimiento());
        entity.setValor(movimiento.getValor());
        entity.setSaldo(movimiento.getSaldo());
        entity.setFecha(movimiento.getFecha());

        return entity;
    }

    private Movimiento mapToDomain(MovimientoEntity entity) {

        Movimiento movimiento = new Movimiento();

        movimiento.setId(entity.getId());
        movimiento.setCuentaId(entity.getCuenta().getId());
        movimiento.setTipoMovimiento(entity.getTipoMovimiento());
        movimiento.setValor(entity.getValor());
        movimiento.setSaldo(entity.getSaldo());
        movimiento.setFecha(entity.getFecha());

        return movimiento;
    }

    @Override
    public List<Movimiento> findByCuentaId(Long cuentaId) {
        return repository.findByCuentaId(cuentaId)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime inicio, LocalDateTime fin) {
        return repository.findByCuenta_IdAndFechaBetween(cuentaId, inicio, fin)
                .stream()
                .map(this::mapToDomain)
                .toList();
    }
}
