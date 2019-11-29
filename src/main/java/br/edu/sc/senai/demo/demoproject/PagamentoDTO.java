package br.edu.sc.senai.demo.demoproject;

public class PagamentoDTO {
	public static final PagamentoDTO NULL_VALUE = new PagamentoDTO(Double.valueOf(0.0));

	private final Double valor;

	public PagamentoDTO(final Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return this.valor;
	}
}