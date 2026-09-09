package org.unc.springcloud.msvc.mantenimiento.Services;
import org.unc.springcloud.msvc.mantenimiento.Models.Entity.TareaMantenimiento;
import java.util.List;
import java.util.Optional;

public interface TareaMantenimientoService {
    List<TareaMantenimiento> listar();
    Optional<TareaMantenimiento>porId(Long id);
    TareaMantenimiento guardar(TareaMantenimiento tarea);
    Optional<TareaMantenimiento>asignarDiagnostico(Long tareaId, Long diagnosticoId);
    Optional<TareaMantenimiento> detalleConDiagnostico(Long id);
}
