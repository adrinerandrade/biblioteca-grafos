package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class ListaAdjacencia implements Grafo {

    private HashMap<String, LinkedList<String>> vertices;
    private TreeSet<Integer> sequenciaGraus;

    @Override
    public int getNumeroVertices() {
        return vertices.size();
    }

    @Override
    public int getNumeroArestas() {
        return sequenciaGraus.stream().reduce(0, (sum, num) -> sum + num) / 2;
    }

    @Override
    public TreeSet<Integer> getSequenciaGraus() {
        return sequenciaGraus;
    }

    @Override
    public double getConsumoDeBytesEmMemoria() {
        return 0;
    }

    public ListaAdjacencia(BufferedReader fileReader) throws IOException {
        String line;
        boolean firstLine = true;
        int numeroDeVertices = 0;
        // Para evitar processamento posterior. Perco em memória mas ganho muito em processamento.
        HashMap<String, Integer> graus = null;
        while ((line = fileReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                numeroDeVertices = Integer.parseInt(line);
                vertices = new HashMap<>(numeroDeVertices);
                graus = new HashMap<>(numeroDeVertices);
            } else {
                String[] verticesAdjacentes = line.split(" ");
                String v1 = verticesAdjacentes[0];
                String v2 = verticesAdjacentes[1];
                obtemOuCriaVertice(v1, vertices, graus).add(v2);
                somaGrau(v1, graus);
                // Se não for um ciclo
                if (!v1.equals(v2)) {
                    obtemOuCriaVertice(v2, vertices, graus).add(v1);
                    somaGrau(v2, graus);
                } else {
                    // ciclo tem grau 2
                    somaGrau(v1, graus);
                }
            }
        }
        calcularSequenciaGraus (graus);
        // Se o número de vértices total não foi gerado, temos vértices isolados.
        if (numeroDeVertices > vertices.size()) {
            gerarVerticesIsolados(numeroDeVertices, vertices, graus);
        }
    }

    private LinkedList<String> obtemOuCriaVertice(String nomeVertice, HashMap<String, LinkedList<String>> listaAdjacencia, HashMap<String, Integer> graus) {
        LinkedList<String> vertices = listaAdjacencia.get(nomeVertice);
        if (vertices == null) {
            vertices = new LinkedList<>();
            adicionarVertice(nomeVertice, listaAdjacencia, vertices, graus);
        }
        return vertices;
    }

    private void gerarVerticesIsolados(int numeroDeVertices, HashMap<String, LinkedList<String>> listaAdjacencia, HashMap<String, Integer> graus) {
        int paraAcrescentar = numeroDeVertices - listaAdjacencia.size();
        int index = listaAdjacencia.size();
        while (paraAcrescentar > 0) {
            String nomeVertice = String.valueOf(++index);
            if (!listaAdjacencia.containsKey(nomeVertice)) {
                --paraAcrescentar;
                adicionarVertice(nomeVertice, listaAdjacencia, new LinkedList<>(), graus);
            }
        }
    }

    private void adicionarVertice(String nomeVertice, HashMap<String, LinkedList<String>> listaAdjacencia, LinkedList<String> adjacenciaInfo, HashMap<String, Integer> graus) {
        listaAdjacencia.put(nomeVertice, adjacenciaInfo);
        graus.put(nomeVertice, 0);
    }

    private void somaGrau(String nomeVertice, HashMap<String, Integer> graus) {
        graus.put(nomeVertice, graus.get(nomeVertice) + 1);
    }

    private void calcularSequenciaGraus(HashMap<String, Integer> graus) {
        TreeSet<Integer> sequenciaGraus = new TreeSet<>((grau1, grau2) -> grau1 >= grau2 ? 1 : -1);
        for (Integer grau : graus.values()) {
            sequenciaGraus.add(grau);
        }
        this.sequenciaGraus = sequenciaGraus;
    }

    @Override
    public String toString() {
        return "ListaAdjacencia{" +
                "numeroVertices=" + getNumeroVertices() +
                ", vertices=" + vertices +
                '}';
    }

}
