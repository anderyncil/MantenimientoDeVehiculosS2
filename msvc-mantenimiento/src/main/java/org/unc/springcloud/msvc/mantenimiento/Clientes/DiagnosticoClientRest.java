package org.unc.springcloud.msvc.mantenimiento.Clientes;

import org.unc.springcloud.msvc.mantenimiento.Models.InformeDiagnostico;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-diagnosticos", url = "localhost:8002/api/diagnosticos")
public interface DiagnosticoClientRest {
    @GetMapping("/{id}")
    InformeDiagnostico obtenerDiagnosticoPorId(@PathVariable("id")Long id);



}
