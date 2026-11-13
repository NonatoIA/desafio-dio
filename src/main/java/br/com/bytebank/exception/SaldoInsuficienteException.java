package br.com.bytebank.exception;

public class SaldoInsuficienteException extends OperacaoBancariaException {

    public SaldoInsuficienteException() {
        super("saldo insuficiente");
    }
}
