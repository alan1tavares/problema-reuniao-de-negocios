import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import pessoa.CriaPessoaAleatoria;
import sala.Sala;

public class Main {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Sala sala = new Sala(5);
		CriaPessoaAleatoria fabricaDePessoas = new CriaPessoaAleatoria(10, sala);
		
//		Porteiro porteiro = new Porteiro(sala, listaDeEspera);
		
		
		// Cria uma pessoa a cada dois segundos
		executorService.execute(fabricaDePessoas);
		// Porteiro controla quem entra e sae da sala
//		executorService.execute(porteiro);
		
		executorService.shutdown();
		
		
		

	}

}
