
public class Cartao {
	private static int numeroDeCartoes = 0;
	private int id;
	
	public Cartao() {
		this.id = ++Cartao.numeroDeCartoes;
	}
	
	public int getId() {
		return id;
	}
}
