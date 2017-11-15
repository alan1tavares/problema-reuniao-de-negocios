package sala;

import pessoa.ListaDePessoas;
import pessoa.Pessoa;

public class Sala {

	private ListaDePessoas lugares;
	private int tamanho;


	public Sala(int tamanho) {
		this.tamanho = tamanho;
		this.lugares = new ListaDePessoas();
	}
	
	// Verdadeiro se a pessoa entrou na sala	
	public synchronized void entrarNaSala(Pessoa pessoa){
		System.out.printf("%s vai tentar entrar na sala.\n", pessoa);
		
		if(this.lugares.totalDePessoas() == this.tamanho) {
			System.out.printf("A sala esta cheia.\nA %s vai dormir.\n", pessoa);
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.lugares.adicionar(pessoa);
		System.out.printf("%s entrou na sala. A sala tem %d lugares vazios.\n", pessoa, (this.tamanho - this.lugares.totalDePessoas()));
		System.out.println("Sala:\n" + this+"\n");
	}
	
	public synchronized void sairDaSala(Pessoa pessoa) {
		System.out.println(pessoa + " fazendo nada na sala.");
		
		this.lugares.remover(pessoa);
		
		System.out.println(pessoa+"saiu da sala. Nofificando as outras.");
		System.out.println("Sala:\n" + this+"\n");
		
		notifyAll();
	}
	
	@Override
	public String toString() {
		return this.lugares.toString();
	}

}
