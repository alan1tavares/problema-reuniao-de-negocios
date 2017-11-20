package pessoa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaDePessoas {
	private List<Pessoa> pessoas;

	public ListaDePessoas() {
		this.pessoas = Collections.synchronizedList(new ArrayList<Pessoa>());
	}

	public void adicionar(Pessoa pessoa) {
		this.pessoas.add(pessoa);
	}

	public void remover(Pessoa pessoa) {
		this.pessoas.remove(pessoa);
	}

	public int totalDePessoas() {
		return this.pessoas.size();
	}
	
	

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	@Override
	public String toString() {
		String saida = String.format("Total de pessoas: %d.\nPessoas%s", this.pessoas.size(),
				this.pessoas.toString());
		return saida;
	}
}
