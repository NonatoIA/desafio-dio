package br.com.bytebank.strategy;

import br.com.bytebank.model.Conta;

import java.math.BigDecimal;

/**
 * Strategy: cada tipo de operação bancária é uma implementação desta interface.
 * Para criar uma nova operação basta implementar a interface e registrá-la
 * no catálogo, sem alterar o código existente (Open/Closed Principle).
 */
public interface Operacao {

    /** Nome usado para identificar a operação (ex.: DEPOSITO, SAQUE). */
    String nome();

    /** Indica se a operação retira dinheiro da conta e, portanto, exige saldo. */
    boolean exigeSaldo();

    void executar(Conta conta, BigDecimal valor);
}
