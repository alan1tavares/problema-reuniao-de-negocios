package gui;

import javafx.beans.property.SimpleStringProperty;

public class RelatorioModelo {
	private final SimpleStringProperty nome;
	private final SimpleStringProperty cartao;
	private final SimpleStringProperty tempo;
	
	public RelatorioModelo(String nome, String cartao, String tempo) {
		this.nome = new SimpleStringProperty(nome);
		this.cartao = new SimpleStringProperty(cartao);
		this.tempo = new SimpleStringProperty(tempo);
	}

	public String getNome() {
		return nome.getValue();
	}

	public String getCartao() {
		return cartao.getValue();
	}

	public String getTempo() {
		return tempo.getValue();
	}
	
	
}
