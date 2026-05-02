package com.ingressos.model;

/**
 * Ingresso VIP — inclui área especial, open bar e brindes.
 * Valor final = precoBase * 1.8 (acréscimo de 80%).
 */
public class IngressoVIP extends Ingresso {

    private static final double MULTIPLICADOR_VIP = 1.8;

    private double precoBase;
    private String beneficios;

    public IngressoVIP() {
        super();
        this.setTipo("VIP");
        this.beneficios = "Área VIP, open bar e brindes exclusivos";
    }

    public IngressoVIP(String eventoId, String nomeEvento, String nomeComprador,
                       String cpfComprador, String local, String dataEvento, double precoBase) {
        super(eventoId, nomeEvento, nomeComprador, cpfComprador, local, dataEvento, "VIP");
        this.precoBase  = precoBase;
        this.beneficios = "Área VIP, open bar e brindes exclusivos";
    }

    @Override
    public double calcularValor() {
        return precoBase * MULTIPLICADOR_VIP;
    }

    @Override
    public String imprimirIngresso() {
        return String.format(
            "INGRESSO VIP\n" +
            "Evento: %s\n" +
            "Local: %s | Data: %s\n" +
            "Comprador: %s | CPF: %s\n" +
            "Beneficios: %s\n" +
            "Preco base: R$ %.2f  |  Acrescimo VIP: 80%%\n" +
            "Valor final: R$ %.2f\n" +
            "Comprado em: %s",
            getNomeEvento(), getLocal(), getDataEvento(),
            getNomeComprador(), getCpfComprador(),
            beneficios, precoBase,
            calcularValor(), formatarDataCompra()
        );
    }

    @Override
    public String descricaoTipo() {
        return "VIP — area especial com 80% de acrescimo";
    }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    public String getBeneficios() { return beneficios; }
    public void setBeneficios(String beneficios) { this.beneficios = beneficios; }
}
