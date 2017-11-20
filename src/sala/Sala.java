package sala;

import java.util.List;

import estrtura_de_dados.ListaDePessoas;
import pessoa.Pessoa;

public class Sala {

	private ListaDePessoas lugares;
	private int tamanho;

	public Sala(int tamanho) {
		this.tamanho = tamanho;
		this.lugares = new ListaDePessoas();
	}

	// Verdadeiro se a pessoa entrou na sala
	public synchronized void entrarNaSala(Pessoa pessoa) {

		if (this.lugares.totalDePessoas() == this.tamanho) {
			System.out.printf("A sala esta cheia.\nA %s vai dormir.\n", pessoa);
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.lugares.adicionar(pessoa);
		System.out.println(pessoa + " entrou na sala.");
		System.out.println("Pessoas na sala: " + this + ".");
	}

	public synchronized void sairDaSala(Pessoa pessoa) {

		this.lugares.remover(pessoa);

		System.out.println(pessoa + " saiu da sala. Nofificando as outras.");
		System.out.println("Sala: " + this);

		notifyAll();
	}
	
	public synchronized Pessoa buscarPessoa() {
		return null;
	}

	@Override
	public String toString() {
		return this.lugares.toString();
	}

	public List<Pessoa> listaDePessoasNaSala() {
		return this.lugares.getPessoas();
	}
}
