package org.yncil.springcloud.msvc.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnosticos_ordenes_trabajo")
public class DiagnosticoOrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_trabajo_id")
    private Long ordenTrabajoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdenTrabajoId() {
        return ordenTrabajoId;
    }

    public void setOrdenTrabajoId(Long ordenTrabajoId) {
        this.ordenTrabajoId = ordenTrabajoId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiagnosticoOrdenTrabajo)) {
            return false;
        }

        DiagnosticoOrdenTrabajo otro = (DiagnosticoOrdenTrabajo) obj;

        return ordenTrabajoId != null && ordenTrabajoId.equals(otro.ordenTrabajoId);
    }
}