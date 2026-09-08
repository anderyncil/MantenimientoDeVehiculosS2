package org.yncil.springcloud.msvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yncil.springcloud.msvc.models.entity.OrdenTrabajo;
import org.yncil.springcloud.msvc.repositories.OrdenTrabajoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenTrabajo> listar() {
        return (List<OrdenTrabajo>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenTrabajo> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public OrdenTrabajo guardar(OrdenTrabajo ordenTrabajo) {
        return repository.save(ordenTrabajo);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenTrabajo> porNumeroOt(String numeroOt) {
        return repository.findByNumeroOt(numeroOt);
    }
}
