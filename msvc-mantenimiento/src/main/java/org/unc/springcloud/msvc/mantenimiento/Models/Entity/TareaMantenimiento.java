package org.unc.springcloud.msvc.mantenimiento.Models.Entity;

import org.unc.springcloud.msvc.mantenimiento.Models.InformeDiagnostico;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tareas_mantenimiento")
public class TareaMantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descripcionTarea;
    private String estadoTarea;
    private LocalDateTime FechaProgramada;

   @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "tarea_mantenimiento_id")
    private List<TareaDiagnostico> tareaDiagnosticos = new ArrayList<>();

   @Transient
    private List<InformeDiagnostico> diagnosticos = new ArrayList<>();

    public void addTareaDiagnostico(TareaDiagnostico td){
        this.tareaDiagnosticos.add(td);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public LocalDateTime getFechaProgramada() {
        return FechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        FechaProgramada = fechaProgramada;
    }

    public List<TareaDiagnostico> getTareaDiagnosticos() {
        return tareaDiagnosticos;
    }

    public void setTareaDiagnosticos(List<TareaDiagnostico> tareaDiagnosticos) {
        this.tareaDiagnosticos = tareaDiagnosticos;
    }

    public List<InformeDiagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<InformeDiagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
}
