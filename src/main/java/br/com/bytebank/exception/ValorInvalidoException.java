package br.com.bytebank.exception;

public class ValorInvalidoException extends OperacaoBancariaException {

    public ValorInvalidoException() {
        super("valor invalido");
    }
}
