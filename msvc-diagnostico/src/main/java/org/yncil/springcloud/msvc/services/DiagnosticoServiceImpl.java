package org.yncil.springcloud.msvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yncil.springcloud.msvc.clients.OrdenTrabajoClientRest;
import org.yncil.springcloud.msvc.models.OrdenTrabajo;
import org.yncil.springcloud.msvc.models.entity.Diagnostico;
import org.yncil.springcloud.msvc.models.entity.DiagnosticoOrdenTrabajo;
import org.yncil.springcloud.msvc.repositories.DiagnosticoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    @Autowired
    private DiagnosticoRepository repository;

    @Autowired
    private OrdenTrabajoClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Diagnostico> listar() {
        return (List<Diagnostico>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Diagnostico> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Diagnostico guardar(Diagnostico diagnostico) {
        return repository.save(diagnostico);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<OrdenTrabajo> asignarOrdenTrabajo(
            OrdenTrabajo ordenTrabajo, Long diagnosticoId) {

        Optional<Diagnostico> diagnosticoOptional = repository.findById(diagnosticoId);

        if (diagnosticoOptional.isPresent()) {
            OrdenTrabajo ordenMsvc = client.detalle(ordenTrabajo.getId());

            Diagnostico diagnostico = diagnosticoOptional.get();
            DiagnosticoOrdenTrabajo relacion = new DiagnosticoOrdenTrabajo();
            relacion.setOrdenTrabajoId(ordenMsvc.getId());

            diagnostico.addDiagnosticoOrdenTrabajo(relacion);
            repository.save(diagnostico);

            return Optional.of(ordenMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<OrdenTrabajo> crearOrdenTrabajo(
            OrdenTrabajo ordenTrabajo, Long diagnosticoId) {

        Optional<Diagnostico> diagnosticoOptional = repository.findById(diagnosticoId);

        if (diagnosticoOptional.isPresent()) {
            OrdenTrabajo ordenMsvc = client.crear(ordenTrabajo);

            Diagnostico diagnostico = diagnosticoOptional.get();
            DiagnosticoOrdenTrabajo relacion = new DiagnosticoOrdenTrabajo();
            relacion.setOrdenTrabajoId(ordenMsvc.getId());

            diagnostico.addDiagnosticoOrdenTrabajo(relacion);
            repository.save(diagnostico);

            return Optional.of(ordenMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<OrdenTrabajo> desasignarOrdenTrabajo(
            OrdenTrabajo ordenTrabajo, Long diagnosticoId) {

        Optional<Diagnostico> diagnosticoOptional = repository.findById(diagnosticoId);

        if (diagnosticoOptional.isPresent()) {
            OrdenTrabajo ordenMsvc = client.detalle(ordenTrabajo.getId());

            Diagnostico diagnostico = diagnosticoOptional.get();
            DiagnosticoOrdenTrabajo relacion = new DiagnosticoOrdenTrabajo();
            relacion.setOrdenTrabajoId(ordenMsvc.getId());

            diagnostico.removeDiagnosticoOrdenTrabajo(relacion);
            repository.save(diagnostico);

            return Optional.of(ordenMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diagnostico> listarPorNumeroOt(String numeroOt) {
        OrdenTrabajo ordenTrabajo = client.porNumeroOt(numeroOt);

        return repository.findDiagnosticosPorOrdenTrabajoId(ordenTrabajo.getId());
    }
}