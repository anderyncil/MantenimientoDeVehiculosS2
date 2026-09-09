package org.yncil.springcloud.msvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yncil.springcloud.msvc.models.entity.Repuesto;
import org.yncil.springcloud.msvc.services.RepuestoService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    @Autowired
    private RepuestoService service;

    @GetMapping
    public List<Repuesto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Repuesto> repuesto = service.porId(id);

        if (repuesto.isPresent()) {
            return ResponseEntity.ok(repuesto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/por-codigo-oem/{codigoOem}")
    public ResponseEntity<?> porCodigoOem(@PathVariable String codigoOem) {
        Optional<Repuesto> repuesto = service.porCodigoOem(codigoOem);

        if (repuesto.isPresent()) {
            return ResponseEntity.ok(repuesto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(
            @Valid @RequestBody Repuesto repuesto,
            BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        if (service.porCodigoOem(repuesto.getCodigoOem()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap(
                    "mensaje", "Ya existe un repuesto con ese código OEM"
            ));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.guardar(repuesto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @Valid @RequestBody Repuesto repuestoCambio,
            BindingResult result,
            @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Repuesto> repuestoOptional = service.porId(id);

        if (repuestoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Repuesto repuestoBD = repuestoOptional.get();

        if (!repuestoCambio.getCodigoOem().equalsIgnoreCase(repuestoBD.getCodigoOem())
                && service.porCodigoOem(repuestoCambio.getCodigoOem()).isPresent()) {

            return ResponseEntity.badRequest().body(Collections.singletonMap(
                    "mensaje", "Ya existe un repuesto con ese código OEM"
            ));
        }

        repuestoBD.setCodigoOem(repuestoCambio.getCodigoOem());
        repuestoBD.setNombreRepuesto(repuestoCambio.getNombreRepuesto());
        repuestoBD.setPrecioUnitario(repuestoCambio.getPrecioUnitario());
        repuestoBD.setStock(repuestoCambio.getStock());

        if (repuestoCambio.getMovimientos() != null && !repuestoCambio.getMovimientos().isEmpty()) {
            repuestoBD.setMovimientos(repuestoCambio.getMovimientos());
        }

        return ResponseEntity.ok(service.guardar(repuestoBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Repuesto> repuesto = service.porId(id);

        if (repuesto.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(error ->
                errores.put(
                        error.getField(),
                        "El campo " + error.getField() + " " + error.getDefaultMessage()
                )
        );

        return ResponseEntity.badRequest().body(errores);
    }
}
