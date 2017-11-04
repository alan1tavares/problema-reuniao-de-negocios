package pessoa;
import java.util.Random;

public class Pessoa {
	private Cartao meuCartao;
	private String sexo;
	
	private Cartao[] vCartoes = new Cartao[3];
	
	public Pessoa() {
		gerarSexo();
		this.meuCartao = new Cartao();
	}
	
	private void gerarSexo(){
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
	
	public void receberCartaoIdPessoa(Cartao cartao){
		if(!is_vCartoesCheio() && !is_cartao(cartao)){
			adicionarCartao(cartao);
		}
	}
	
	private void adicionarCartao(Cartao cartao) {
		for (int i = 0; i < vCartoes.length; i++)
			if(vCartoes[i] == null){
				vCartoes[i] = cartao;
				break;
			}
	}

	private boolean is_vCartoesCheio(){
		for (Cartao i : vCartoes)
			if (i == null) return false;
		return true;
	}
	
	private boolean is_cartao(Cartao cartao){
		for (Cartao i : vCartoes)
			if( i.equals(cartao)) return true;
		return false;
	}
	
}
