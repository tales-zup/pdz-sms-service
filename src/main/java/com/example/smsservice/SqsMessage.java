package com.example.smsservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SqsMessage {

    private BigDecimal valor;

    private Long idLoja;

    private List<String> produtos;

    public SqsMessage() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public List<String> getProdutos() {
        return produtos;
    }
}
