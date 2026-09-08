package org.yncil.springcloud.msvc.controllers;

import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yncil.springcloud.msvc.models.OrdenTrabajo;
import org.yncil.springcloud.msvc.models.entity.Diagnostico;
import org.yncil.springcloud.msvc.services.DiagnosticoService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService service;

    @GetMapping
    public List<Diagnostico> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Diagnostico> diagnostico = service.porId(id);

        if (diagnostico.isPresent()) {
            return ResponseEntity.ok(diagnostico.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(
            @Valid @RequestBody Diagnostico diagnostico,
            BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.guardar(diagnostico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @Valid @RequestBody Diagnostico diagnosticoCambio,
            BindingResult result,
            @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Diagnostico> diagnosticoOptional = service.porId(id);

        if (diagnosticoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Diagnostico diagnosticoBD = diagnosticoOptional.get();
        diagnosticoBD.setFallaReportada(diagnosticoCambio.getFallaReportada());
        diagnosticoBD.setDiagnosticoTecnico(diagnosticoCambio.getDiagnosticoTecnico());
        diagnosticoBD.setCostoEstimado(diagnosticoCambio.getCostoEstimado());
        diagnosticoBD.setEstadoEjecucion(diagnosticoCambio.getEstadoEjecucion());

        return ResponseEntity.ok(service.guardar(diagnosticoBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Diagnostico> diagnostico = service.porId(id);

        if (diagnostico.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignarOrdenTrabajo/{diagnosticoId}")
    public ResponseEntity<?> asignarOrdenTrabajo(
            @RequestBody OrdenTrabajo ordenTrabajo,
            @PathVariable Long diagnosticoId) {

        try {
            Optional<OrdenTrabajo> ordenOptional =
                    service.asignarOrdenTrabajo(ordenTrabajo, diagnosticoId);

            if (ordenOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(ordenOptional.get());
            }

            return ResponseEntity.notFound().build();

        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap(
                            "mensaje",
                            "No existe la orden de trabajo o hay un error de comunicación"
                    ));
        }
    }

    @PostMapping("/crearOrdenTrabajo/{diagnosticoId}")
    public ResponseEntity<?> crearOrdenTrabajo(
            @RequestBody OrdenTrabajo ordenTrabajo,
            @PathVariable Long diagnosticoId) {

        try {
            Optional<OrdenTrabajo> ordenOptional =
                    service.crearOrdenTrabajo(ordenTrabajo, diagnosticoId);

            if (ordenOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(ordenOptional.get());
            }

            return ResponseEntity.notFound().build();

        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap(
                            "mensaje",
                            "No se pudo crear la orden de trabajo o hay un error de comunicación"
                    ));
        }
    }

    @DeleteMapping("/desasignarOrdenTrabajo/{diagnosticoId}")
    public ResponseEntity<?> desasignarOrdenTrabajo(
            @RequestBody OrdenTrabajo ordenTrabajo,
            @PathVariable Long diagnosticoId) {

        try {
            Optional<OrdenTrabajo> ordenOptional =
                    service.desasignarOrdenTrabajo(ordenTrabajo, diagnosticoId);

            if (ordenOptional.isPresent()) {
                return ResponseEntity.ok(ordenOptional.get());
            }

            return ResponseEntity.notFound().build();

        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap(
                            "mensaje",
                            "No existe la orden de trabajo o hay un error de comunicación"
                    ));
        }
    }

    @GetMapping("/por-numero-ot/{numeroOt}")
    public ResponseEntity<?> listarPorNumeroOt(@PathVariable String numeroOt) {
        try {
            return ResponseEntity.ok(service.listarPorNumeroOt(numeroOt));

        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap(
                            "mensaje",
                            "No existe una orden de trabajo con ese número"
                    ));
        }
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