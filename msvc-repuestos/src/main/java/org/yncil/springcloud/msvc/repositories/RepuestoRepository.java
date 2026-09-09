package org.yncil.springcloud.msvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.yncil.springcloud.msvc.models.entity.Repuesto;

import java.util.Optional;

public interface RepuestoRepository extends CrudRepository<Repuesto, Long> {

    Optional<Repuesto> findByCodigoOem(String codigoOem);
}
