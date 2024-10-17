package br.com.bprates.contas_service.clients;

import br.com.bprates.contas_service.dtos.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "clientes-service")
public interface ClientesClient {

    @GetMapping("/clientes/{id}")
    ClienteDTO obterClientePorId(
            @PathVariable("id") Integer id,
            @RequestHeader("Authorization") String authorizationHeader
    );
}
