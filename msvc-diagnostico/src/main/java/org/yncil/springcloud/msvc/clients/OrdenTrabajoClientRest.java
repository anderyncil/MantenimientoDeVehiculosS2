package org.yncil.springcloud.msvc.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.yncil.springcloud.msvc.models.OrdenTrabajo;

@FeignClient(
        name = "msvc-recepcion",
        url = "http://localhost:8001/api/ordenes"
)
public interface OrdenTrabajoClientRest {

    @GetMapping("/{id}")
    OrdenTrabajo detalle(@PathVariable("id") Long id);

    @GetMapping("/por-numero-ot/{numeroOt}")
    OrdenTrabajo porNumeroOt(@PathVariable("numeroOt") String numeroOt);

    @PostMapping
    OrdenTrabajo crear(@RequestBody OrdenTrabajo ordenTrabajo);
}