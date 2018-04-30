package trabalho;

import java.util.*;

public class Busca {

    private Grafo grafo;

    public Busca(Grafo grafo) {
        this.grafo = grafo;
    }

    public TreeSet<Vertice> buscaEmLagura(Integer vertice) {
        HashMap<Integer, Vertice> verticesMarcadores = new HashMap<>();
        for(Integer key: grafo.getVertices()) {
            verticesMarcadores.put(key, new Vertice(key));
        }
        verticesMarcadores.get(vertice).setDistancia(0);
        verticesMarcadores.get(vertice).setCor("cinza");
        Queue<Integer> fila = new LinkedList<>();
        fila.add(vertice);
        while(!fila.isEmpty()) {
            int verticePrimeiroFila = fila.poll();
            Collection<Integer> vertivesAdjascentes = grafo.getVerticesAdjacentes(verticePrimeiroFila);
            for(int verticeAtual: vertivesAdjascentes) {
                if(verticesMarcadores.get(verticeAtual).getCor() == "branco") {
                    fila.add(verticeAtual);
                    verticesMarcadores.get(verticeAtual).setCor("cinza");
                    verticesMarcadores.get(verticeAtual).setVerticePai(verticePrimeiroFila);
                    verticesMarcadores.get(verticeAtual).setDistancia(verticesMarcadores.get(verticePrimeiroFila).getDistancia() + 1);
                }
            }
            verticesMarcadores.get(verticePrimeiroFila).setCor("preto");
        }

        TreeSet<Vertice> verticesOrdenados = new TreeSet<Vertice>();
        verticesOrdenados.addAll(verticesMarcadores.values());
        return verticesOrdenados;
    }

    public TreeSet<Vertice> buscaEmProfundidade(Integer vertice) {
        int tempo = 0;
        HashMap<Integer, Vertice> verticesMarcadores = new HashMap<Integer, Vertice>();
        for(Integer key: grafo.getVertices()) {
            verticesMarcadores.put(key, new Vertice(key));
        }
        verticesMarcadores.get(vertice).setDistancia(tempo);
        Collection<Integer> vertivesAdjascentes = grafo.getVerticesAdjacentes(vertice);
        for(Integer verticeAtual: vertivesAdjascentes) {
            if(verticesMarcadores.get(verticeAtual).getCor() == "branco") {
                verticesMarcadores.get(verticeAtual).setVerticePai(vertice);
                tempo = buscaDSF(verticeAtual, verticesMarcadores, tempo);
            }
        }
        TreeSet<Vertice> verticesOrdenados = new TreeSet<Vertice>();
        verticesOrdenados.addAll(verticesMarcadores.values());
        return verticesOrdenados;
    }

    private int buscaDSF(Integer vertice, HashMap<Integer, Vertice> verticesMarcadores, int tempo) {
        verticesMarcadores.get(vertice).setCor("cinza");
        tempo += 1;
        verticesMarcadores.get(vertice).setDistancia(tempo);
        Collection<Integer> vertivesAdjascentes = grafo.getVerticesAdjacentes(vertice);
        for(Integer verticeAtual: vertivesAdjascentes) {
            if(verticesMarcadores.get(verticeAtual).getCor() == "branco") {
                verticesMarcadores.get(verticeAtual).setVerticePai(vertice);
                buscaDSF(verticeAtual, verticesMarcadores, tempo);
            }
        }

        verticesMarcadores.get(vertice).setCor("preto");
        return tempo;
    }

}
