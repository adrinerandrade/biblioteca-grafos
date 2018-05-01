package trabalho;

import java.util.*;

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
                if (verticesMarcadores.get(verticeAtual).getCor().equals("branco")) {
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

    public Collection<VerticeProfundidade> buscaEmProfundidade(Integer vertice) {
        Collection<Integer> vertices = grafo.getVertices();
        HashMap<Integer, VerticeProfundidade> verticesMarcadores = new HashMap<>(vertices.size());
        for (Integer key : vertices) {
            verticesMarcadores.put(key, new VerticeProfundidade(key));
        }

        // Para evitar StackOverflow não será utlizado recursão.
        // Será trabalhado com referências
        HashMap<Integer, LinkedList<Integer>> verticesNaoVisitadosControle = new HashMap<>(vertices.size());
        int tempo = 0;

        // Movendo elemento escolhido para primeira posição
        LinkedList<Integer> verticesOrdem = new LinkedList<>(vertices);
        verticesOrdem.remove(vertice);
        verticesOrdem.addFirst(vertice);
        for (Integer v : verticesOrdem) {
            boolean ehRetorno = false;
            VerticeProfundidade verticePai = null;
            VerticeProfundidade verticeAtual = verticesMarcadores.get(v);
            if (verticeAtual.getCor().equals("branco")) {
                do {
                    if (!ehRetorno) {
                        verticeAtual.setCor("cinza");
                        verticeAtual.setTempo(++tempo);
                        verticeAtual.setVerticePai(verticePai != null ? verticePai.getNome() : null);
                        verticesNaoVisitadosControle.put(verticeAtual.getNome(), new LinkedList<>(grafo.getVerticesAdjacentes(verticeAtual.getNome())));
                    }
                    LinkedList<Integer> verticesNaoVisitados = verticesNaoVisitadosControle.get(verticeAtual.getNome());

                    boolean setPreto = true;
                    while (!verticesNaoVisitados.isEmpty()) {
                        VerticeProfundidade proxVertice = verticesMarcadores.get(verticesNaoVisitados.pop());
                        // Não visitar vértices que já estejam sendo processados
                        if (proxVertice.getCor().equals("branco")) {
                            verticePai = verticeAtual;
                            verticeAtual = proxVertice;
                            setPreto = false;
                            ehRetorno = false;
                            break;
                        }
                    }
                    if (setPreto) {
                        verticeAtual.setTempoRetorno(++tempo);
                        verticeAtual.setCor("preto");
                        verticeAtual = verticePai;
                        ehRetorno = true;
                        verticePai = verticePai != null ? verticesMarcadores.get(verticePai.getVerticePai()) : null;
                    }
                } while (verticeAtual != null);
            }
        }

        return verticesMarcadores.values();
    }

}
