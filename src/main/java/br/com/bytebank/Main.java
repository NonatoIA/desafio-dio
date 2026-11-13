package br.com.bytebank;

import br.com.bytebank.facade.ByteBankFacade;
import br.com.bytebank.model.Conta;
import br.com.bytebank.singleton.HistoricoTransacoes;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        ByteBankFacade bytebank = new ByteBankFacade();

        System.out.println("=== Operacoes (Facade + Strategy + Chain of Responsibility) ===");
        executar(bytebank, "Ana", "DEPOSITO", "100", "50");
        executar(bytebank, "Bruno", "SAQUE", "80", "30");
        executar(bytebank, "Carla", "SAQUE", "20", "40");
        executar(bytebank, "Diego", "PIX", "100", "10");
        executar(bytebank, "Elisa", "DEPOSITO", "100", "0");

        HistoricoTransacoes historico = HistoricoTransacoes.getInstancia();

        System.out.println();
        System.out.println("=== Historico de transacoes (Singleton) ===");
        historico.listar().forEach(t -> System.out.println(t.resumo()));

        System.out.println();
        System.out.printf("Sucesso: %d | Falha: %d%n", historico.totalComSucesso(), historico.totalComFalha());
    }

    private static void executar(ByteBankFacade bytebank, String titular,
                                 String tipo, String saldoInicial, String valor) {
        Conta conta = new Conta(titular, new BigDecimal(saldoInicial));
        String resultado = bytebank.processar(conta, tipo, new BigDecimal(valor));
        System.out.printf("%-6s %-9s saldo inicial: %-4s valor: %-4s -> %s%n",
                titular, tipo, saldoInicial, valor, resultado);
    }
}
