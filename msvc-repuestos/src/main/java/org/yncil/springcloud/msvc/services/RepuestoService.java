package org.yncil.springcloud.msvc.services;

import org.yncil.springcloud.msvc.models.entity.Repuesto;

import java.util.List;
import java.util.Optional;

public interface RepuestoService {

    List<Repuesto> listar();

    Optional<Repuesto> porId(Long id);

    Repuesto guardar(Repuesto repuesto);

    void eliminar(Long id);

    Optional<Repuesto> porCodigoOem(String codigoOem);
}
