package trabalho;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Busca {

    private Grafo grafo;

    public Busca(Grafo grafo) {
        this.grafo = grafo;
    }

    public Collection<VerticeLargura> buscaEmLagura(Integer vertice) {
        HashMap<Integer, VerticeLargura> verticesMarcadores = new HashMap<>();
        for (Integer key : grafo.getVertices()) {
            verticesMarcadores.put(key, new VerticeLargura(key));
        }
        verticesMarcadores.get(vertice).setNivel(0);
        verticesMarcadores.get(vertice).setCor("cinza");
        Queue<Integer> fila = new LinkedList<>();
        fila.add(vertice);
        while (!fila.isEmpty()) {
            int verticePrimeiroFila = fila.poll();
            Collection<Integer> vertivesAdjascentes = grafo.getVerticesAdjacentes(verticePrimeiroFila);
            for (int verticeAtual : vertivesAdjascentes) {
                if (verticesMarcadores.get(verticeAtual).getCor() == "branco") {
                    fila.add(verticeAtual);
                    verticesMarcadores.get(verticeAtual).setCor("cinza");
                    verticesMarcadores.get(verticeAtual).setVerticePai(verticePrimeiroFila);
                    verticesMarcadores.get(verticeAtual).setNivel(verticesMarcadores.get(verticePrimeiroFila).getNivel() + 1);
                }
            }
            verticesMarcadores.get(verticePrimeiroFila).setCor("preto");
        }
        return verticesMarcadores.values();
    }
//    ./arquivos_teste/grafo1.txt
    public Collection<VerticeProfundidade> buscaEmProfundidade(Integer vertice) {
        int tempo = 0;
        HashMap<Integer, VerticeProfundidade> verticesMarcadores = new HashMap<Integer, VerticeProfundidade>();
        for (Integer key : grafo.getVertices()) {
            verticesMarcadores.put(key, new VerticeProfundidade(key));
        }
        verticesMarcadores.get(vertice).setCor("cinza");
        verticesMarcadores.get(vertice).setDistancia(tempo);
        Collection<Integer> vertivesAdjascentes = grafo.getVerticesAdjacentes(vertice);
        for (Integer verticeAtual : vertivesAdjascentes) {
            if (verticesMarcadores.get(verticeAtual).getCor() == "branco") {
                verticesMarcadores.get(verticeAtual).setVerticePai(vertice);
                tempo = buscaDSF(verticeAtual, verticesMarcadores, tempo);
            }
        }
        tempo += 1;
        verticesMarcadores.get(vertice).setDistanciaRetorno(tempo);
        verticesMarcadores.get(vertice).setCor("preto");
        return verticesMarcadores.values();
    }

    private int buscaDSF(Integer vertice, HashMap<Integer, VerticeProfundidade> verticesMarcadores, int tempo) {
        verticesMarcadores.get(vertice).setCor("cinza");
        tempo += 1;
        verticesMarcadores.get(vertice).setDistancia(tempo);
        Collection<Integer> vertivesAdjascentes = grafo.getVerticesAdjacentes(vertice);
        for (Integer verticeAtual : vertivesAdjascentes) {
            if (verticesMarcadores.get(verticeAtual).getCor() == "branco") {
                verticesMarcadores.get(verticeAtual).setVerticePai(vertice);
                tempo = buscaDSF(verticeAtual, verticesMarcadores, tempo);
            }
        }
        tempo += 1;
        verticesMarcadores.get(vertice).setDistanciaRetorno(tempo);
        verticesMarcadores.get(vertice).setCor("preto");
        return tempo;
    }

}
