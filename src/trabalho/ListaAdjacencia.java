package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class ListaAdjacencia extends Grafo {

    private HashMap<Integer, LinkedList<Integer>> vertices;

    @Override
    public long getConsumoMemoria() {
        return ObtensorTamanhoEmMemoria.tamanhoObjetoEmMemoria(vertices);
    }

    @Override
    protected Collection<Integer> getVertices() {
        return vertices.keySet();
    }

    @Override
    protected Collection<Integer> getVerticesAdjacentes(Integer vertice) {
        return vertices.get(vertice);
    }

    public ListaAdjacencia(BufferedReader fileReader) throws IOException {
        super(fileReader);
    }

    @Override
    protected void criarEstrutura(int quantidadeVertices) {
        vertices = new HashMap<>(quantidadeVertices);
    }

    @Override
    protected void addAdjacencia(Integer u, Integer v) {
        obtemOuCriaVertice(u).add(v);
    }

    @Override
    protected void addLaco(Integer u) {
        obtemOuCriaVertice(u).add(u);
    }

    @Override
    protected void addVerticeIsolado(Integer nomeVertice) {
        vertices.put(nomeVertice, new LinkedList<>());
    }

    @Override
    protected int getQuantidadeAtualVertices() {
        return vertices.size();
    }

    @Override
    protected boolean contemVertice(Integer nomeVertice) {
        return vertices.containsKey(nomeVertice);
    }

    private LinkedList<Integer> obtemOuCriaVertice(Integer nomeVertice) {
        LinkedList<Integer> adjacentes = vertices.get(nomeVertice);
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
