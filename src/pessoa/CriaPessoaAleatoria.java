package pessoa;

import java.util.Collections;
import java.util.List;
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
	private double tempoMedioFila;
	private double tempoMedioSala;

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

		System.out.println("\nPessoas que foram ciradas:\n" + listaDePessoasCriadas + "\n");

		this.executorService.shutdown();

		try {
			if (!executorService.awaitTermination(2, TimeUnit.MINUTES)) {
				System.out.println("Interrompenddo todas as threads....");
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sala);
		List<Pessoa> pessoas = getListaDePessoasCriadas();
		double tempoTotalSala = 0, tempoTotalFila = 0;
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa + "\t" + pessoa.getCartoes() + "\t" + pessoa.getTempoNaSala());

			tempoTotalSala += pessoa.getTempoNaSala();
			tempoTotalFila += pessoa.getTempoNaFila();
		}

		System.out.println("Tempo medio na fila: " + (tempoTotalFila / listaDePessoasCriadas.totalDePessoas()) + "s");
		System.out.println("Tempo medio na sala: " + (tempoTotalSala / listaDePessoasCriadas.totalDePessoas()) + "s");
		tempoMedioFila = tempoTotalFila / listaDePessoasCriadas.totalDePessoas();
		tempoMedioSala = tempoTotalSala / listaDePessoasCriadas.totalDePessoas();
		
	}

	public List<Pessoa> getListaDePessoasCriadas() {
		return Collections.unmodifiableList(listaDePessoasCriadas.getPessoas());
	}

	public double getTempoMedioFila() {
		return tempoMedioFila;
	}

	public double getTempoMedioSala() {
		return tempoMedioSala;
	}
}
