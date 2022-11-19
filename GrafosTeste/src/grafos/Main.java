package grafos;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        String path = "src/grafos/test.txt";

        FileManager fileM = new FileManager();
        ArrayList<String> vetorGrafo = fileM.stringReader(path);
        int qtdGrafo = Integer.parseInt(vetorGrafo.get(0));
        System.out.println("Qtd Grafo: " + qtdGrafo);
        String[] posicao = new String[qtdGrafo];
        int[][] matrixGrafo = new int[qtdGrafo+1][qtdGrafo+1];



        for(int i = 1; i<= qtdGrafo+1; i++){

            String[] vetorSplit = vetorGrafo.get(i).split(" ");

            for(int j = 1; j < vetorSplit.length ; j++){
                String[] arestas = vetorSplit[j].split("-");

                int pos = Integer.parseInt(arestas[0]); // 0 é a posição
                int peso = Integer.parseInt(arestas[1].replace(";","")); // 1 é o peso
                int cmp = i - 1;
                matrixGrafo[cmp][pos] = peso;

                System.out.println("valor: " + peso);
            }
            System.out.println(" ");
        }

    }

}
