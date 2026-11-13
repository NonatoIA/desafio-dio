package br.com.bytebank.chain;

import br.com.bytebank.exception.OperacaoInvalidaException;
import br.com.bytebank.model.SolicitacaoOperacao;
import br.com.bytebank.strategy.CatalogoOperacoes;

public class ValidadorTipoOperacao extends Validador {

    private final CatalogoOperacoes catalogo;

    public ValidadorTipoOperacao(CatalogoOperacoes catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    protected void verificar(SolicitacaoOperacao solicitacao) {
        if (catalogo.buscar(solicitacao.tipo()).isEmpty()) {
            throw new OperacaoInvalidaException();
        }
    }
}
