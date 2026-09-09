package org.unc.springcloud.msvc.mantenimiento.Controllers;

import org.unc.springcloud.msvc.mantenimiento.Services.TareaMantenimientoService;
import org.unc.springcloud.msvc.mantenimiento.Models.Entity.TareaMantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mantenimiento")
public class TareaMantenimientoController {

    @Autowired
    private TareaMantenimientoService service;

    @GetMapping
    public List<TareaMantenimiento>listar(){
       return service.listar();
    }

    @PostMapping
    public ResponseEntity<TareaMantenimiento> crear(@RequestBody TareaMantenimiento tarea){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(tarea));
    }

    @PostMapping ("/asignar-diagnostico/{tareaId}")
    public ResponseEntity<?> asignarDiagnostico(@PathVariable Long tareaId, @RequestParam Long diagnosticoId){
        Optional<TareaMantenimiento> o= service.asignarDiagnostico(tareaId,diagnosticoId);
        if (o.isPresent()){
            return ResponseEntity.status((HttpStatus.CREATED)).body(o.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la tarea o el diagnostico remoto");

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle (@PathVariable Long id){
        Optional<TareaMantenimiento> o = service.detalleConDiagnostico(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }
}
