package pessoa;

import java.util.Random;

import sala.Sala;

public class Pessoa implements Runnable {
	private Cartao meuCartao;
	private String sexo;

	private Cartao[] vCartoes = new Cartao[3]; // Mudar isso aqui depois pra um ArrayList.
	// A pessoa pode receber vários cartoes

	private Sala sala;

	// Métodos

	public Pessoa(Sala sala) {
		gerarSexo();
		this.meuCartao = new Cartao();
		this.sala = sala;
	}

	private void gerarSexo() {
		// 0 - masculino, 1 feminino
		int sexo = new Random().nextInt(2);
		this.sexo = (sexo == 0) ? "masculino" : "feminino";
	}

	@Override
	public void run() {

		entrarNaSala();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// for (int i = 1; i <= 3; i++)
		// tentarTrocarCartaComOutraPessoa();
		//
		// while (!podeSairDaSala())
		// this.wait();

		sairDaSala();
	}

	private void sairDaSala() {
		this.sala.sairDaSala(this);
	}

	private void entrarNaSala() {
		this.sala.entrarNaSala(this);
	}

	@Override
	public String toString() {
		return super.toString().replaceAll("pessoa.", "");
	}

}
