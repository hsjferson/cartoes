package com.cartoes.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;

public class TransacaoDto {

    private String id;

    @NotEmpty(message = "CNPJ não pode ser vazio.")
    @Length(min = 14, max = 14, message = "CNPJ precisa ter 14 caracteres.")
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;

    @NotEmpty(message = "Valor não pode ser vazio.")
    @Length(min = 1, max = 10, message = "Valor precisa ser preenchido com no máximo 10 caracteres numéricos.")
    private String valor;

    @NotEmpty(message = "qtdParcelas não pode ser vazio.")
    @Length(min = 0, max = 2, message = "Quantidade de Parcelas precisa ser preenchido com no máximo 2 caracteres numéricos.")
    private String qtdParcelas;

    @NotEmpty(message = "Juros não pode ser vazio.")
    @Length(min = 0, max = 4, message = "Juros precisa ser preenchido com no máximo 4 caracteres numéricos.")
    private String juros;

    @JsonProperty("cartao")
    private String numeroCartao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(String qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public String getJuros() {
        return juros;
    }

    public void setJuros(String juros) {
        this.juros = juros;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public String toString() {
        return "TransacaoDto{" +
                "id='" + id + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", valor='" + valor + '\'' +
                ", qtdParcelas='" + qtdParcelas + '\'' +
                ", juros='" + juros + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                '}';
    }
}
