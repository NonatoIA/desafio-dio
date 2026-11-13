package br.com.bytebank.strategy;

import br.com.bytebank.model.Conta;

import java.math.BigDecimal;

public class Saque implements Operacao {

    @Override
    public String nome() {
        return "SAQUE";
    }

    @Override
    public boolean exigeSaldo() {
        return true;
    }

    @Override
    public void executar(Conta conta, BigDecimal valor) {
        conta.debitar(valor);
    }
}
