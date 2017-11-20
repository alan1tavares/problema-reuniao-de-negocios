package estrtura_de_dados.buffer;

public interface Buffer<E> {
	public void adicionar(E elemento);
	public void remover(E elemento);
	public boolean contem(E elemento);
	
	
}
