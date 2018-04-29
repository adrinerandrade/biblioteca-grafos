package trabalho;

import java.io.BufferedReader;
import java.io.IOException;

public class MatrizAdjacencia extends Grafo {

    private int[][] matriz;

    public MatrizAdjacencia(BufferedReader fileReader) throws IOException {
        super(fileReader);
    }

    @Override
    public double getConsumoDeBytesEmMemoria() {
        return 0;
    }

    @Override
    protected void criarEstrutura(int quantidadeVertices) {
        matriz = new int[quantidadeVertices][quantidadeVertices];
    }

    @Override
    protected void addAdjacencia(Integer u, Integer v) {
        matriz[u][v] += 1;
        matriz[v][u] += 1;
    }

    @Override
    protected void addLaco(Integer u) {
        matriz[u][u] += 2;
    }

    @Override
    protected void addVerticeIsolado(Integer nomeVertice) {

    }

    @Override
    protected int getQuantidadeAtualVertices() {
        return 0;
    }

    @Override
    protected boolean contemVertice(Integer nomeVertice) {
        return false;
    }
}
