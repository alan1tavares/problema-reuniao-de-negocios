package estrtura_de_dados.buffer;

import pessoa.Pessoa;

public class ListaPessoas implements Buffer<Pessoa>{
	int tamanho;
	Pessoa[] pessoas;

	
	public ListaPessoas(int tamanho) {
		this.tamanho = tamanho;
		pessoas = new Pessoa[tamanho];
	}

	@Override
	public void adicionar(Pessoa elemento) {
		for (int i = 0; i < pessoas.length; i++) {
			if (pessoas[i] == null)
				pessoas[i] = elemento;
		}
	}

	@Override
	public void remover(Pessoa elemento) {
		for (int i = 0; i < pessoas.length; i++) {
			if(pessoas[i] == elemento)
				pessoas[i] = null;
		}
	}

	@Override
	public boolean contem(Pessoa elemento) {
		for (int i = 0; i < pessoas.length; i++) {
			if(pessoas[i] == elemento)
				return true;
		}
		
		return false;
		
	}
	
}
