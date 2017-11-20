import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pessoa.CriaPessoaAleatoria;
import sala.Sala;

public class Main {

	public static void main(String[] args) {
		Sala sala = new Sala(5);
		CriaPessoaAleatoria fabricaDePessoas = new CriaPessoaAleatoria(10, sala);
		
//		Porteiro porteiro = new Porteiro(sala, listaDeEspera);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// Cria uma pessoa a cada dois segundos
		executorService.execute(fabricaDePessoas);
		// Porteiro controla quem entra e sae da sala
//		executorService.execute(porteiro);
		
		executorService.shutdown();
		

	}

}
