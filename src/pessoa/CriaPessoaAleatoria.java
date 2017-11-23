package pessoa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import estrtura_de_dados.ListaDePessoas;
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

		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		for (int i = 0; i < numPessoas; i++) {
			try {

				Pessoa pessoa = new Pessoa(this.sala);
				System.out.println(pessoa + " foi criada");
				listaDePessoasCriadas.adicionar(pessoa);

				executorService.execute(pessoa);

				Thread.sleep(2000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("\n Pssoas que foram ciradas:\n" + listaDePessoasCriadas + "\n");

		this.executorService.shutdown();

		try {
			if (!executorService.awaitTermination(70, TimeUnit.SECONDS)) {
				System.out.println("Interrompenddo todas as threads....");
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
