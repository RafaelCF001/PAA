/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

/**
 *
 * @author humberto e douglas
 */
public class Vertice {
    private int vertice;
    private String cor;
    private int tempoInicial;
    private int tempoFinal;
    private int distancia;
    private Vertice pai;
    public Vertice( int v ){
        this.vertice = v;
        this.cor = "branco";
        this.tempoInicial = 0;
        this.tempoFinal = 0;
        this.distancia = Integer.MAX_VALUE;
        this.pai = null;
    }
    public void setTempoInicial(int tempo){
        this.tempoInicial = tempo;
    }
    public void setTempoFinal(int tempo){
        this.tempoFinal = tempo;
    }
    public int getTempo(){
        return this.tempoInicial;
    }
    public int getTempoFinal(){
        return this.tempoFinal;
    }
    public Vertice getPai(){
        return this.pai;
    }
    public int getDistancia(){return this.distancia;}
    public void setDistancia(int distancia){
        this.distancia = distancia;
    }
    public void setPai(Vertice v){
        this.pai = v;
    }
    public String getCor(){return this.cor;}
    public void setCor(String cor){this.cor = cor;}
    public int id() {
        return vertice;
    }

    public void setarVertice(int vertice) {
        this.vertice = vertice;
    }
    
}
