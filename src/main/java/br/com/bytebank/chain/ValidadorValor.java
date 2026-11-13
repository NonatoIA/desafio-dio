package br.com.bytebank.chain;

import br.com.bytebank.exception.ValorInvalidoException;
import br.com.bytebank.model.SolicitacaoOperacao;

import java.math.BigDecimal;

public class ValidadorValor extends Validador {

    @Override
    protected void verificar(SolicitacaoOperacao solicitacao) {
        BigDecimal valor = solicitacao.valor();
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException();
        }
    }
}
