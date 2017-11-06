import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pessoa.FabricaDePessoas;
import pessoa.Pessoa;
import sala.Porteiro;
import sala.Sala;

public class Main {

	public static void main(String[] args) {
		Sala sala = new Sala(5);
		List<Pessoa> listaDeEspera = new ArrayList<>();
		
		FabricaDePessoas fabricaDePessoa = new FabricaDePessoas(5, listaDeEspera);
		Porteiro porteiro = new Porteiro(sala, listaDeEspera);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// Cria uma pessoa a cada dois segundos
		executorService.execute(fabricaDePessoa);
		// Porteiro controla quem entra e sae da sala
		executorService.execute(porteiro);
		
		executorService.shutdown();

	}

}
