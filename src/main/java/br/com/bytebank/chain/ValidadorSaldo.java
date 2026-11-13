package br.com.bytebank.chain;

import br.com.bytebank.exception.OperacaoInvalidaException;
import br.com.bytebank.exception.SaldoInsuficienteException;
import br.com.bytebank.model.SolicitacaoOperacao;
import br.com.bytebank.strategy.CatalogoOperacoes;
import br.com.bytebank.strategy.Operacao;

public class ValidadorSaldo extends Validador {

    private final CatalogoOperacoes catalogo;

    public ValidadorSaldo(CatalogoOperacoes catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    protected void verificar(SolicitacaoOperacao solicitacao) {
        Operacao operacao = catalogo.buscar(solicitacao.tipo())
                .orElseThrow(OperacaoInvalidaException::new);

        boolean semSaldo = solicitacao.valor().compareTo(solicitacao.conta().getSaldo()) > 0;
        if (operacao.exigeSaldo() && semSaldo) {
            throw new SaldoInsuficienteException();
        }
    }
}
