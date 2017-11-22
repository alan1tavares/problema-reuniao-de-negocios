package gui;

import javafx.beans.property.SimpleStringProperty;

public class RelatorioModelo {
	private final SimpleStringProperty nome;
	private final SimpleStringProperty cartao;
	private final SimpleStringProperty sala;
	
	public RelatorioModelo(String nome, String cartao, String sala) {
		this.nome = new SimpleStringProperty(nome);
		this.cartao = new SimpleStringProperty(cartao);
		this.sala = new SimpleStringProperty(sala);
	}

	public String getNome() {
		return nome.getValue();
	}

	public String getCartao() {
		return cartao.getValue();
	}

	public String getSala() {
		return sala.getValue();
	}
	
	
}
