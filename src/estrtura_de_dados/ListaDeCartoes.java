package estrtura_de_dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import pessoa.Cartao;
import pessoa.Pessoa;
import pessoa.Sexo;

public class ListaDeCartoes {
	private List<Cartao> cartoes;
	private AtomicInteger totalCartoesSexoMasculino;
	private AtomicInteger totalCartoesSexoFeminino;

	public ListaDeCartoes() {
		this.cartoes = Collections.synchronizedList(new ArrayList<Cartao>());
		totalCartoesSexoMasculino = new AtomicInteger(0);
		totalCartoesSexoFeminino = new AtomicInteger(0);
	}

	public void adicionarCartaoPessoa(Pessoa pessoa) {
		cartoes.add(pessoa.getMeuCartao());
		if (pessoa.sexo == Sexo.Masculino)
			totalCartoesSexoMasculino.incrementAndGet();
		else
			totalCartoesSexoFeminino.incrementAndGet();
	}
	
	public int totalDoSexoMasculino() {
		return totalCartoesSexoMasculino.get();
	}
	
	public int totalDoSexoFeminino() {
		return totalCartoesSexoFeminino.get();
	}
	
	public boolean contem(Cartao cartao) {
		return cartoes.contains(cartao);
	}
	
	public int total() {
		return cartoes.size();
	}
}
