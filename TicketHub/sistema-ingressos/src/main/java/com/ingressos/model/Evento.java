package com.ingressos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "eventos")
public class Evento {

    @Id
    private String id;
    private String nome;
    private String local;
    private String data;
    private String descricao;
    private double precoBase;
    private int capacidade;
    private int ingressosVendidos;

    public Evento() {}

    public Evento(String nome, String local, String data, String descricao,
                  double precoBase, int capacidade) {
        this.nome             = nome;
        this.local            = local;
        this.data             = data;
        this.descricao        = descricao;
        this.precoBase        = precoBase;
        this.capacidade       = capacidade;
        this.ingressosVendidos = 0;
    }

    public boolean temVagasDisponiveis() {
        return ingressosVendidos < capacidade;
    }

    public int vagasRestantes() {
        return capacidade - ingressosVendidos;
    }

    public void registrarVenda() {
        if (!temVagasDisponiveis()) throw new IllegalStateException("Evento sem vagas disponiveis.");
        ingressosVendidos++;
    }

    public void cancelarVenda() {
        if (ingressosVendidos > 0) ingressosVendidos--;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public int getIngressosVendidos() { return ingressosVendidos; }
    public void setIngressosVendidos(int ingressosVendidos) { this.ingressosVendidos = ingressosVendidos; }
}
