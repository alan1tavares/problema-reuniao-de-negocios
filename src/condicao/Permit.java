package condicao;

public class Permit implements Condition{
	private int contador;
	
	private void Pemit(int inicializarContador) {
		this.contador = inicializarContador;
	} 
	@Override
	public synchronized void await() {
		while(this.contador == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.contador--;
		}
	}

	@Override
	public synchronized void signal() {
		contador++;
		
	}

}
