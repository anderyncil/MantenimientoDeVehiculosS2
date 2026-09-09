package org.unc.springcloud.msvc.mantenimiento.Services;

import org.unc.springcloud.msvc.mantenimiento.Clientes.DiagnosticoClientRest;
import org.unc.springcloud.msvc.mantenimiento.Models.InformeDiagnostico;
import org.unc.springcloud.msvc.mantenimiento.Models.Entity.TareaDiagnostico;
import org.unc.springcloud.msvc.mantenimiento.Models.Entity.TareaMantenimiento;
import org.unc.springcloud.msvc.mantenimiento.Repositories.TareaMantenimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareaMantenimientoServiceImpl implements TareaMantenimientoService{

    @Autowired
    private TareaMantenimientoRepository repository;

    @Autowired
    private DiagnosticoClientRest clientRest;

    @Override
    @Transactional(readOnly = true)
    public List<TareaMantenimiento> listar(){
        return (List<TareaMantenimiento>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TareaMantenimiento> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public TareaMantenimiento guardar(TareaMantenimiento tarea){
        return repository.save(tarea);
    }


    @Override
    @Transactional
    public Optional<TareaMantenimiento> asignarDiagnostico(Long tareaId, Long diagnsticoId){
        Optional <TareaMantenimiento> opt = repository.findById(tareaId);
        if(opt.isPresent()){
            InformeDiagnostico diagnosticoRemoto = clientRest.obtenerDiagnosticoPorId(diagnsticoId);
            if(diagnosticoRemoto !=null){
                TareaMantenimiento tarea = opt.get();
                TareaDiagnostico tareaDiagnostico = new TareaDiagnostico(diagnsticoId);
                tarea.addTareaDiagnostico(tareaDiagnostico);
                return Optional.of(repository.save(tarea));
            }
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TareaMantenimiento> detalleConDiagnostico(Long id){
        Optional<TareaMantenimiento> opt = repository.findById(id);
        if (opt.isPresent()){
            TareaMantenimiento tarea = opt.get();
            if (!tarea.getTareaDiagnosticos().isEmpty()){
                List<InformeDiagnostico> lista = tarea.getTareaDiagnosticos().stream()
                        .map(td -> clientRest.obtenerDiagnosticoPorId(td.getDiagnosticoId()))
                        .toList();
                tarea.setDiagnosticos(lista);
            }
            return Optional.of(tarea);
        }
        return  Optional.empty();
    }
}
