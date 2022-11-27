package grafos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class InciMatrix implements Grafo{

    private final ArrayList<Aresta> arestas;
    private final ArrayList<Vertice> vertices;
    private final HashMap<Aresta, LinkedList<HashMap<Vertice,Integer>>> inciMatrix;
    private HashMap<Vertice,Integer> cmp = new HashMap<>();
    private final int qtdVertices;
    private int tempoInicial = 0;
    private int tempoFinal = 0;
    
    public Aresta encontraAresta(int i, int j){
          for(Aresta a: arestas){
              if(a.destino().id() == j && a.origem().id() == i){
                  return a;
              }
          }
          return null;
      }
    
    public String getTempos(){
        String resultado = "";
        for(Vertice v: vertices){
            resultado  += v.getTempo() + " " + v.getTempoFinal();
        }
        return resultado;
    }
    public InciMatrix(int qtdVertices){
        this.inciMatrix = new HashMap< Aresta,LinkedList<HashMap<Vertice, Integer>>>();
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.qtdVertices = qtdVertices;

    }
    public void resetarCor(){
        for(Vertice v: vertices){
            v.setCor("branco");
        }
    }
    public int getQtdVertices(){return this.qtdVertices;}

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino) throws Exception {

    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception {

    }


    public void adicionarAresta(int origem,int destino, double peso) throws Exception {
        Vertice a = vertices.get(origem);
        Vertice b = vertices.get(destino);
        Aresta cmp = new Aresta(a,b,peso);
        arestas.add(cmp);
        LinkedList<HashMap<Vertice,Integer>> adj = inciMatrix.get(a);
        HashMap<Vertice,Integer> cmpH = new HashMap<>();
        if(adj == null) {
            adj = new LinkedList<>();
            inciMatrix.put(cmp, adj);
        }
        cmpH.put(b, (int) peso);
        adj.add(cmpH);

    }
    public void addVertice(int id){
        vertices.add(new Vertice(id));
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
        return qtdVertices;
    }

    @Override
    public int numeroDeArestas() {
        return 0;
    }

    @Override
    public ArrayList<Vertice> adjacentesDe(Vertice vertice) throws Exception {
        ArrayList<Vertice> result = new ArrayList<>();

       for(Aresta a: arestas){
           if(a.origem() == vertice){
               result.add(a.destino());
           }
       }
        return result;
    }

    @Override
    public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {

    }

    @Override
    public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) throws Exception {
        ArrayList<Aresta> aux = new ArrayList<>();
        for(Aresta a : arestas){
            if(a.origem() == origem && a.destino() == destino){
                aux.add(a);
            }
        }
        return aux;
    }

    @Override
    public ArrayList<Vertice> vertices() {
        return this.vertices;
    }

    @Override
    public Aresta arestaEntre(Vertice u, Vertice v) {
        for(Aresta a : arestas){
            if(a.origem() == u && a.destino() == v){
                return a;
            }
        }
        return null;
    }

    @Override
    public int getValue(int i, int j) {
        Vertice a = vertices.get(i);
        Vertice b = vertices.get(j);

        for(Aresta c: inciMatrix.keySet()){
            if (c.origem().id() == i && c.destino().id() == j){
                return (int) c.peso();
            }
        }
        return Integer.MAX_VALUE;
    }
}
