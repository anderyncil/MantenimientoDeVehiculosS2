package org.yncil.springcloud.msvc.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.yncil.springcloud.msvc.models.OrdenTrabajo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String fallaReportada;

    @NotEmpty
    private String diagnosticoTecnico;

    @NotNull
    @Positive
    private BigDecimal costoEstimado;

    @NotEmpty
    private String estadoEjecucion; // PENDIENTE, EN_PROCESO, FINALIZADO

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "diagnostico_id")
    private List<DiagnosticoOrdenTrabajo> diagnosticosOrdenesTrabajo;

    @Transient
    private List<OrdenTrabajo> ordenesTrabajo;

    public Diagnostico() {
        diagnosticosOrdenesTrabajo = new ArrayList<>();
        ordenesTrabajo = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFallaReportada() {
        return fallaReportada;
    }

    public void setFallaReportada(String fallaReportada) {
        this.fallaReportada = fallaReportada;
    }

    public String getDiagnosticoTecnico() {
        return diagnosticoTecnico;
    }

    public void setDiagnosticoTecnico(String diagnosticoTecnico) {
        this.diagnosticoTecnico = diagnosticoTecnico;
    }

    public BigDecimal getCostoEstimado() {
        return costoEstimado;
    }

    public void setCostoEstimado(BigDecimal costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public String getEstadoEjecucion() {
        return estadoEjecucion;
    }

    public void setEstadoEjecucion(String estadoEjecucion) {
        this.estadoEjecucion = estadoEjecucion;
    }

    public List<DiagnosticoOrdenTrabajo> getDiagnosticosOrdenesTrabajo() {
        return diagnosticosOrdenesTrabajo;
    }

    public void setDiagnosticosOrdenesTrabajo(List<DiagnosticoOrdenTrabajo> diagnosticosOrdenesTrabajo) {
        this.diagnosticosOrdenesTrabajo = diagnosticosOrdenesTrabajo;
    }

    public List<OrdenTrabajo> getOrdenesTrabajo() {
        return ordenesTrabajo;
    }

    public void setOrdenesTrabajo(List<OrdenTrabajo> ordenesTrabajo) {
        this.ordenesTrabajo = ordenesTrabajo;
    }

    public void addDiagnosticoOrdenTrabajo(DiagnosticoOrdenTrabajo diagnosticoOrdenTrabajo) {
        diagnosticosOrdenesTrabajo.add(diagnosticoOrdenTrabajo);
    }

    public void removeDiagnosticoOrdenTrabajo(DiagnosticoOrdenTrabajo diagnosticoOrdenTrabajo) {
        diagnosticosOrdenesTrabajo.remove(diagnosticoOrdenTrabajo);
    }
}