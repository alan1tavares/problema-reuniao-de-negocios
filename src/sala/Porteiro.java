package sala;
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
		try {
			Thread.sleep(13000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (Pessoa pessoa : listaDeEspera) {
			try {
				this.sala.entrarNaSala(pessoa);
				System.out.println(pessoa + "entrou na sala.");
				this.listaDeEspera.remove(pessoa);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
