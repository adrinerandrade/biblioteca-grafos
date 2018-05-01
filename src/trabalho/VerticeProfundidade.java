package trabalho;


public class VerticeProfundidade {

	private String cor;
	private int tempo;
	private int tempoRetorno;
	private int nome;
	private Integer verticePai;
	
	public VerticeProfundidade(int nome) {
		this.cor = "branco";
		this.tempo = Integer.MAX_VALUE;
		this.tempoRetorno = Integer.MAX_VALUE;
		this.nome = nome;
	}

	public int getTempoRetorno() {
		return tempoRetorno;
	}

	public void setTempoRetorno(int tempoRetorno) {
		this.tempoRetorno = tempoRetorno;
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int nivel) {
		this.tempo = nivel;
	}
	public int getNome() {
		return nome;
	}
	public void setNome(int nome) {
		this.nome = nome;
	}
	public Integer getVerticePai() {
		return verticePai;
	}
	public void setVerticePai(Integer verticePai) {
		this.verticePai = verticePai;
	}

	@Override
	public String toString() {
		return "Vertice{" +
				"cor='" + cor + '\'' +
				", tempo=" + tempo +
				", tempoRetorno=" + tempoRetorno +
				", nome=" + nome +
				", verticePai=" + verticePai +
				'}';
	}
}
