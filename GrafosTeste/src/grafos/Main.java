package grafos;

import java.util.ArrayList;

public class Main {

    public void main(int tipoGrafo, int opcao, int posVertice, int vFinal) throws Exception {        
        
        String path = "src/grafos/test.txt";
        Algoritmos algo = new Algoritmos();
        FileManager fileM = new FileManager();
        ArrayList<String> vetorGrafo = fileM.stringReader(path);
        int qtdGrafo = Integer.parseInt(vetorGrafo.get(0));               
        String[] posicao = new String[qtdGrafo];
        int[][] matrixGrafo = new int[qtdGrafo+1][qtdGrafo+1];
        
        if(tipoGrafo == 1){
            
            InciMatrix grap3 = new InciMatrix(qtdGrafo);
            
            for(int i = 1; i<= qtdGrafo;i++){
                
            int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
            grap3.addVertice(id);
            
        }
            for(int i = 1; i<= qtdGrafo; i++){

            String[] vetorSplit = vetorGrafo.get(i).split(" ");
            int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);

            for(int j = 1; j < vetorSplit.length ; j++){
                String[] arestas = vetorSplit[j].split("-");

                int pos = Integer.parseInt(arestas[0]); // 0 é a posição
                double peso = Integer.parseInt(arestas[1].replace(";","")); // 1 é o peso
                int cmp = i - 1;

                grap3.adicionarAresta(id,pos,peso);
            }
            }
            
             if(opcao == 1){
                algo.buscaEmProfundidade(grap3);
            }else if(opcao == 2){
                ArrayList<Vertice> vertices = grap3.vertices();
                algo.buscaEmLargura(grap3,vertices.get(posVertice));
            }
            else if(opcao == 3){
                ArrayList<Vertice> vertices = grap3.vertices();
                algo.menorCaminho(grap3,vertices.get(posVertice),vertices.get(vFinal));
               
            }else if(opcao == 4){
                ArrayList<Vertice> vertices = grap3.vertices();
                algo.agmUsandoKruskall(grap3,vertices.get(posVertice));
            }
            
        }else if(tipoGrafo == 2){
            GrafoMatrixAdj grafo = new GrafoMatrixAdj(qtdGrafo);
            
            for(int i = 1; i<= qtdGrafo;i++){
            int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
            grafo.addVertice(id);
                  

            }
            for(int i = 1; i<= qtdGrafo; i++){

            String[] vetorSplit = vetorGrafo.get(i).split(" ");
            int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);

            for(int j = 1; j < vetorSplit.length ; j++){
                String[] arestas = vetorSplit[j].split("-");

                int pos = Integer.parseInt(arestas[0]); // 0 é a posição
                double peso = Integer.parseInt(arestas[1].replace(";","")); // 1 é o peso
                int cmp = i - 1;

                grafo.adicionarAresta(id,pos,peso);

            }
            }
            
            if(opcao == 1){
                algo.buscaEmProfundidade(grafo);
            }else if(opcao == 2){
                ArrayList<Vertice> vertices = grafo.vertices();
                algo.buscaEmLargura(grafo,vertices.get(posVertice));
            }
            else if(opcao == 3){
                ArrayList<Vertice> vertices = grafo.vertices();
                algo.menorCaminho(grafo,vertices.get(posVertice),vertices.get(vFinal));
            }else if(opcao == 4){
                ArrayList<Vertice> vertices = grafo.vertices();
                algo.agmUsandoKruskall(grafo,vertices.get(posVertice));
            }
        }else if(tipoGrafo == 3){
            ListAdj graph2 = new ListAdj(qtdGrafo);
                
            for(int i = 1; i<= qtdGrafo;i++){
            int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
            graph2.addVertice(id);
                }
            for(int i = 1; i<= qtdGrafo; i++){

            String[] vetorSplit = vetorGrafo.get(i).split(" ");
            int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);

            for(int j = 1; j < vetorSplit.length ; j++){
                String[] arestas = vetorSplit[j].split("-");

                int pos = Integer.parseInt(arestas[0]); // 0 é a posição
                double peso = Integer.parseInt(arestas[1].replace(";","")); // 1 é o peso
                int cmp = i - 1;

                graph2.adicionarAresta(id,pos,peso);
           
            }
            }
             if(opcao == 1){
                algo.buscaEmProfundidade(graph2);
            }else if(opcao == 2){
                ArrayList<Vertice> vertices = graph2.vertices();
                algo.buscaEmLargura(graph2,vertices.get(posVertice));
            }
            else if(opcao == 3){
                ArrayList<Vertice> vertices = graph2.vertices();
                algo.menorCaminho(graph2,vertices.get(posVertice),vertices.get(vFinal));
            }else if(opcao == 4){
                ArrayList<Vertice> vertices = graph2.vertices();
                algo.agmUsandoKruskall(graph2,vertices.get(posVertice));
            }
        }
        
    }

}
