package org.unc.springcloud.msvc.mantenimiento.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unc.springcloud.msvc.mantenimiento.Models.Entity.TareaMantenimiento;

public interface TareaMantenimientoRepository extends JpaRepository<TareaMantenimiento, Long> {
}
