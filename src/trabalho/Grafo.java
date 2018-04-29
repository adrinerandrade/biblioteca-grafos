package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

public abstract class Grafo {

    private TreeSet<Integer> sequenciaGraus;
    private int numeroDeVertices;

    protected Grafo(BufferedReader fileReader) throws IOException {
        String line;
        boolean firstLine = true;
        // Para evitar processamento posterior. Perco em memória mas ganho muito em processamento.
        HashMap<String, Integer> graus = null;
        while ((line = fileReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                numeroDeVertices = Integer.parseInt(line);
                criarEstrutura(numeroDeVertices);
                graus = new HashMap<>(numeroDeVertices);
            } else {
                String[] verticesAdjacentes = line.split(" ");
                String v1 = verticesAdjacentes[0];
                String v2 = verticesAdjacentes[1];
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

    private void somaGrau(String nomeVertice, HashMap<String, Integer> graus) {
        graus.put(nomeVertice, graus.get(nomeVertice) + 1);
    }

    private void somaGrauLaco(String nomeVertice, HashMap<String, Integer> graus) {
        graus.put(nomeVertice, graus.get(nomeVertice) + 2);
    }

    private void inicializaGrausSeNecessario(String vertice, HashMap<String, Integer> graus) {
        if (!graus.containsKey(vertice)) {
            graus.put(vertice, 0);
        }
    }

    protected abstract void criarEstrutura(int quantidadeVertices);

    protected abstract void addAdjacencia(String u, String v);

    protected abstract void addLaco(String u);

    protected abstract void addVerticeIsolado(String nomeVertice);

    protected abstract int getQuantidadeAtualVertices();

    protected abstract boolean contemVertice(String nomeVertice);

    public abstract double getConsumoDeBytesEmMemoria();

    private void gerarVerticesIsolados(int numeroDeVertices, HashMap<String, Integer> graus) {
        int paraAcrescentar = numeroDeVertices - getQuantidadeAtualVertices();
        int index = getQuantidadeAtualVertices();
        while (paraAcrescentar > 0) {
            String nomeVertice = String.valueOf(++index);
            if (!contemVertice(nomeVertice)) {
                --paraAcrescentar;
                addVerticeIsolado(nomeVertice);
                graus.put(nomeVertice, 0);
            }
        }
    }

    private void calcularSequenciaGraus(HashMap<String, Integer> graus) {
        TreeSet<Integer> sequenciaGraus = new TreeSet<>((grau1, grau2) -> grau1 >= grau2 ? 1 : -1);
        for (Integer grau : graus.values()) {
            sequenciaGraus.add(grau);
        }
        this.sequenciaGraus = sequenciaGraus;
    }

}
