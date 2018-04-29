package trabalho;

public class Vertice {
	private int nivel;
	private String cor;
	private int nome;
	private Vertice[] verticesAdjacentes;
	
	public Vertice[] getVerticesAdjacentes() {
		return verticesAdjacentes;
	}



	public void setVerticesAdjacentes(Vertice[] verticesAdjacentes) {
		this.verticesAdjacentes = verticesAdjacentes;
	}



	public int getNivel() {
		return nivel;
	}



	public void setNivel(int nivel) {
		this.nivel = nivel;
	}



	public String getCor() {
		return cor;
	}



	public void setCor(String cor) {
		this.cor = cor;
	}



	public int getNome() {
		return nome;
	}



	public void setNome(int nome) {
		this.nome = nome;
	}



	public Vertice(int nome) {
		this.cor = "branco";
		this.nivel = 0;
		this.nome = nome;
	}
}
