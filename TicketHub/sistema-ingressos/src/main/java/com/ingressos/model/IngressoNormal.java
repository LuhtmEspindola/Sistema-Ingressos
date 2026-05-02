package com.ingressos.model;

/**
 * Ingresso Normal — preço base do evento, sem acréscimos ou descontos.
 */
public class IngressoNormal extends Ingresso {

    private double precoBase;

    public IngressoNormal() {
        super();
        this.setTipo("NORMAL");
    }

    public IngressoNormal(String eventoId, String nomeEvento, String nomeComprador,
                          String cpfComprador, String local, String dataEvento, double precoBase) {
        super(eventoId, nomeEvento, nomeComprador, cpfComprador, local, dataEvento, "NORMAL");
        this.precoBase = precoBase;
    }

    @Override
    public double calcularValor() {
        return precoBase;
    }

    @Override
    public String imprimirIngresso() {
        return String.format(
            "INGRESSO NORMAL\n" +
            "Evento: %s\n" +
            "Local: %s | Data: %s\n" +
            "Comprador: %s | CPF: %s\n" +
            "Valor: R$ %.2f\n" +
            "Comprado em: %s",
            getNomeEvento(), getLocal(), getDataEvento(),
            getNomeComprador(), getCpfComprador(),
            calcularValor(), formatarDataCompra()
        );
    }

    @Override
    public String descricaoTipo() {
        return "Normal — preço cheio sem desconto";
    }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }
}
