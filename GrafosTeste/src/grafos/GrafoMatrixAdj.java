package grafos;

import java.util.ArrayList;

public class GrafoMatrixAdj {

    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> vertices;
    private int adjMat[][];

    public GrafoMatrixAdj(int qtdVertices){
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.adjMat = new int[qtdVertices][qtdVertices];
    }

    public void addVertice(){

    }
}
