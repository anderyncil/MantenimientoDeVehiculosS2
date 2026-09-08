package org.yncil.springcloud.msvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yncil.springcloud.msvc.models.entity.OrdenTrabajo;
import org.yncil.springcloud.msvc.services.OrdenTrabajoService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService service;

    @GetMapping
    public List<OrdenTrabajo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<OrdenTrabajo> ordenTrabajo = service.porId(id);

        if (ordenTrabajo.isPresent()) {
            return ResponseEntity.ok(ordenTrabajo.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/por-numero-ot/{numeroOt}")
    public ResponseEntity<?> porNumeroOt(@PathVariable String numeroOt) {
        Optional<OrdenTrabajo> ordenTrabajo = service.porNumeroOt(numeroOt);

        if (ordenTrabajo.isPresent()) {
            return ResponseEntity.ok(ordenTrabajo.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(
            @Valid @RequestBody OrdenTrabajo ordenTrabajo,
            BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        if (service.porNumeroOt(ordenTrabajo.getNumeroOt()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap(
                    "mensaje", "Ya existe una orden de trabajo con ese número"
            ));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.guardar(ordenTrabajo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @Valid @RequestBody OrdenTrabajo ordenTrabajoCambio,
            BindingResult result,
            @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<OrdenTrabajo> ordenOptional = service.porId(id);

        if (ordenOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        OrdenTrabajo ordenBD = ordenOptional.get();

        if (!ordenTrabajoCambio.getNumeroOt().equalsIgnoreCase(ordenBD.getNumeroOt())
                && service.porNumeroOt(ordenTrabajoCambio.getNumeroOt()).isPresent()) {

            return ResponseEntity.badRequest().body(Collections.singletonMap(
                    "mensaje", "Ya existe una orden de trabajo con ese número"
            ));
        }

        ordenBD.setNumeroOt(ordenTrabajoCambio.getNumeroOt());
        ordenBD.setKilometraje(ordenTrabajoCambio.getKilometraje());
        ordenBD.setClienteId(ordenTrabajoCambio.getClienteId());
        ordenBD.setVehiculoId(ordenTrabajoCambio.getVehiculoId());

        if (ordenTrabajoCambio.getHojaInventario() != null) {
            ordenBD.setHojaInventario(ordenTrabajoCambio.getHojaInventario());
        }

        return ResponseEntity.ok(service.guardar(ordenBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<OrdenTrabajo> ordenTrabajo = service.porId(id);

        if (ordenTrabajo.isPresent()) {
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
