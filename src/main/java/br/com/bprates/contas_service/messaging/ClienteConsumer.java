package br.com.bprates.contas_service.messaging;

import br.com.bprates.contas_service.model.Conta;
import br.com.bprates.contas_service.repository.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ClienteConsumer.class);

    private final ContaRepository contaRepository;

    public ClienteConsumer(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @RabbitListener(queues = "cliente-created")
    public void receberMensagem(String mensagem) {
        logger.info("Mensagem recebida: {}", mensagem);

        // Extrair o ID do cliente da mensagem (ou pode enviar diretamente o ID do cliente)
        String[] partes = mensagem.split(":");
        Integer clienteId = Integer.parseInt(partes[1].trim());

        // Criar a conta com o último número de conta baseado no último ID do banco
        var ultimoId = contaRepository.count() + 1;
        String numeroConta = "ACCT-" + ultimoId;

        Conta novaConta = new Conta(0.0, numeroConta, clienteId);
        contaRepository.save(novaConta);

        logger.info("Conta criada para o cliente ID: {}", clienteId);
    }
}
