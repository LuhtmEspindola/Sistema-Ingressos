package com.ingressos.model;

/**
 * Ingresso Meia-Entrada — 50% de desconto sobre o preco base.
 * Destinado a estudantes, idosos e pessoas com deficiencia.
 */
public class IngressoMeia extends Ingresso {

    private static final double DESCONTO_MEIA = 0.5;

    private double precoBase;
    private String motivoDesconto;

    public IngressoMeia() {
        super();
        this.setTipo("MEIA");
    }

    public IngressoMeia(String eventoId, String nomeEvento, String nomeComprador,
                        String cpfComprador, String local, String dataEvento,
                        double precoBase, String motivoDesconto) {
        super(eventoId, nomeEvento, nomeComprador, cpfComprador, local, dataEvento, "MEIA");
        this.precoBase      = precoBase;
        this.motivoDesconto = motivoDesconto;
    }

    @Override
    public double calcularValor() {
        return precoBase * DESCONTO_MEIA;
    }

    @Override
    public String imprimirIngresso() {
        return String.format(
            "INGRESSO MEIA-ENTRADA\n" +
            "Evento: %s\n" +
            "Local: %s | Data: %s\n" +
            "Comprador: %s | CPF: %s\n" +
            "Motivo do desconto: %s\n" +
            "Preco base: R$ %.2f  |  Desconto: 50%%\n" +
            "Valor final: R$ %.2f\n" +
            "Comprado em: %s",
            getNomeEvento(), getLocal(), getDataEvento(),
            getNomeComprador(), getCpfComprador(),
            motivoDesconto, precoBase,
            calcularValor(), formatarDataCompra()
        );
    }

    @Override
    public String descricaoTipo() {
        return "Meia-Entrada — 50% de desconto";
    }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    public String getMotivoDesconto() { return motivoDesconto; }
    public void setMotivoDesconto(String motivoDesconto) { this.motivoDesconto = motivoDesconto; }
}
