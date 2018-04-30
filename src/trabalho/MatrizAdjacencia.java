package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MatrizAdjacencia extends Grafo {

    private int[][] matriz;

    public MatrizAdjacencia(BufferedReader fileReader) throws IOException {
        super(fileReader);
    }

    @Override
    public long getConsumoMemoria() {
        return ObtensorTamanhoEmMemoria.tamanhoObjetoEmMemoria(matriz);
    }

    @Override
    protected Collection<Integer> getVertices() {
        return IntStream.rangeClosed(1, matriz.length).boxed().collect(Collectors.toList());
    }

    @Override
    protected Collection<Integer> getVerticesAdjacentes(Integer vertice) {
        LinkedList<Integer> adjacentes = new LinkedList<>();
        int verticeIndex = vertice - 1;
        for (int i = 1; i <= matriz[verticeIndex].length; i++) {
            if (matriz[verticeIndex][i - 1] > 0) {
                adjacentes.add(i);
            }
        }
        return adjacentes;
    }

    @Override
    protected void criarEstrutura(int quantidadeVertices) {
        matriz = new int[quantidadeVertices][quantidadeVertices];
    }

    @Override
    protected void addAdjacencia(Integer u, Integer v) {
        int uIndex = u - 1;
        int vIndex = v - 1;
        matriz[uIndex][vIndex] += 1;
        matriz[vIndex][uIndex] += 1;
    }

    @Override
    protected void addLaco(Integer u) {
        int uIndex = u - 1;
        matriz[uIndex][uIndex] += 2;
    }

    @Override
    protected void addVerticeIsolado(Integer vertice) {
        if (vertice > matriz.length && vertice <= matriz.length) {
            matriz[vertice - 1] = new int[matriz.length];
        }
    }

    @Override
    protected int getQuantidadeAtualVertices() {
        return matriz.length;
    }

    @Override
    protected boolean contemVertice(Integer vertice) {
        return matriz.length > vertice;
    }
}
