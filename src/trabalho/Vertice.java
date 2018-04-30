package trabalho;


public class Vertice {
	private String cor;
	private int nivel;
	private int nome;
	private int verticePai;
	
	public Vertice(int nome) {
		this.cor = "branco";
		this.nivel = 0;
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
	
}
