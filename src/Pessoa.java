import java.util.Random;

public class Pessoa {
	private int meuCartao;
	private final String sexo;
	private static int numerDeObjetos = 0;
	
	private int[] vCartoes = new int[3];
	
	public Pessoa() {
		// 0 - masculino, 1 feminino 
		int sexo = new Random().nextInt(2);
		this.sexo = (sexo == 0) ? "masculino" : "feminino";
		
		this.meuCartao = Pessoa.numerDeObjetos += 1;
	}
	
	public int getId() {
		return meuCartao;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void receberCartaoIdPessoa(int cartao){
		if(!is_vCartoesCheio() && !is_cartao(cartao)){
			adicionarCartao(cartao);
		}
	}
	
	private void adicionarCartao(int cartao) {
		for (int i = 0; i < vCartoes.length; i++)
			if(vCartoes[i] == 0){
				vCartoes[i] = cartao;
				break;
			}
	}

	private boolean is_vCartoesCheio(){
		for (int i : vCartoes)
			if (i == 0) return false;
		return true;
	}
	
	private boolean is_cartao(int id){
		for (int i : vCartoes)
			if( i == id) return true;
		return false;
	}
	
}
