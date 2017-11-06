package sala;

import java.util.ArrayList;
import java.util.List;

import pessoa.Pessoa;

public class Porteiro implements Runnable {

	private Sala sala;
	private List<Pessoa> listaDeEspera;

	public Porteiro(Sala sala, List<Pessoa> listaDeEspera) {
		this.sala = sala;
		this.listaDeEspera = listaDeEspera;
	}

	@Override
	public void run() {
		while(listaDeEspera.isEmpty())
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		while (!this.sala.isCheia()) {
			try {
				deixarPessoasEntraremNaSala();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void deixarPessoasEntraremNaSala() throws InterruptedException {
		ArrayList<Pessoa> aux = new ArrayList<Pessoa>();

		for (Pessoa pessoa : listaDeEspera) {
			this.sala.entrarNaSala(pessoa);
			System.out.println(pessoa + " entrou na sala.");

			aux.add(pessoa);
		}
		// Remove as pessoas da lista de espera
		this.listaDeEspera.removeAll(aux);
	}

}
