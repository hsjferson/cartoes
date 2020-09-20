package com.cartoes.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transacao")
public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_transacao", nullable = false)
    private Date dataTransacao;

    @Column(name = "cnpj", nullable = false, length = 14, unique = true)
    private String cnpj;

    @Column(name = "valor", nullable = false, length = 10)
    private double valor;

    @Column(name = "qtd_parcelas", nullable = false, length = 2)
    private int qtdParcelas;

    @Column(name = "juros", nullable = false, length = 4)
    private double juros;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Cartao cartao;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @PrePersist
    public void prePersist() {
        dataTransacao = new Date();
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", dataTransacao=" + dataTransacao +
                ", cnpj='" + cnpj + '\'' +
                ", valor=" + valor +
                ", qtdParcelas=" + qtdParcelas +
                ", juros=" + juros +
                ", cartao=" + cartao +
                '}';
    }
}
