package trabalho;

import java.io.*;
import java.util.*;

public class BibliotecaGrafos {

    private final BufferedReader fileReader;

    public BibliotecaGrafos(String path) throws FileNotFoundException {
        try {
            this.fileReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o caminho do arquivo que contém o grafo:");
        String path;
        BibliotecaGrafos biblioteca = null;
        do {
             path = scanner.nextLine();
            try {
                biblioteca = new BibliotecaGrafos(path);
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo informado não encontrado, informe um novo caminho:");
            }
        } while (biblioteca == null);

        System.out.println("Qual tipo de representação você deseja?");
        TipoRepresentacaoGrafo tipoRepresentacao = null;
        do {
            for (TipoRepresentacaoGrafo tipo : TipoRepresentacaoGrafo.values()) {
                System.out.println(String.format("%s) %s", tipo.opcao, tipo.name().replaceAll("_", " ")));
            }
            try {
                tipoRepresentacao = TipoRepresentacaoGrafo.acharPorOpcao(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Opção não encontrada, aqui estão as opções disponíveis:");
            }
        } while(tipoRepresentacao == null);

        biblioteca.escreverOutput(biblioteca.criarGrafo(tipoRepresentacao));
    }

    private Grafo criarGrafo(TipoRepresentacaoGrafo tipo) {
        try (fileReader) {
            Grafo grafo = new ListaAdjacencia(fileReader);
            System.out.println("Grafo processado com sucesso!");
            return grafo;
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao fechar stream do arquivo.", e);
        }
    }

    private void escreverOutput(Grafo grafo) {
        try (FileWriter fileWriter = new FileWriter("./output/output.txt")) {

            fileWriter.write(String.format("Número de vértices: %s\n", grafo.getNumeroDeVertices()));
            fileWriter.write(String.format("Número de arestas: %s\n", grafo.getNumeroArestas()));
            fileWriter.write(String.format("Sequência de graus: %s\n", grafo.getSequenciaGraus()));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever o arquivo", e);
        }
    }

    private enum TipoRepresentacaoGrafo {

        LISTA_DE_ADJACENCIA("a"),
        MATRIZ_DE_ADJACENCIA("b");

        private String opcao;

        TipoRepresentacaoGrafo(String opcao) {
            this.opcao = opcao;
        }

        static TipoRepresentacaoGrafo acharPorOpcao(String opcao) {
            for (TipoRepresentacaoGrafo tipo : values()) {
                if (tipo.opcao.equals(opcao))
                    return tipo;
            }
            throw new IllegalArgumentException("Opção não encontrada;");
        }

    }

}
