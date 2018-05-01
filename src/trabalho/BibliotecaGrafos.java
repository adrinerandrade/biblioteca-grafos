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
        
        TipoDeBusca tipoBusca= null;
        do {
            for (TipoDeBusca tipo : TipoDeBusca.values()) {
                System.out.println(String.format("%s) %s", tipo.opcao, tipo.name().replaceAll("_", " ")));
            }
            try {
            	tipoBusca = TipoDeBusca.acharPorOpcao(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Opção não encontrada, aqui estão as opções disponíveis:");
            }
        } while(tipoBusca == null);
        
        if(tipoBusca.equals(TipoDeBusca.BUSCA_EM_LARGURA)) {
        	int verticeInicial = 0;
        	do {
        		System.out.println("Informe o vertice inicial");            
        		try {
        			verticeInicial = Integer.parseInt(scanner.nextLine());
        		} catch (IllegalArgumentException e) {
        			System.out.println("Valor invalido. Apenas valore inteiros são permitidos");
        		}
        	} while(verticeInicial == 0);
        	Grafo grafo = biblioteca.criarGrafo(tipoRepresentacao);
        	Busca busca = new Busca(grafo);
        	Collection buscaLargura = busca.buscaEmLagura(verticeInicial);
        	biblioteca.escreverOutput(grafo, buscaLargura);
        } else if(tipoBusca.equals(TipoDeBusca.BUSCA_EM_PROFUNDIDADE)) {
        	int verticeInicial = 0;
        	do {
        		System.out.println("Informe o vertice inicial");            
        		try {
        			verticeInicial = Integer.parseInt(scanner.nextLine());
        		} catch (IllegalArgumentException e) {
        			System.out.println("Valor invalido. Apenas valore inteiros são permitidos");
        		}
        	} while(verticeInicial == 0);
        	Grafo grafo = biblioteca.criarGrafo(tipoRepresentacao);
        	Busca busca = new Busca(grafo);
        	Collection buscaLargura = busca.buscaEmProfundidade(verticeInicial);
        	biblioteca.escreverOutput(grafo, buscaLargura);
        } else if(tipoBusca.equals(TipoDeBusca.DIAMETRO_DO_GRAFO)) {
        	Grafo grafo = biblioteca.criarGrafo(tipoRepresentacao);
        	int diametro = grafo.getDiametro();
        	biblioteca.escreverOutput(grafo, diametro);
        }        
    }
    
    

    private Grafo criarGrafo(TipoRepresentacaoGrafo tipo) {
        try (fileReader) {
            Grafo grafo;
            if (TipoRepresentacaoGrafo.LISTA_DE_ADJACENCIA.equals(tipo)) {
                grafo = new ListaAdjacencia(fileReader);
            } else {
                grafo = new MatrizAdjacencia(fileReader);
            }
            System.out.println("Grafo processado com sucesso!");
            return grafo;
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao fechar stream do arquivo.", e);
        }
    }

    private void escreverOutput(Grafo grafo, Collection busca) {
        try (FileWriter fileWriter = new FileWriter("./output/output.txt")) {

            fileWriter.write(String.format("Número de vértices: %s\n", grafo.getNumeroDeVertices()));
            fileWriter.write(String.format("Número de arestas: %s\n", grafo.getNumeroArestas()));
            fileWriter.write(String.format("Sequência de graus: %s\n", grafo.getSequenciaGraus()));
            
            fileWriter.write("Arvore de busca: ");
            busca.forEach(vertice -> {
            	try {
					fileWriter.write(vertice.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}            	
            });
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever o arquivo", e);
        }
    }
    
    private void escreverOutput(Grafo grafo, int diametro) {
        try (FileWriter fileWriter = new FileWriter("./output/output.txt")) {

            fileWriter.write(String.format("Número de vértices: %s\n", grafo.getNumeroDeVertices()));
            fileWriter.write(String.format("Número de arestas: %s\n", grafo.getNumeroArestas()));
            fileWriter.write(String.format("Sequência de graus: %s\n", grafo.getSequenciaGraus()));
            fileWriter.write(String.format("Diamentro do grafo: %s\n", diametro));
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
    
    private enum TipoDeBusca {

        BUSCA_EM_PROFUNDIDADE("a"),
        BUSCA_EM_LARGURA("b"),
        DIAMETRO_DO_GRAFO("c");
    	

        private String opcao;

        TipoDeBusca(String opcao) {
            this.opcao = opcao;
        }

        static TipoDeBusca acharPorOpcao(String opcao) {
            for (TipoDeBusca tipo : values()) {
                if (tipo.opcao.equals(opcao))
                    return tipo;
            }
            throw new IllegalArgumentException("Opção não encontrada;");
        }

    }

}
