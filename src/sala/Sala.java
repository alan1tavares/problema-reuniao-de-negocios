package sala;

import java.util.List;

import estrtura_de_dados.ListaDePessoas;
import pessoa.Pessoa;
import pessoa.Sexo;

public class Sala {

	private ListaDePessoas lugares;
	private int tamanho;
	private int quantidadeSexoMasculino = 0;
	private int quantidadeSexoFeminino = 0;

	public Sala(int tamanho) {
		this.tamanho = tamanho;
		this.lugares = new ListaDePessoas();
	}

	public synchronized void entrarNaSala(Pessoa pessoa) {

		while (lugares.totalDePessoas() == tamanho
				|| (pessoa.sexo == Sexo.Masculino && quantidadeSexoMasculino >= (tamanho - 1))
				|| (pessoa.sexo == Sexo.Feminino && quantidadeSexoFeminino >= (tamanho - 1))) {
			System.out.println(pessoa + " nao conseguiu entrar na sala. Vai dormir.");
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Thread: " + Thread.currentThread().getName() + " foi imterrompida");
				Thread.currentThread().interrupt();
				return;
				// e.printStackTrace();
			}
		}

		if (pessoa.sexo == Sexo.Masculino)
			quantidadeSexoMasculino++;
		else
			quantidadeSexoFeminino++;

		lugares.adicionar(pessoa);
		System.out.println(pessoa + " entrou na sala.");
		System.out.println("Pessoas que etao na sala: " + this + ".");
	}

	public synchronized void sairDaSala(Pessoa pessoa) {

		this.lugares.remover(pessoa);

		if (pessoa.sexo == Sexo.Masculino)
			quantidadeSexoMasculino--;
		else
			quantidadeSexoFeminino--;

		System.out.println(pessoa + " saiu da sala. Nofificando as outras.");
		System.out.println("Pessoas que etao na sala: " + this + ".");

		notifyAll();
	}

	public synchronized Pessoa buscarPessoa() {
		return null;
	}

	@Override
	public String toString() {
		return lugares.toString();
	}

	public List<Pessoa> listaDePessoasNaSala() {
		return lugares.getPessoas();
	}
}
