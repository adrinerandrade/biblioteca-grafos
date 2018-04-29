package trabalho;

import java.util.TreeSet;

public interface Grafo {

    int getNumeroVertices();

    int getNumeroArestas();

    TreeSet<Integer> getSequenciaGraus();

    double getConsumoDeBytesEmMemoria();

}
