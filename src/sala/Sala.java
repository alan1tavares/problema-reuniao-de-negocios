package sala;
import java.util.Arrays;
import java.util.List;

import pessoa.Pessoa;

public class Sala {

	private List<Pessoa> lugares;

	public Sala(int tamanho) {
		this.lugares = Arrays.asList(new Pessoa[5]);
	}
	
	// Verdadeiro se a pessoa entrou na sala
	public synchronized void entrarNaSala(Pessoa pessoa) throws InterruptedException {
		this.lugares.add(pessoa);
		/*if(lugares.size() == 4 && soTemHomem() && pessoa.getSexo() == "feminino")
			return false;
		if(lugares.size() == 4 && !soTemHomem() && pessoa.getSexo() == "masculino")
			return false;
		
		if (!this.lugares.add(pessoa)) {
			pessoa.wait();
			return false;
		} else return true;*/
	}
	
	
	private boolean soTemHomem() {
		int count = 0;
		for (Pessoa pessoa : lugares)
			if(pessoa.getSexo() == "masculino") count++;
		
		return count == this.lugares.size() ? true : false;
	}
	
	public boolean is_Vazia() {
		return this.lugares.isEmpty();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
