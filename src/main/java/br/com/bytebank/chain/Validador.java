package br.com.bytebank.chain;

import br.com.bytebank.model.SolicitacaoOperacao;

/**
 * Chain of Responsibility: cada validador faz sua verificação e,
 * se tudo estiver certo, repassa a solicitação para o próximo da cadeia.
 * Se encontrar um problema, lança uma exceção e interrompe a cadeia.
 */
public abstract class Validador {

    private Validador proximo;

    /**
     * Define o próximo validador e o devolve, permitindo encadear chamadas:
     * a.encadear(b).encadear(c)
     */
    public Validador encadear(Validador proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public final void validar(SolicitacaoOperacao solicitacao) {
        verificar(solicitacao);
        if (proximo != null) {
            proximo.validar(solicitacao);
        }
    }

    protected abstract void verificar(SolicitacaoOperacao solicitacao);
}
