package trabalho;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class MatrizAdjacencia extends Grafo {

    private HashMap<String, Integer> mapeamentoIndex;
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

    }

    @Override
    protected void addAdjacencia(String u, String v) {

    }

    @Override
    protected void addLaco(String u) {
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
