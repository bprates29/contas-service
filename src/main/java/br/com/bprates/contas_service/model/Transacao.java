package br.com.bprates.contas_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O valor não pode estar vazio")
    private Double valor;

    @NotNull(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotNull(message = "O tipo de transação deve ser informado")
    @Enumerated(EnumType.STRING) // Armazena o enum como uma string no banco de dados
    private TipoTransacao tipo;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    @JsonBackReference
    private Conta conta;

    public Transacao() {}

    public Transacao(Double valor, String descricao, TipoTransacao tipo, Conta conta) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
        this.conta = conta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", tipo=" + tipo +
                ", conta=" + (conta != null ? conta.getNumeroConta() : "sem conta") +
                '}';
    }
}
