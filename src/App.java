import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        Graph g = new Graph(4);

        g.addEdge(0, 1, 3);
        g.addEdge(1, 0, 3);
        g.addEdge(0, 3, 4);
        g.addEdge(3, 0, 4);
        g.addEdge(0, 4, 1);
        g.addEdge(0, 2, 3);

        System.out.println(g);

        System.out.println("Num de Vizinhos: " + g.alfaDegree(0));
        System.out.println("Num de Vizinhos: " + g.alfaDegree(1));
        System.out.println("Num de Vizinhos: " + g.alfaDegree(2));
        System.out.println("Num de Vizinhos: " + g.alfaDegree(4));

        System.out.println("\nMaior Grau presente no Grafo = " + g.highestDegree());
        System.out.println("\nMenor Grau presente no Grafo = " + g.lowestDegree());

        Graph complementGraph;
        complementGraph = g.complement();
        System.out.println("\n" + complementGraph.toString());

        Graph g1 = new Graph(4);
        g1.addEdge(0, 1, 1);
        g1.addEdge(0, 3, 1);
        g1.addEdge(1, 0, 1);
        g1.addEdge(3, 0, 1);
        System.out.println("Matriz de G1\n" + g1);

        Graph g2 = new Graph(4);
        g2.addEdge(0, 2, 1);
        g2.addEdge(2, 0, 1);
        g2.addEdge(1, 2, 1);
        g2.addEdge(2, 1, 1);
        g2.addEdge(2, 3, 1);
        g2.addEdge(3, 2, 1);
        g2.addEdge(1, 3, 1);
        g2.addEdge(3, 1, 1);
        System.out.println("Matriz de G2\n" +g2);

        Graph g3 = new Graph(4);
        g3.addEdge(0, 3, 1);
        g3.addEdge(3, 0, 1);
        System.out.println("Matriz de G3\n" +g3);

        Graph g4 = new Graph(4);
        g4.addEdge(0, 1, 1);
        g4.addEdge(1, 0, 1);
        g4.addEdge(2, 3, 1);
        g4.addEdge(3, 2, 1);
        System.out.println("Matriz de G4\n" +g4);

        System.out.println("G1 é SubGrafo de G2? = " + g1.subGraph(g2));
        System.out.println("G1 é SubGrafo de G3? = " + g1.subGraph(g3));
        System.out.println("G2 é SubGrafo de G3? = " + g2.subGraph(g3));
        System.out.println("G1 é SubGrafo de G4? = " + g1.subGraph(g4));

        Graph G5 = new Graph(4);
        G5.addEdge(0, 1, 1);
        G5.addEdge(1, 0, 1);
        G5.addEdge(3, 0, 1);
        G5.addEdge(0, 3, 1);
        System.out.printf("\nDensidade de G5: %.2f", G5.density());
        System.out.println("\nO Grafo G5 é orientado?: " + G5.oriented());

        ArrayList<Integer>list_largura = g3.busca_largura(1);
        System.out.println("\nBusca Largura: " + list_largura);
        System.out.println("O Grafo G3 é conexo?: " + g3.isConex());

        Graph G6 = new Graph(8);
        G6.addEdgeUnriented(6,1,1);
        G6.addEdgeUnriented(6,7,1);
        G6.addEdgeUnriented(6,3,1);
        G6.addEdgeUnriented(3,4,1);
        G6.addEdgeUnriented(7,0,1);
        G6.addEdgeUnriented(0,5,1);
        G6.addEdgeUnriented(1,2,1);

        ArrayList<Integer>list_profundidade = G6.busca_profundidade(2);
        System.out.println("\nBusca Profundidade " + list_profundidade);
        list_profundidade = G6.DFS_REC(2);
        System.out.println("\nBusca Profundidade Recursiva " + list_profundidade);



    }
}
