public class App {
    public static void main(String[] args) throws Exception {

        Graph g1 = new Graph(4);

        g1.addEdge(0, 1, 3);
        g1.addEdge(1, 0, 3);
        g1.addEdge(0, 3, 4);
        g1.addEdge(3, 0, 4);
        g1.addEdge(0, 4, 1);
        g1.addEdge(0, 2, 3);

        System.out.println(g1);
        
        System.out.println("Num de Vizinhos: " + g1.alfaDegree(0));
        System.out.println("Num de Vizinhos: " + g1.alfaDegree(1));
        System.out.println("Num de Vizinhos: " + g1.alfaDegree(2));
        System.out.println("Num de Vizinhos: " + g1.alfaDegree(4));

        System.out.println("\nMaior Grau presente no Grafo = " + g1.highestDegree());
        System.out.println("\nMenor Grau presente no Grafo = " + g1.lowestDegree());

    }
}
