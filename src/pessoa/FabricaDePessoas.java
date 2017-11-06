package pessoa;
import java.util.List;

public class FabricaDePessoas implements Runnable {
	private List<Pessoa> pessoas;
	private int numDePessoasASeremCriadas;
	
	public FabricaDePessoas(int numDePessoasASeremCriadas, List<Pessoa> pessoas) {
		this.numDePessoasASeremCriadas = numDePessoasASeremCriadas;
		this.pessoas = pessoas;
	}
	

	private synchronized void addLista(Pessoa pessoa) {
		pessoas.add(pessoa);
		System.out.println(pessoa+" foi pra lista de espera.");
		System.out.println(pessoas);
	}
	
	@Override
	public void run() {
		for (int i = 0; i < numDePessoasASeremCriadas; i++) {
			try {
				
				Pessoa pessoa = new Pessoa();
				
				System.out.println("Criado "+ pessoa);
				addLista(pessoa);
								
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
