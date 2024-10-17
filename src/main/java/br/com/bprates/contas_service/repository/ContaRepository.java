package br.com.bprates.contas_service.repository;

import br.com.bprates.contas_service.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    List<Conta> findByClientID(Integer clientID);
}
