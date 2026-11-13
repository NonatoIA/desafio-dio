package br.com.bytebank.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Guarda as estratégias disponíveis e permite escolhê-las pelo nome,
 * substituindo a cadeia de if/else por uma busca em um Map.
 */
public class CatalogoOperacoes {

    private final Map<String, Operacao> operacoes = new HashMap<>();

    public static CatalogoOperacoes padrao() {
        return new CatalogoOperacoes()
                .registrar(new Deposito())
                .registrar(new Saque());
    }

    public CatalogoOperacoes registrar(Operacao operacao) {
        operacoes.put(operacao.nome(), operacao);
        return this;
    }

    public Optional<Operacao> buscar(String nome) {
        if (nome == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(operacoes.get(nome.trim().toUpperCase()));
    }
}
