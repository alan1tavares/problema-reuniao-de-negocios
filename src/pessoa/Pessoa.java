package pessoa;

import java.util.Random;

import sala.Sala;

public class Pessoa implements Runnable {
	private Cartao meuCartao;
	private String sexo;

	private Cartao[] vCartoes = new Cartao[3];
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

	public Cartao getMeuCartao() {
		return meuCartao;
	}

	public String getSexo() {
		return sexo;
	}

	@Override
	public void run() {
		try {
			// Vai ficar tentando entrar na sala
			while (!this.sala.entrarNaSala(this));
			
			// A pesso entrou na sala
			while(!tenhoTodosOsCartoes()) {
				
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void receberCartaoIdPessoa(Cartao cartao) {
		if (!tenhoTodosOsCartoes() && !is_cartao(cartao)) {
			adicionarCartao(cartao);
		}
	}

	private void adicionarCartao(Cartao cartao) {
		for (int i = 0; i < vCartoes.length; i++)
			if (vCartoes[i] == null) {
				vCartoes[i] = cartao;
				break;
			}
	}

	private boolean tenhoTodosOsCartoes() {
		for (Cartao i : vCartoes)
			if (i == null)
				return false;
		return true;
	}

	private boolean is_cartao(Cartao cartao) {
		for (Cartao i : vCartoes)
			if (i.equals(cartao))
				return true;
		return false;
	}

}
