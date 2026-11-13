package br.com.bytebank.exception;

/**
 * Exceção base (unchecked) para as regras de negócio do ByteBank.
 */
public class OperacaoBancariaException extends RuntimeException {

    public OperacaoBancariaException(String mensagem) {
        super(mensagem);
    }
}
