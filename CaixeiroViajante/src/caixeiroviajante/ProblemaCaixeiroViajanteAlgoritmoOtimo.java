
package caixeiroviajante;

import java.util.ArrayList;

public class ProblemaCaixeiroViajanteAlgoritmoOtimo {
    public static long cost = Long.MAX_VALUE;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste_2.txt");
        
        Graph graph = null;

        int nVertex = 0;
        
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0){
                nVertex = Integer.parseInt(line.trim());
                graph = new AdjMatrix(nVertex);
            }
            else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits){
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);

                    graph.setEdge(oriVertex, targetVertex, weight);
//                    graph.setEdge(targetVertex, oriVertex, weight);
                }
            }
        }

        int initialNode = 99;
        int[] path = new int[nVertex];
        boolean[] av = new boolean[nVertex];
        for (int i = 0; i < nVertex; i++) {
            av[i] = true;
            path[i] = -1;
        }
        path[0] = initialNode;
        av[initialNode] = false;
        backTracking(initialNode, path, av, 1, graph);
    }
    
    public static void backTracking (   int node,
                                        int path[],
                                        boolean av[],
                                        int pos,
                                        Graph graph) {

        if (pos == graph.getVertexSize() &&graph.getEdgeWeight(path[pos - 1], path[0]) < cost ) {
            int pathCost = graph.getEdgeWeight(path[pos - 1], path[0]);
            for (int i = 0; i < (pos - 1); i++) {
                pathCost += graph.getEdgeWeight(path[i], path[i + 1]);
            }

            if (pathCost < cost && pathCost > 0) {
                cost = pathCost;
                for (int i = 0; i < (pos - 1); i++) {
                    System.out.print(path[i] + "\t");
                }
                System.out.print(path[pos-1] + "\t-\t");
                System.out.println(pathCost);
            }
        }
        else {
            //calcula o custo minimo para o proximo passo -> poda aqueles que tem maior que o minimo
            int minRemainingCost = Integer.MAX_VALUE;
            ArrayList<Integer> adj = graph.getAdjVertex(node);
            for (int i : adj) {
                if (av[i] == true) {
                    minRemainingCost = Math.min(minRemainingCost, graph.getEdgeWeight(node, i));
                }
            }
            minRemainingCost += graph.getEdgeWeight(node, path[0]);

            // Prune path if cost is higher than current minimum
            if (minRemainingCost >= cost) {
                return;
            }

            for (int i : adj) {
                if (av[i] == true) {
                    av[i] = false;
                    path[pos] = i;
                    pos++;
                    backTracking(i, path, av, pos, graph);
                    pos--;
                    av[i] = true;
                }
            }
        }
    }
    
}
