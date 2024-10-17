package br.com.bprates.contas_service.service;

import br.com.bprates.contas_service.clients.ClientesClient;
import br.com.bprates.contas_service.model.Conta;
import br.com.bprates.contas_service.repository.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.List;

@Service
public class ContaService {

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClientesClient clienteClient;

    public Conta incluir(Conta conta) {

        logger.info("Iniciando processo de criação de conta para cliente ID: {}", conta.getClientID());
        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString(("bprates:infnet2024").getBytes());
        var cliente = clienteClient.obterClientePorId(conta.getClientID(), basicAuthHeader);

        if (cliente != null && cliente.isAtivo()) {
            logger.info("Cliente ID: {} é válido. Criando conta...", conta.getClientID());
            return contaRepository.save(conta);
        } else {
            logger.warn("Cliente ID: {} inválido ou inativo.", conta.getClientID());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inválido ou inativo.");
        }
    }

    public List<Conta> obterLista() {
        logger.info("Obtendo lista de todas as contas");
        return contaRepository.findAll();
    }

    public Conta obterPorId(Integer id) {
        logger.info("Obtendo conta com ID: {}", id);
        return contaRepository.findById(id).orElse(null);
    }

    public void excluir(Integer id) {
        logger.info("Excluindo conta com ID: {}", id);
        contaRepository.deleteById(id);
    }

    public List<Conta> obterContasPorClienteId(Integer clientID) {
        logger.info("Obtendo contas para o cliente com ID: {}", clientID);
        return contaRepository.findByClientID(clientID);
    }
}