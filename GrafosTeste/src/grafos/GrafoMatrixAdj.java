package grafos;

import java.util.ArrayList;
import java.util.Collection;

public class GrafoMatrixAdj implements Grafo {

    private final Aresta[][] arestas;
    private final ArrayList<Vertice> vertices;
    private int qtdVertices;
    private int tempoInicial = 0;
    private int tempoFinal = 0;
   
    
    public String getTempos(){
        String resultado = "";
        for(Vertice v: vertices){
            resultado  += v.getTempo() + " " + v.getTempoFinal();
        }
        return resultado;
    }
    public GrafoMatrixAdj(int qtdVertices){
        this.arestas = new Aresta[qtdVertices][qtdVertices];
        this.vertices = new ArrayList<>();
        this.qtdVertices = qtdVertices;

    }
    public Aresta encontraAresta(int i, int j){
        if(arestas[i][j] != null){
            return arestas[i][j];
        }
        else{
            return null;
        }
    }
    public int getValue(int i,int j){
        if(arestas[i][j] != null) {
            return (int) arestas[i][j].peso();
        }else{
            return Integer.MAX_VALUE;
        }
    }
    public void resetarCor(){
        for(Vertice v: vertices){
            v.setCor("branco");
        }
    }
    public int getQtdVertices(){return this.qtdVertices;}
    @Override
    public void adicionarAresta(Vertice origem, Vertice destino) throws Exception {
        int a = origem.id();
        int b = destino.id();
        arestas[a][b] = new Aresta(origem,destino);
        arestas[b][a] = new Aresta(destino,origem);
    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception {

    }

    public void addVertice(int id){
        vertices.add(new Vertice(id));
    }
    public void adicionarAresta(int origem, int destino, double peso) throws Exception {

        Vertice a = vertices.get(origem);
        Vertice b = vertices.get(destino);

        arestas[origem][destino] = new Aresta(a,b,peso);

    }

    @Override
    public boolean existeAresta(Vertice origem, Vertice destino) {
        return false;
    }

    @Override
    public int grauDoVertice(Vertice vertice) throws Exception {
        return 0;
    }

    @Override
    public int numeroDeVertices() {
        return this.qtdVertices;
    }

    @Override
    public int numeroDeArestas() {
        return 0;
    }

    @Override
    public ArrayList<Vertice> adjacentesDe(Vertice vertice) throws Exception {
        ArrayList<Vertice> adjs = new ArrayList<>();
        int id = vertice.id();
        int qtd = getQtdVertices();
        for(int j = 0;j < qtd; j++ ){
            Aresta cmp = arestas[id][j];
            if(cmp == null){
                continue;
            }

            Vertice adj = cmp.destino();
            if(adj == vertice){
                continue;
            }
            adjs.add(adj);
        }
        return adjs;
    }

    @Override
    public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {

    }
    public Aresta arestaEntre(Vertice origem,Vertice destino) {
        int linha = origem.id();
        int coluna = destino.id();

        return arestas[linha][coluna];
    }

    public ArrayList<Double>getAllValues(){
        ArrayList<Double> valores = new ArrayList<>();
        for(int i = 0; i< this.qtdVertices;i++){
            for(int j = 0; j<this.qtdVertices;j++){
                if(arestas[i][j]==null){
                    continue;
                }else{
                    valores.add(arestas[i][j].peso());
                }
            }
        }
        return valores;
    }
    @Override
    public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Vertice> vertices() {
        return this.vertices;
    }
}
