package pessoa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sala.Sala;

public class CriaPessoaAleatoria implements Runnable {

	private int numPessoas;
	private ListaDePessoas listaDePessoasCriadas;
	private Sala sala;

	ExecutorService executorService;

	public CriaPessoaAleatoria(int numPessoas, Sala sala) {
		this.numPessoas = numPessoas;
		this.listaDePessoasCriadas = new ListaDePessoas();
		this.sala = sala;

		this.executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		for (int i = 0; i < numPessoas; i++) {
			try {

				Pessoa pessoa = new Pessoa(this.sala);
				System.out.println(pessoa + " foi criada");
				listaDePessoasCriadas.adicionar(pessoa);
				System.out.println(pessoa + " entrou na lista");

				this.executorService.execute(pessoa);

				Thread.sleep(2000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(this.listaDePessoasCriadas);

		this.executorService.shutdown();
	}
}
