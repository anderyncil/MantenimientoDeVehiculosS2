package org.unc.springcloud.msvc.mantenimiento.Models.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "asignaciones_taller")
public class AsignacionTaller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String mecanicoAsignado;
    private String bahiaTrabajo;
    private LocalDateTime fechaAsignacion;

    public AsignacionTaller(){
        this.fechaAsignacion= LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMecanicoAsignado() {
        return mecanicoAsignado;
    }

    public void setMecanicoAsignado(String mecanicoAsignado) {
        this.mecanicoAsignado = mecanicoAsignado;
    }

    public String getBahiaTrabajo() {
        return bahiaTrabajo;
    }

    public void setBahiaTrabajo(String bahiaTrabajo) {
        this.bahiaTrabajo = bahiaTrabajo;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}
