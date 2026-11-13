package br.com.bytebank.strategy;

import br.com.bytebank.model.Conta;

import java.math.BigDecimal;

public class Deposito implements Operacao {

    @Override
    public String nome() {
        return "DEPOSITO";
    }

    @Override
    public boolean exigeSaldo() {
        return false;
    }

    @Override
    public void executar(Conta conta, BigDecimal valor) {
        conta.creditar(valor);
    }
}
