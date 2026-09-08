package org.yncil.springcloud.msvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.yncil.springcloud.msvc.models.entity.OrdenTrabajo;

import java.util.Optional;

public interface OrdenTrabajoRepository extends CrudRepository<OrdenTrabajo, Long> {

    Optional<OrdenTrabajo> findByNumeroOt(String numeroOt);
}
