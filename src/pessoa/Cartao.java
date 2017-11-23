package pessoa;

public class Cartao {
	private String id;
	private Sexo sexo;
	
	public Cartao(String id, Sexo sexo) {
		this.id = id;
		this.sexo = sexo;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return id+"["+sexo.getValor()+"]";
	}
}
