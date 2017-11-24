import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pessoa.CriaPessoaAleatoria;
import sala.Sala;

public class Main {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Sala sala = new Sala(5);
		CriaPessoaAleatoria fabricaDePessoas = new CriaPessoaAleatoria(20, sala);
				
		executorService.execute(fabricaDePessoas);

		executorService.shutdown();
			

	}

}
