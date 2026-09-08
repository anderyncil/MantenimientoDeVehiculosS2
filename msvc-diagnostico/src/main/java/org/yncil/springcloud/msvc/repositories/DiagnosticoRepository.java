package org.yncil.springcloud.msvc.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.yncil.springcloud.msvc.models.entity.Diagnostico;

import java.util.List;

public interface DiagnosticoRepository extends CrudRepository<Diagnostico, Long> {

    @Query("""
            select d
            from Diagnostico d
            join d.diagnosticosOrdenesTrabajo dot
            where dot.ordenTrabajoId = ?1
            """)
    List<Diagnostico> findDiagnosticosPorOrdenTrabajoId(Long ordenTrabajoId);
}