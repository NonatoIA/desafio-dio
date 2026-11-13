package br.com.bytebank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Registro imutável de uma operação processada (com sucesso ou falha).
 */
public record Transacao(LocalDateTime dataHora, String titular, String tipo,
                        BigDecimal valor, boolean sucesso, String resultado) {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public String resumo() {
        return String.format("[%s] %-6s %-9s %6s -> %s",
                dataHora.format(FORMATO), titular, tipo, valor, resultado);
    }
}
