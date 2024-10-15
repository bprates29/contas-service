package br.com.bprates.contas_service.repository;

import br.com.bprates.contas_service.model.TipoTransacao;
import br.com.bprates.contas_service.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<Transacao> findAll(Sort sort);

    List<Transacao> findByTipo(TipoTransacao tipo);

    List<Transacao> findByContaId(Integer contaId);

    List<Transacao> findByValorGreaterThan(Double valor);
}

