package org.yncil.springcloud.msvc.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "hojas_inventario_recepcion")
public class HojaInventarioRecepcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nivelCombustible;

    private String rayonesObservados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivelCombustible() {
        return nivelCombustible;
    }

    public void setNivelCombustible(String nivelCombustible) {
        this.nivelCombustible = nivelCombustible;
    }

    public String getRayonesObservados() {
        return rayonesObservados;
    }

    public void setRayonesObservados(String rayonesObservados) {
        this.rayonesObservados = rayonesObservados;
    }
}