package org.unc.springcloud.msvc.mantenimiento.Models.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "tareas_diagnosticos")

public class TareaDiagnostico {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long diagnosticoId;

    public TareaDiagnostico(){}

    public TareaDiagnostico(Long diagnosticoId) {
        this.diagnosticoId = diagnosticoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiagnosticoId() {
        return diagnosticoId;
    }

    public void setDiagnosticoId(Long diagnosticoId) {
        this.diagnosticoId = diagnosticoId;
    }
}
