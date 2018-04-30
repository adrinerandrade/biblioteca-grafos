package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public abstract class Grafo {

    private TreeSet<Integer> sequenciaGraus;
    private int numeroDeVertices;

    protected Grafo(BufferedReader fileReader) throws IOException {
        String line;
        boolean firstLine = true;
        // Para evitar processamento posterior. Perco em memória durante a execução do algoritmo, mas ganho muito em processamento.
        HashMap<Integer, Integer> graus = null;
        while ((line = fileReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                numeroDeVertices = Integer.parseInt(line);
                criarEstrutura(numeroDeVertices);
                graus = new HashMap<>(numeroDeVertices);
            } else {
                String[] verticesAdjacentes = line.split(" ");
                Integer v1 = Integer.parseInt(verticesAdjacentes[0]);
                Integer v2 = Integer.parseInt(verticesAdjacentes[1]);
                inicializaGrausSeNecessario(v1, graus);
                // Se for laço
                if (v1.equals(v2)) {
                    addLaco(v1);
                    // laço tem grau 2
                    somaGrauLaco(v1, graus);
                } else {
                    inicializaGrausSeNecessario(v2, graus);
                    addAdjacencia(v1, v2);
                    somaGrau(v1, graus);
                    addAdjacencia(v2, v1);
                    somaGrau(v2, graus);
                }
            }
        }
        calcularSequenciaGraus (graus);
        // Se o número de vértices total não foi gerado, temos vértices isolados.
        if (numeroDeVertices > getQuantidadeAtualVertices()) {
            gerarVerticesIsolados(numeroDeVertices, graus);
        }
    }

    public int getNumeroArestas() {
        return sequenciaGraus.stream().reduce(0, (sum, num) -> sum + num) / 2;
    }

    public TreeSet<Integer> getSequenciaGraus() {
        return sequenciaGraus;
    }

    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }
    
    public HashMap<Integer, Vertice> buscaListaAdjacencia(Integer vertice) {
		int tempo = 0;
    	HashMap<Integer, Vertice> verticesMarcadores = new HashMap<Integer, Vertice>();
    	for(Integer key: getVertives()) {
    		verticesMarcadores.put(key, new Vertice(key));
    	}
    	
    	int[] vertivesAdjascentes = getVerticesAdjacentes(vertice);
    	for(Integer verticeAtual: vertivesAdjascentes) {
    		if(verticesMarcadores.get(verticeAtual).getCor() == "branco") {
    			verticesMarcadores.get(verticeAtual).setVerticePai(vertice);
    			tempo = buscaDSFListaAdjacencia(verticeAtual, verticesMarcadores, tempo);
    		}
    	}
		return verticesMarcadores;
    }

	private int buscaDSFListaAdjacencia(Integer vertice, HashMap<Integer, Vertice> verticesMarcadores, int tempo) {
    	verticesMarcadores.get(vertice).setCor("cinza");
    	tempo += 1;
    	verticesMarcadores.get(vertice).setNivel(tempo);
    	int[] vertivesAdjascentes = getVerticesAdjacentes(vertice);
    	for(Integer verticeAtual: vertivesAdjascentes) {
    		if(verticesMarcadores.get(verticeAtual).getCor() == "branco") {
    			verticesMarcadores.get(verticeAtual).setVerticePai(vertice);
    			buscaDSFListaAdjacencia(verticeAtual, verticesMarcadores, tempo);
    		}
    	}
    	verticesMarcadores.get(vertice).setCor("preto");
    	return tempo;
    }

    private void somaGrau(Integer nomeVertice, HashMap<Integer, Integer> graus) {
        graus.put(nomeVertice, graus.get(nomeVertice) + 1);
    }

    private void somaGrauLaco(Integer nomeVertice, HashMap<Integer, Integer> graus) {
        graus.put(nomeVertice, graus.get(nomeVertice) + 2);
    }

    private void inicializaGrausSeNecessario(Integer vertice, HashMap<Integer, Integer> graus) {
        if (!graus.containsKey(vertice)) {
            graus.put(vertice, 0);
        }
    }

//    protected abstract Vertice[] getVerticesAdjacentes(int vertice);
    
    protected abstract void criarEstrutura(int quantidadeVertices);

    protected abstract void addAdjacencia(Integer u, Integer v);

    protected abstract void addLaco(Integer u);

    protected abstract void addVerticeIsolado(Integer nomeVertice);

    protected abstract int getQuantidadeAtualVertices();

    protected abstract boolean contemVertice(Integer nomeVertice);

    public abstract double getConsumoDeBytesEmMemoria();
    
    protected abstract int[] getVertives();
    
    protected abstract int[] getVerticesAdjacentes(Integer vertice);

    private void gerarVerticesIsolados(int numeroDeVertices, HashMap<Integer, Integer> graus) {
        int paraAcrescentar = numeroDeVertices - getQuantidadeAtualVertices();
        int index = getQuantidadeAtualVertices();
        while (paraAcrescentar > 0) {
            Integer nomeVertice = ++index;
            if (!contemVertice(nomeVertice)) {
                --paraAcrescentar;
                addVerticeIsolado(nomeVertice);
                graus.put(nomeVertice, 0);
            }
        }
    }

    private void calcularSequenciaGraus(HashMap<Integer, Integer> graus) {
        TreeSet<Integer> sequenciaGraus = new TreeSet<>((grau1, grau2) -> grau1 >= grau2 ? 1 : -1);
        for (Integer grau : graus.values()) {
            sequenciaGraus.add(grau);
        }
        this.sequenciaGraus = sequenciaGraus;
    }

}
