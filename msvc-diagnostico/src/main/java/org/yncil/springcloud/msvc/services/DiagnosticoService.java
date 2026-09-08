package org.yncil.springcloud.msvc.services;

import org.yncil.springcloud.msvc.models.OrdenTrabajo;
import org.yncil.springcloud.msvc.models.entity.Diagnostico;

import java.util.List;
import java.util.Optional;

public interface DiagnosticoService {

    List<Diagnostico> listar();

    Optional<Diagnostico> porId(Long id);

    Diagnostico guardar(Diagnostico diagnostico);

    void eliminar(Long id);

    Optional<OrdenTrabajo> asignarOrdenTrabajo(OrdenTrabajo ordenTrabajo, Long diagnosticoId);

    Optional<OrdenTrabajo> crearOrdenTrabajo(OrdenTrabajo ordenTrabajo, Long diagnosticoId);

    Optional<OrdenTrabajo> desasignarOrdenTrabajo(OrdenTrabajo ordenTrabajo, Long diagnosticoId);

    List<Diagnostico> listarPorNumeroOt(String numeroOt);
}