package org.yncil.springcloud.msvc.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "repuestos")
public class Repuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String codigoOem;

    @NotEmpty
    private String nombreRepuesto;

    @NotNull
    @PositiveOrZero
    private BigDecimal precioUnitario;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "repuesto_id")
    private List<MovimientoAlmacen> movimientos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoOem() {
        return codigoOem;
    }

    public void setCodigoOem(String codigoOem) {
        this.codigoOem = codigoOem;
    }

    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<MovimientoAlmacen> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoAlmacen> movimientos) {
        this.movimientos = movimientos;
    }
}
