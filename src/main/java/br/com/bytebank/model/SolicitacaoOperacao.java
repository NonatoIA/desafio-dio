package br.com.bytebank.model;

import java.math.BigDecimal;

/**
 * Dados de uma tentativa de operação, que percorrem a cadeia de validadores.
 */
public record SolicitacaoOperacao(Conta conta, String tipo, BigDecimal valor) {
}
