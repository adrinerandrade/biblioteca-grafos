package trabalho;


public class VerticeProfundidade {

	private String cor;
	private int distancia;
	private int distanciaRetorno;
	private int nome;
	private Integer verticePai;
	
	public VerticeProfundidade(int nome) {
		this.cor = "branco";
		this.distancia = Integer.MAX_VALUE;
		this.distanciaRetorno = Integer.MAX_VALUE;
		this.nome = nome;
	}

	public int getDistanciaRetorno() {
		return distanciaRetorno;
	}

	public void setDistanciaRetorno(int distanciaRetorno) {
		this.distanciaRetorno = distanciaRetorno;
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
	public String toString() {
		return "Vertice{" +
				"cor='" + cor + '\'' +
				", distancia=" + distancia +
				", distanciaRetorno=" + distanciaRetorno +
				", nome=" + nome +
				", verticePai=" + verticePai +
				'}';
	}
}
