package br.com.bytebank.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Representa uma conta digital do ByteBank.
 * As regras de negócio (validações) ficam na cadeia de validadores,
 * e a conta apenas credita ou debita valores.
 */
public class Conta {

    private final String titular;
    private BigDecimal saldo;

    public Conta(String titular, BigDecimal saldoInicial) {
        this.titular = Objects.requireNonNull(titular, "O titular é obrigatório");
        this.saldo = Objects.requireNonNull(saldoInicial, "O saldo inicial é obrigatório");
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    public void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }
}
