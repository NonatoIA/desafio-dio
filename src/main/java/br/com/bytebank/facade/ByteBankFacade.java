package br.com.bytebank.facade;

import br.com.bytebank.chain.Validador;
import br.com.bytebank.chain.ValidadorSaldo;
import br.com.bytebank.chain.ValidadorTipoOperacao;
import br.com.bytebank.chain.ValidadorValor;
import br.com.bytebank.exception.OperacaoBancariaException;
import br.com.bytebank.exception.OperacaoInvalidaException;
import br.com.bytebank.model.Conta;
import br.com.bytebank.model.SolicitacaoOperacao;
import br.com.bytebank.model.Transacao;
import br.com.bytebank.singleton.HistoricoTransacoes;
import br.com.bytebank.strategy.CatalogoOperacoes;
import br.com.bytebank.strategy.Operacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Facade: um único ponto de entrada simples que esconde a interação
 * entre as estratégias, a cadeia de validadores e o histórico.
 */
public class ByteBankFacade {

    private final CatalogoOperacoes catalogo;
    private final Validador validacoes;
    private final HistoricoTransacoes historico = HistoricoTransacoes.getInstancia();

    public ByteBankFacade() {
        this(CatalogoOperacoes.padrao());
    }

    public ByteBankFacade(CatalogoOperacoes catalogo) {
        this.catalogo = catalogo;

        // Ordem da cadeia: tipo -> valor -> saldo
        Validador primeiro = new ValidadorTipoOperacao(catalogo);
        primeiro.encadear(new ValidadorValor())
                .encadear(new ValidadorSaldo(catalogo));
        this.validacoes = primeiro;
    }

    public String processar(Conta conta, String tipo, BigDecimal valor) {
        try {
            validacoes.validar(new SolicitacaoOperacao(conta, tipo, valor));

            Operacao operacao = catalogo.buscar(tipo).orElseThrow(OperacaoInvalidaException::new);
            operacao.executar(conta, valor);

            String resultado = "Saldo final: " + conta.getSaldo().toPlainString();
            registrar(conta, operacao.nome(), valor, true, resultado);
            return resultado;

        } catch (OperacaoBancariaException e) {
            String resultado = "Erro: " + e.getMessage();
            registrar(conta, tipo, valor, false, resultado);
            return resultado;
        }
    }

    private void registrar(Conta conta, String tipo, BigDecimal valor, boolean sucesso, String resultado) {
        historico.registrar(new Transacao(LocalDateTime.now(), conta.getTitular(), tipo, valor, sucesso, resultado));
    }
}
