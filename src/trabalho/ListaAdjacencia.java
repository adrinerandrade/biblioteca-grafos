package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class ListaAdjacencia extends Grafo {

    private HashMap<String, LinkedList<String>> vertices;

    @Override
    public double getConsumoDeBytesEmMemoria() {
        return 0;
    }

    public ListaAdjacencia(BufferedReader fileReader) throws IOException {
        super(fileReader);
    }

    @Override
    protected void criarEstrutura(int quantidadeVertices) {
        vertices = new HashMap<>(quantidadeVertices);
    }

    @Override
    protected void addAdjacencia(String u, String v) {
        obtemOuCriaVertice(u).add(v);
    }

    @Override
    protected void addVerticeIsolado(String nomeVertice) {
        vertices.put(nomeVertice, new LinkedList<>());
    }

    @Override
    protected int getQuantidadeAtualVertices() {
        return vertices.size();
    }

    @Override
    protected boolean contemVertice(String nomeVertice) {
        return vertices.containsKey(nomeVertice);
    }

    private LinkedList<String> obtemOuCriaVertice(String nomeVertice) {
        LinkedList<String> adjacentes = vertices.get(nomeVertice);
        if (adjacentes == null) {
            adjacentes = new LinkedList<>();
            vertices.put(nomeVertice, adjacentes);
        }
        return adjacentes;
    }

    @Override
    public String toString() {
        return "ListaAdjacencia{" +
                "numeroVertices=" + getNumeroDeVertices() +
                ", vertices=" + vertices +
                '}';
    }

}
