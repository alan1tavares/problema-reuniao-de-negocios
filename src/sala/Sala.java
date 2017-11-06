package sala;

import pessoa.Pessoa;

public class Sala {

	private Pessoa[] lugares;

	public Sala(int tamanho) {
		this.lugares = new Pessoa[tamanho];
	}

	// Verdadeiro se a pessoa entrou na sala
	public synchronized boolean entrarNaSala(Pessoa pessoa) throws InterruptedException {
		for (int i = 0; i < this.lugares.length; i++) {
			if (this.lugares[i] == null) {
				this.lugares[i] = pessoa;
				return true;
			}
		}
		// A pessoa não conseguiu entrar na sala. Então ela fica aguardando até a sala
		// tiver vaga
		pessoa.wait();
		return false;
	}

	private boolean soTemHomem() {
		int count = 0;
		for (Pessoa pessoa : lugares)
			if (pessoa.getSexo() == "masculino")
				count++;

		return count == this.lugares.length ? true : false;
	}

	public boolean isVazia() {
		for (int i = 0; i < this.lugares.length; i++)
			if (this.lugares[i] != null)
				return false;
		return true;
	}

	public boolean isCheia() {
		for (int i = 0; i < this.lugares.length; i++)
			if (this.lugares[i] == null)
				return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
