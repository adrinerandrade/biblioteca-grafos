package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

//./arquivos_teste/grafo1.txt

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

    public int getDiametro() {
    	int maiorDistancia = 0;
    	VerticeLargura verticeAtual;
    	for(int vertice: getVertices()) {
    		Collection<VerticeLargura> lista = new Busca(this).buscaEmLagura(vertice);
            TreeSet<VerticeLargura> ordenarMaiorDistancia = new TreeSet<>((v1, v2) -> v1.getNivel() < v2.getNivel() ? 1 : -1);
            for (VerticeLargura obj : lista) {
               ordenarMaiorDistancia.add(obj);
            }
   			verticeAtual = ordenarMaiorDistancia.first();
    		if(verticeAtual.getNivel() > maiorDistancia) {
    			maiorDistancia = verticeAtual.getNivel();
    		}
    		
    	}
    	return maiorDistancia;
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
    
    protected abstract void criarEstrutura(int quantidadeVertices);

    protected abstract void addAdjacencia(Integer u, Integer v);

    protected abstract void addLaco(Integer u);

    protected abstract void addVerticeIsolado(Integer nomeVertice);

    protected abstract int getQuantidadeAtualVertices();

    protected abstract boolean contemVertice(Integer nomeVertice);

    public abstract long getConsumoMemoria();
    
    protected abstract Collection<Integer> getVertices();
    
    protected abstract Collection<Integer> getVerticesAdjacentes(Integer vertice);

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
