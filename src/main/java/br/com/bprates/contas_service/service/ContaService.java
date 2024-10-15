package br.com.bprates.contas_service.service;

import br.com.bprates.contas_service.model.Conta;
import br.com.bprates.contas_service.repository.ContaRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta incluir(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> obterLista() {
        return contaRepository.findAll();
    }

    public Conta obterPorId(Integer id) {
        return contaRepository.findById(id).orElse(null);
    }

    public void excluir(Integer id) {
        contaRepository.deleteById(id);
    }
}

