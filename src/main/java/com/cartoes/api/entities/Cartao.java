package com.cartoes.api.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero", nullable = false, length = 16, unique = true)
    private String numero;

    @Column(name = "data_validade", nullable = false)
    private Date dataValidade;

    @Column(name = "bloqueado", nullable = false)
    private boolean bloqueado;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        dataAtualizacao = new Date();
    }

    @Override
    public String toString() {
        return "Cartao[" + "id=" + id + ","
                + "numero=" + numero + ","
                + "dataValidade=" + dataValidade + ","
                + "bloqueado=" + bloqueado + ","
                + "dataAtualizacao=" + dataAtualizacao + ","
                + "cliente=" + cliente + "]";
    }
}
