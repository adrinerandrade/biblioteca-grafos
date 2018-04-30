package trabalho;


public class Vertice implements Comparable<Vertice>{
	private String cor;
	private int distancia;
	private int nome;
	private int verticePai;
	
	public Vertice(int nome) {
		this.cor = "branco";
		this.distancia = Integer.MAX_VALUE;
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int nivel) {
		this.distancia = nivel;
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
	public int compareTo(Vertice v) {
		if(this.distancia < v.distancia) {
			return -1;
		} else if(this.distancia > v.distancia) {
			return 1;
		}
		return 0;
	}	
	
}
