package trabalho;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BibliotecaGrafos {

    public static void main(String[] args) {
        BibliotecaGrafos biblioteca = new BibliotecaGrafos();
        System.out.println(biblioteca.criarGrafoDeArquivo("C:/git/grafos/arquivos_teste/grafo1.txt"));
    }

    public List<Vertice> criarGrafoDeArquivo(String path) {
        HashMap<String, Vertice> vertices = new HashMap<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean firstLine = true;
            int numeroDeVertices = 0;
            while ((line = fileReader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    numeroDeVertices = Integer.parseInt(line);
                } else {
                    String[] adjacencia = line.split(" ");
                    Vertice v1 = getVertice(adjacencia[0], vertices);
                    Vertice v2 = getVertice(adjacencia[1], vertices);
                    v1.addAdjacencia(v2);
                }
            }
            if (numeroDeVertices > vertices.size()) {
                vertices.putAll(gerarNovosVertice(numeroDeVertices - vertices.size(), vertices));
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(String.format("Arquivo de caminho '%s' não encontrado.", path));
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao ler arquivo");
        }
        return new ArrayList<>(vertices.values());
    }

    private Vertice getVertice(String nome, HashMap<String, Vertice> vertices) {
        if (vertices.containsKey(nome)) {
            return vertices.get(nome);
        }
        Vertice vertice = new Vertice(nome);
        vertices.put(nome, vertice);
        return vertice;
    }

    private HashMap<String, Vertice> gerarNovosVertice(int quantidade, HashMap<String, Vertice> vertices) {
        HashMap<String, Vertice> novosVertices = new HashMap<>();
        int index = vertices.size();
        while (quantidade > 0) {
            String novoNome = String.valueOf(++index);
            if (!vertices.containsKey(novoNome)) {
                --quantidade;
                novosVertices.put(novoNome, new Vertice(novoNome));
            }
        }
        return novosVertices;
    }

}
