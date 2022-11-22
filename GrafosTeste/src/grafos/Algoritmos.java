package grafos;

import java.util.*;

public class Algoritmos implements AlgoritmosEmGrafos{
    public int tempo = 0;
    @Override
    public Grafo carregarGrafo(String path, TipoDeRepresentacao t) throws Exception {
        return null;
    }

    @Override
    public Collection<Aresta> buscaEmLargura (Grafo g){
        return null;
    }
    public ArrayList<Aresta> buscaEmLargura(Grafo g, Vertice inicial) throws Exception {
            inicial.setCor("cinza");
            Queue<Vertice> q = new LinkedList<>();
            q.add(inicial);

            while(!(q.isEmpty())){
                Vertice u = q.remove();
                ArrayList<Vertice> adj = (ArrayList<Vertice>) g.adjacentesDe(u);

                for(Vertice v: adj){
                     if(v.getCor().equals("branco")){
                         v.setCor("cinza");
                         v.setTempoInicial(tempo);
                         tempo++;
                         q.add(v);
                     }
                }
                u.setCor("preto");
                System.out.println("Pintou: " + u.getTempo());
            }
            
          return null;
    }
    public void dfsVisit(Vertice u, Grafo g, int tempo) throws Exception {
        u.setCor("cinza");
        tempo++;
        u.setTempoInicial(tempo);
        ArrayList<Vertice> adjacentes = (ArrayList<Vertice>) g.adjacentesDe(u);
        for(Vertice adj: adjacentes){
            if(adj.getCor().equals("branco")){
                dfsVisit(adj,g,tempo);
            }
        }
        u.setCor("Preto");
        u.setTempoFinal(tempo);
        System.out.println("Visitou: " + u.getTempoFinal());
    }
    @Override
    public Collection<Aresta> buscaEmProfundidade(Grafo g) throws Exception {
        ArrayList<Aresta> visitou = new ArrayList<>();
        for(Vertice u: g.vertices()){
            if(u.getCor().equals("branco")){
                dfsVisit(u,g,tempo);
            }
        }

        return visitou;
    }
    public void relaxa(Vertice u, Vertice v, Aresta w, Queue<Vertice> fila){
        if(v.getDistancia() > (u.getDistancia() + w.peso())){
            v.setDistancia((int) (u.getDistancia() + w.peso()));
            v.setPai(u);
            fila.remove(u);
            fila.add(v);
        }
    }
    @Override
    public ArrayList<Vertice> menorCaminho(Grafo g, Vertice origem, Vertice destino) throws Exception {
        ArrayList<Vertice> caminho = new ArrayList<>();

        origem.setDistancia(0);
        Queue<Vertice> fila = new LinkedList<>();
        fila.add(origem);

        while (!fila.isEmpty()){
           Vertice u = fila.poll();
            ArrayList<Vertice> adj = (ArrayList<Vertice>) g.adjacentesDe(u);
            for(Vertice v: adj) {
                Aresta w = g.arestaEntre(u,v);
                relaxa(u, v, w,fila);
            }
        }
        Vertice pai = destino.getPai();
        caminho.add(pai);
        while(pai != null){
            pai = pai.getPai();
            caminho.add(pai);
        }
        for (Vertice v: caminho){
            if(v != null) {
                System.out.println("caminho: " + v.id());
            }
        }
    return caminho;
    }

    @Override
    public boolean existeCiclo(Grafo g) {
        return false;
    }

    @Override
    public Collection<Aresta> agmUsandoKruskall(Grafo g) {
        return null;
    }
    public int busca(int i,int[] pai ){
        while(pai[i] != i){
            i = pai[i];

        }
        return i;
    }
    public void union(int i, int j,int[] pai){
        int a = busca(i,pai);
        int b = busca(j,pai);
        pai[a] = b;
    }
    public Collection<Aresta> agmUsandoKruskall(Grafo g,Vertice inical) {
        ArrayList<Aresta> agm = new ArrayList<>();
        ArrayList<Vertice> vertice = g.vertices();
        int[] pai = new int[g.numeroDeVertices()];
        int contador = 0;
        for(int i = 0; i< g.numeroDeVertices();i++){
            pai[i] = i;
        }
        while (contador< g.numeroDeVertices()-1){
            int min = Integer.MAX_VALUE;
            int a = 0, b = 0;

            for(int i = 0; i< g.numeroDeVertices();i++){
                for(int j = 0; j< g.numeroDeVertices(); j++){
                    if(busca(i,pai) != busca(j,pai) && g.getValue(i,j)< min){
                        min = g.getValue(i,j);
                        a = i;
                        b = j;
                    }
                }
            }
            union(a,b,pai);
            System.out.println("Min: " + min);
            contador++;
        }

        return null;
    }

    @Override
    public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception {
        return 0;
    }

    @Override
    public boolean ehArvoreGeradora(Grafo g, Collection<Aresta> arestas) {
        return false;
    }

    @Override
    public ArrayList<Aresta> caminhoMaisCurto(Grafo g, Vertice origem, Vertice destino) {
        return null;
    }

    @Override
    public double custoDoCaminho(Grafo g, ArrayList<Aresta> arestas, Vertice origem, Vertice destino) throws Exception {
        return 0;
    }

    @Override
    public boolean ehCaminho(ArrayList<Aresta> arestas, Vertice origem, Vertice destino) {
        return false;
    }

    @Override
    public Collection<Aresta> arestasDeArvore(Grafo g) {
        return null;
    }

    @Override
    public Collection<Aresta> arestasDeRetorno(Grafo g) {
        return null;
    }

    @Override
    public Collection<Aresta> arestasDeAvanco(Grafo g) {
        return null;
    }

    @Override
    public Collection<Aresta> arestasDeCruzamento(Grafo g) {
        return null;
    }
}
