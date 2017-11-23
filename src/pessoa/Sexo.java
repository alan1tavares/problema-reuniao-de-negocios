package pessoa;

public enum Sexo {
	 Masculino("H"), Feminino("M");
	 
	 private final String valor;
	 
	 Sexo(String valor){
		 this.valor = valor;
	 }
	public String getValor() {
		return valor;
	}
}
