import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pessoa.CriaPessoaAleatoria;
import pessoa.Pessoa;
import sala.Sala;

public class Main {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Sala sala = new Sala(5);
		CriaPessoaAleatoria fabricaDePessoas = new CriaPessoaAleatoria(10, sala);
				
		executorService.execute(fabricaDePessoas);

		executorService.shutdown();
			

	}

}
