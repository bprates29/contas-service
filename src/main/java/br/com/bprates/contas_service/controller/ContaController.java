package br.com.bprates.contas_service.controller;

import br.com.bprates.contas_service.model.Conta;
import br.com.bprates.contas_service.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping(value = "listar")
    public Iterable<Conta> obterLista(){
        return contaService.obterLista();
    }

    @GetMapping(value = "{id}")
    public Conta obterPorId(@PathVariable Integer id) {
        return contaService.obterPorId(id);
    }

    @GetMapping(value = "cliente/{id}")
    public List<Conta> obterContaPorClienteId(@PathVariable Integer id) {
        return contaService.obterContasPorClienteId(id);
    }

    @PostMapping(value = "incluir")
    public String incluir(@RequestBody Conta conta) {

        contaService.incluir(conta);

        return "A inclusão da " + conta + " foi realizada com sucesso!!!";
    }

    @DeleteMapping(value = "{id}")
    public String excluir(@PathVariable Integer id) {
        contaService.excluir(id);
        return "A exclusão do id " + id + " foi realizada com sucesso!!!";
    }
}
