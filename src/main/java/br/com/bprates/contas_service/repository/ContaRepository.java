package br.com.bprates.contas_service.repository;

import br.com.bprates.contas_service.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
