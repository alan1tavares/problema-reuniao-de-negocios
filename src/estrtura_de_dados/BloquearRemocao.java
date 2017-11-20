package estrtura_de_dados;

import condicao.Condition;

public class BloquearRemocao implements Condition{

	@Override
	public synchronized void await() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void signal() {
		notifyAll();		
	}

}
