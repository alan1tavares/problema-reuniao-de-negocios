package pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import sala.Sala;

public class Pessoa implements Runnable {
	private Cartao meuCartao;
	private String sexo;

	private List<Cartao> cartoes;

	private Sala sala;

	private AtomicBoolean trocandoCartao = new AtomicBoolean(false);

	// Métodos

	public Pessoa(Sala sala) {
		gerarSexo();
		this.meuCartao = new Cartao();
		this.sala = sala;
		this.cartoes = new ArrayList<>();
	}

	private void gerarSexo() {
		// 0 - masculino, 1 feminino
		int sexo = new Random().nextInt(2);
		this.sexo = (sexo == 0) ? "masculino" : "feminino";
	}

	@Override
	public void run() {

		entrarNaSala();

		System.out.println(this + " esta procurando alguem pra trocar cartao");
		synchronized (this.sala) {
			for (int i = 1; i <= 3; i++) {
				List<Pessoa> pessoas = new ArrayList<>(this.sala.listaDePessoasNaSala());
				System.out.println(this + " ja está " + i + "a tentativa");
				System.out.println(this + " pessoas na sala " + pessoas);

				for (Pessoa pessoa : pessoas) {
					synchronized (pessoa) {
						if (pessoa.getMeuCartao() != this.meuCartao && !tenhoEsteCartao(pessoa.getMeuCartao())
								&& !pessoa.estaTrocandoCartao()) {
							pessoa.setTrocandoCartao(true);
							this.setTrocandoCartao(true);

							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// if(this.sala.estaNaSala(pessoa))
							this.trocarCartao(pessoa);
							pessoa.trocarCartao(this);
							pessoa.notify();

							System.out.println("Na " + i + "a tentativa " + this + " trocou cartao com " + pessoa);

							pessoa.setTrocandoCartao(false);
							this.setTrocandoCartao(false);

							break;

						}
					}
				}
			}
		}

		synchronized (this) {
			while (this.totalDeCartoes() < 3 && !this.estaTrocandoCartao()) {
				try {
					System.out.println(this + " nao tem cartoes pra sair da sala. Foi dormir.");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		//
		// while (!podeSairDaSala())
		// this.wait();

		sairDaSala();
	}

	private void trocarCartao(Pessoa pessoa) {
		this.cartoes.add(pessoa.meuCartao);
	}

	private boolean tenhoEsteCartao(Cartao cartao) {
		return this.cartoes.contains(cartao);
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

	public Cartao getMeuCartao() {
		return this.meuCartao;
	}

	public boolean estaTrocandoCartao() {
		return trocandoCartao.get();
	}

	public void setTrocandoCartao(boolean trocandoCartao) {
		this.trocandoCartao.set(trocandoCartao);
	}

	public int totalDeCartoes() {
		return this.cartoes.size();
	}

}
