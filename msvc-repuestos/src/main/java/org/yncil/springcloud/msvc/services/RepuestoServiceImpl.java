package org.yncil.springcloud.msvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yncil.springcloud.msvc.models.entity.Repuesto;
import org.yncil.springcloud.msvc.repositories.RepuestoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoServiceImpl implements RepuestoService {

    @Autowired
    private RepuestoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Repuesto> listar() {
        return (List<Repuesto>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Repuesto> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Repuesto guardar(Repuesto repuesto) {
        return repository.save(repuesto);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Repuesto> porCodigoOem(String codigoOem) {
        return repository.findByCodigoOem(codigoOem);
    }
}
