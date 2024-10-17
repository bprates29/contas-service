package br.com.bprates.contas_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Conta deve ter um cliente vinculado")
    private Integer clientID;

    @NotNull(message = "Toda conta deve inicializar com um valor")
    private Double saldo;

    @NotBlank(message = "O número da conta não pode estar vazio")
    private String numeroConta;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transacao> transacoes = new ArrayList<>();

    public Conta() {}

    public Conta(Double saldo, String numeroConta, Integer clientID) {
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.clientID = clientID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", saldo=" + saldo +
                ", numeroConta='" + numeroConta + '\'' +
                ", id do cliente='" + clientID + '\'' +
                ", transacoes=" + (transacoes != null ? transacoes.size() : 0) +
                '}';
    }
}
