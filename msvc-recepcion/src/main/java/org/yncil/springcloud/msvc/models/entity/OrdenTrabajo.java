package org.yncil.springcloud.msvc.models.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Pattern(
            regexp = "^OT-\\d{6}$",
            message = "debe tener el formato OT- seguido de 6 dígitos (ejemplo: OT-100200)"
    )
    @Column(unique = true)
    private String numeroOt;

    private LocalDateTime fechaIngreso;

    @NotNull
    private Integer kilometraje;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long vehiculoId;

    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hoja_inventario_id", referencedColumnName = "id")
    private HojaInventarioRecepcion hojaInventario;

    @PrePersist
    public void prePersist() {
        this.fechaIngreso = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOt() {
        return numeroOt;
    }

    public void setNumeroOt(String numeroOt) {
        this.numeroOt = numeroOt;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public HojaInventarioRecepcion getHojaInventario() {
        return hojaInventario;
    }

    public void setHojaInventario(HojaInventarioRecepcion hojaInventario) {
        this.hojaInventario = hojaInventario;
    }
}