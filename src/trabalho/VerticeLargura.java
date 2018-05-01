package trabalho;


public class VerticeLargura {

	private String cor;
	private int nivel;
	private int nome;
	private Integer verticePai;
	
	public VerticeLargura(int nome) {
		this.cor = "branco";
		this.nivel = Integer.MAX_VALUE;
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getNome() {
		return nome;
	}
	public void setNome(int nome) {
		this.nome = nome;
	}
	public int getVerticePai() {
		return verticePai;
	}
	public void setVerticePai(int verticePai) {
		this.verticePai = verticePai;
	}

	@Override
	public String toString() {
		return "{" +
				"nome='" + nome + '\'' +
				", distancia=" + nivel +
				", cor=" + cor +
				", verticePai=" + verticePai +
				'}';
	}
}
