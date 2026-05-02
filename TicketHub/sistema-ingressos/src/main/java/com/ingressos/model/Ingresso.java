package com.ingressos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata base para todos os tipos de ingresso.
 * Define o contrato polimórfico através dos métodos abstratos
 * calcularValor() e imprimirIngresso().
 */
@Document(collection = "ingressos")
public abstract class Ingresso {

    @Id
    private String id;

    private String eventoId;
    private String nomeEvento;
    private String nomeComprador;
    private String cpfComprador;
    private String local;
    private String dataEvento;
    private LocalDateTime dataCompra;
    private String tipo;

    public Ingresso() {
        this.dataCompra = LocalDateTime.now();
    }

    public Ingresso(String eventoId, String nomeEvento, String nomeComprador,
                    String cpfComprador, String local, String dataEvento, String tipo) {
        this.eventoId     = eventoId;
        this.nomeEvento   = nomeEvento;
        this.nomeComprador = nomeComprador;
        this.cpfComprador = cpfComprador;
        this.local        = local;
        this.dataEvento   = dataEvento;
        this.tipo         = tipo;
        this.dataCompra   = LocalDateTime.now();
    }

    /**
     * Calcula o valor final do ingresso conforme o tipo.
     * Cada subclasse implementa sua regra de precificação.
     */
    public abstract double calcularValor();

    /**
     * Retorna um resumo formatado com todas as informações do ingresso.
     * Cada subclasse pode personalizar a exibição.
     */
    public abstract String imprimirIngresso();

    /**
     * Retorna a descrição do tipo de ingresso (Normal, VIP, Meia-Entrada).
     */
    public abstract String descricaoTipo();

    protected String formatarDataCompra() {
        if (dataCompra == null) return "";
        return dataCompra.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEventoId() { return eventoId; }
    public void setEventoId(String eventoId) { this.eventoId = eventoId; }

    public String getNomeEvento() { return nomeEvento; }
    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }

    public String getNomeComprador() { return nomeComprador; }
    public void setNomeComprador(String nomeComprador) { this.nomeComprador = nomeComprador; }

    public String getCpfComprador() { return cpfComprador; }
    public void setCpfComprador(String cpfComprador) { this.cpfComprador = cpfComprador; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getDataEvento() { return dataEvento; }
    public void setDataEvento(String dataEvento) { this.dataEvento = dataEvento; }

    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
