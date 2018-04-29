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
    protected void addAdjacencia(String u, String v) {
        int intU = Integer.parseInt(u);
        int intV = Integer.parseInt(v);
        matriz[intU][intV] += 1;
        matriz[intV][intU] += 1;
    }

    @Override
    protected void addLaco(String u) {
        int intU = Integer.parseInt(u);
        matriz[intU][intU] += 2;
    }

    @Override
    protected void addVerticeIsolado(String nomeVertice) {

    }

    @Override
    protected int getQuantidadeAtualVertices() {
        return 0;
    }

    @Override
    protected boolean contemVertice(String nomeVertice) {
        return false;
    }
}
