package pessoa;
import java.util.List;

public class FabricaPessoas implements Runnable {
	private List<Pessoa> pessoasCriadas;
	private int numDePessoasASeremCriadas;
	
	public FabricaPessoas(int numDePessoasASeremCriadas, List<Pessoa> listaDeEspera) {
		this.numDePessoasASeremCriadas = numDePessoasASeremCriadas;
		this.pessoasCriadas = listaDeEspera;
	}
	

	private synchronized void addLista(Pessoa pessoa) {
		pessoasCriadas.add(pessoa);
		System.out.println(pessoa+" foi pra lista de espera.");
		System.out.println(pessoasCriadas);
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
