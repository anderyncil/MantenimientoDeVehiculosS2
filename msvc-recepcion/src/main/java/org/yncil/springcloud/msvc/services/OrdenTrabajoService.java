package org.yncil.springcloud.msvc.services;

import org.yncil.springcloud.msvc.models.entity.OrdenTrabajo;

import java.util.List;
import java.util.Optional;

public interface OrdenTrabajoService {

    List<OrdenTrabajo> listar();

    Optional<OrdenTrabajo> porId(Long id);

    OrdenTrabajo guardar(OrdenTrabajo ordenTrabajo);

    void eliminar(Long id);

    Optional<OrdenTrabajo> porNumeroOt(String numeroOt);
}