package pessoa;

import java.util.List;
import java.util.Random;

import estrtura_de_dados.ListaDeCartoes;
import sala.Sala;

public class Pessoa implements Runnable {
	public final Sexo sexo = (new Random().nextInt(2) == 0) ? Sexo.Masculino : Sexo.Feminino;

	private Cartao meuCartao;
	private ListaDeCartoes cartoes;

	private Sala sala;

	private double tempoNaSala;
	private double tempoNaFila;

	public Pessoa(Sala sala) {
		this.sala = sala;
		meuCartao = new Cartao(Integer.toHexString(hashCode()), sexo);
		cartoes = new ListaDeCartoes();
	}

	@Override
	public void run() {
		Thread.currentThread().setName(this.toString());

		long tempoInicialFila = System.currentTimeMillis();
		entrarNaSala();
		tempoNaFila = (System.currentTimeMillis() - tempoInicialFila) / 1000;

		long tempoInicialSala = System.currentTimeMillis();
		trocarCartao();

		synchronized (this) {
			while (cartoes.total() < 3 || cartoes.totalDoSexoMasculino() < 1 || cartoes.totalDoSexoFeminino() < 1) {
				try {
					System.out.println(this + " nao tem cartoes pra sair da sala. Foi dormir.");
					wait();
				} catch (InterruptedException e) {
					System.out.println("Thread: " + Thread.currentThread().getName() + " foi imterrompida");
					tempoNaSala = (System.currentTimeMillis() - tempoInicialSala) / 1000;
					Thread.currentThread().interrupt();
					return;
					// e.printStackTrace();
				}
			}
		}

		sairDaSala();

		tempoNaSala = (System.currentTimeMillis() - tempoInicialSala) / 1000;

	}

	private void trocarCartao() {
		System.out.println(this + " esta procurando alguem pra trocar cartao");
		for (int i = 1; i <= 3; i++) {

			List<Pessoa> pessoas = sala.listaDePessoasNaSala();
			System.out.println(this + " est� na " + i + "a tentativa");

			Pessoa pessoa = buscarPessoaPraTrocarCartao(pessoas);
			if (pessoa == null)
				continue;
			synchronized (pessoa) {

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

				if (!sala.listaDePessoasNaSala().contains(pessoa) || tenhoEsteCartao(pessoa.meuCartao))
					continue;
				trocarCartao(pessoa);
				pessoa.trocarCartao(this);
				pessoa.notify();

				System.out.println("Na " + i + "a tentativa " + this + " trocou cartao com " + pessoa);

			}

		}
	}

	private Pessoa buscarPessoaPraTrocarCartao(List<Pessoa> pessoas) {
		synchronized (pessoas) {
			for (Pessoa pessoa : pessoas) {
				if (pessoa.getMeuCartao() != meuCartao && !tenhoEsteCartao(pessoa.getMeuCartao()))
					return pessoa;
			}
		}
		return null;
	}

	private void trocarCartao(Pessoa pessoa) {
		cartoes.adicionarCartaoPessoa(pessoa);
	}

	private boolean tenhoEsteCartao(Cartao cartao) {
		return cartoes.contem(cartao);
	}

	private synchronized void sairDaSala() {
		sala.sairDaSala(this);
	}

	private void entrarNaSala() {
		sala.entrarNaSala(this);
	}

	@Override
	public String toString() {
		return super.toString().replaceAll("pessoa.", "") + "[" + sexo.getValor() + "]";
	}

	public Cartao getMeuCartao() {
		return meuCartao;
	}

	public ListaDeCartoes getCartoes() {
		return cartoes;
	}

	public double getTempoNaSala() {
		return tempoNaSala;
	}

	public double getTempoNaFila() {
		return tempoNaFila;
	}
}
