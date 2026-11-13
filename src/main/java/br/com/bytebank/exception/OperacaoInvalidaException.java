package br.com.bytebank.exception;

public class OperacaoInvalidaException extends OperacaoBancariaException {

    public OperacaoInvalidaException() {
        super("operacao invalida");
    }
}
