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

	// public AtomicBoolean estaTrocandoCartao = new AtomicBoolean(false);

	public Pessoa(Sala sala) {
		this.sala = sala;
		meuCartao = new Cartao();
		cartoes = new ListaDeCartoes();
	}

	@Override
	public void run() {

		entrarNaSala();

		System.out.println(this + " esta procurando alguem pra trocar cartao");
		for (int i = 1; i <= 3; i++) {

			List<Pessoa> pessoas = sala.listaDePessoasNaSala();
			System.out.println(this + " ja está " + i + "a tentativa");
			System.out.println(this + " pessoas na sala " + sala.listaDePessoasNaSala());

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

		synchronized (this) {
			while (cartoes.total() < 3 || cartoes.totalDoSexoMasculino() < 1
					|| cartoes.totalDoSexoFeminino() < 1/* && !estaTrocandoCartao() */) {
				try {
					System.out.println(this + " nao tem cartoes pra sair da sala. Foi dormir.");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		sairDaSala();
	}

	private Pessoa buscarPessoaPraTrocarCartao(List<Pessoa> pessoas) {
		synchronized (pessoas) {
			for (Pessoa pessoa : pessoas) {
				if (pessoa.getMeuCartao() != meuCartao && !tenhoEsteCartao(pessoa.getMeuCartao())
				/* && !pessoa.estaTrocandoCartao() */)
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
		return super.toString().replaceAll("pessoa.", "");
	}

	public Cartao getMeuCartao() {
		return meuCartao;
	}

	// public boolean estaTrocandoCartao() {
	// return estaTrocandoCartao.get();
	// }

	// public void setTrocandoCartao(boolean trocandoCartao) {
	// estaTrocandoCartao.set(trocandoCartao);
	// }

}
