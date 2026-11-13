package br.com.bytebank.singleton;

import br.com.bytebank.model.Transacao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Singleton: existe um único histórico de transações compartilhado por toda a aplicação.
 * Usa o idiom "Holder" (inicialização preguiçosa e thread-safe, garantida pela JVM).
 */
public final class HistoricoTransacoes {

    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    private HistoricoTransacoes() {
    }

    private static final class Holder {
        private static final HistoricoTransacoes INSTANCIA = new HistoricoTransacoes();
    }

    public static HistoricoTransacoes getInstancia() {
        return Holder.INSTANCIA;
    }

    public void registrar(Transacao transacao) {
        transacoes.add(transacao);
    }

    public List<Transacao> listar() {
        return List.copyOf(transacoes);
    }

    public long totalComSucesso() {
        return transacoes.stream().filter(Transacao::sucesso).count();
    }

    public long totalComFalha() {
        return transacoes.stream().filter(t -> !t.sucesso()).count();
    }
}
