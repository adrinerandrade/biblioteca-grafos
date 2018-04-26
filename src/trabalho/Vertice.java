package trabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertice {

    private String nome;
    private List<Vertice> adjacencias = new ArrayList<>();

    public Vertice(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void addAdjacencia(Vertice vertice) {
        adjacencias.add(vertice);
        vertice.adjacencias.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return Objects.equals(nome, vertice.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
