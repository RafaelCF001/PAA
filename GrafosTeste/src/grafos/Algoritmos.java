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
    public String buscaEmLargura(Grafo g, Vertice inicial) throws Exception {
            inicial.setCor("cinza");
            Queue<Vertice> q = new LinkedList<>();
            q.add(inicial);
            String tempos = "";
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
                tempos += "(" + u.getTempo() + "), ";
            }
            
          return tempos;
    }
    public void dfsVisit(Vertice u, Grafo g) throws Exception {
        u.setCor("cinza");
        tempo++;
        u.setTempoInicial(tempo);
        ArrayList<Vertice> adjacentes = (ArrayList<Vertice>) g.adjacentesDe(u);
        for(Vertice adj: adjacentes){
            if(adj.getCor().equals("branco")){
                dfsVisit(adj,g);
            }else if(adj.getTempoFinal() == 0){
                Aresta a = g.encontraAresta(u.id(),adj.id());
                a.setTipoAresta("retorno");
            }else if(u.getTempo() < adj.getTempo()){
                Aresta a = g.encontraAresta(u.id(),adj.id());
                a.setTipoAresta("avanco");
            }else{
                Aresta a = g.encontraAresta(u.id(),adj.id());
                a.setTipoAresta("cruzamento");

            }
        }
        u.setCor("Preto");
        tempo++;
        u.setTempoFinal(tempo);
    }
    @Override
    public String buscaEmProfundidade(Grafo g) throws Exception {
        ArrayList<Aresta> visitou = new ArrayList<>();
        String tempos = "";
        for(Vertice u: g.vertices()){
            if(u.getCor().equals("branco")){
                dfsVisit(u,g);
            }
        }
         for(Vertice v: g.vertices()){
             tempos+= "(" + v.getTempo() + ", " +  v.getTempoFinal() + ") \n";
         }
        return tempos;
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
    public String menorCaminho(Grafo g, Vertice origem, Vertice destino) throws Exception {
        ArrayList<Vertice> caminho = new ArrayList<>();
        String caminhoFinal = "";
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
                caminhoFinal += "(" + v.id() + ", " + v.getDistancia() + ")" + " \n";
            }
        }
    return caminhoFinal;
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
    public String agmUsandoKruskall(Grafo g,Vertice inical) {
        ArrayList<Aresta> agm = new ArrayList<>();
        String arestasFinais = "";
        ArrayList<Vertice> vertice = g.vertices();
        String caminho  = "";
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
            arestasFinais += "(" + a + ", " + b + ") , \n";
            contador++;
        }

        return arestasFinais;
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
    public String caminhoMaisCurto(Grafo g, Vertice origem, Vertice destino) {
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
