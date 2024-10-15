package br.com.bprates.contas_service.controller;

import br.com.bprates.contas_service.model.TipoTransacao;
import br.com.bprates.contas_service.model.Transacao;
import br.com.bprates.contas_service.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping(value = "listar")
    public Iterable<Transacao> obterLista(){
        return transacaoService.obterLista();
    }

    @GetMapping("/listar/order")
    public List<Transacao> listarTransacoes(@RequestParam(defaultValue = "valor") String ordenacao,
                                            @RequestParam(defaultValue = "asc") String direcao) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direcao) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return transacaoService.obterListaOrdenada(Sort.by(sortDirection, ordenacao));
    }

    @GetMapping("/buscar/tipo")
    public List<Transacao> buscarPorTipo(@RequestParam TipoTransacao tipo) {
        return transacaoService.buscarPorTipo(tipo);
    }

    @GetMapping(value = "{id}")
    public Transacao obterPorId(@PathVariable Integer id) {
        return transacaoService.obterPorId(id);
    }

    @GetMapping("/buscar/conta/{contaId}")
    public List<Transacao> buscarPorContaId(@PathVariable Integer contaId) {
        return transacaoService.buscarPorContaId(contaId);
    }

    @GetMapping("/buscar/valor")
    public List<Transacao> buscarPorValorMaiorQue(@RequestParam Double valor) {
        return transacaoService.buscarPorValorMaiorQue(valor);
    }

    @PostMapping(value = "incluir")
    public String incluir(@RequestBody Transacao conta) {

        transacaoService.incluir(conta);

        return "A inclusão da " + conta + " foi realizada com sucesso!!!";
    }

    @DeleteMapping(value = "{id}")
    public String excluir(@PathVariable Integer id) {
        transacaoService.excluir(id);
        return "A exclusão do id " + id + " foi realizada com sucesso!!!";
    }
}
