import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

public class Graph {

    private int countNodes;
    private int countEdges;
    private int[][] adjMatriz;

    public Graph(int numNodes) {
        this.countNodes = numNodes;
        this.countEdges = 0;
        this.adjMatriz = new int[numNodes][numNodes];
    }

    public Graph(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Read header
        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));

        // Create and fill adjMatrix with read edges
        this.adjMatriz = new int[this.countNodes][this.countNodes];
        for (int i = 0; i < fileLines; ++i) {
            String[] edgeInfo = bufferedReader.readLine().split(" ");
            int source = Integer.parseInt(edgeInfo[0]);
            int sink = Integer.parseInt(edgeInfo[1]);
            int weight = Integer.parseInt(edgeInfo[2]);
            addEdge(source, sink, weight);
        }
        bufferedReader.close();
        reader.close();
    }

    public void addEdge(int source, int sink, int weight) {
        if (source < 0 || source > this.adjMatriz.length - 1 || sink < 0 || sink > this.adjMatriz.length - 1
                || weight <= 0) {
            System.out.println("\n!!! Invalid edg: So: " + source + " /Si: " + sink + " /We: " + weight + "\n");
            return;
        }
        this.countEdges++;
        this.adjMatriz[source][sink] = weight;
    }

    public void addEdgeUnriented(int source, int sink, int weight) {
        if (source < 0 || source > this.adjMatriz.length - 1 || sink < 0 || sink > this.adjMatriz.length - 1
                || weight <= 0) {
            System.out.println("\n!!! Invalid edg: So: " + source + " /Si: " + sink + " /We: " + weight + "\n");
            return;
        }
        this.countEdges += 2;
        this.adjMatriz[source][sink] = weight;
        this.adjMatriz[sink][source] = weight;

    }

    public int alfaDegree(int node) {
        if (node >= 4) {
            System.out.println("\n !!! Invalid node number !!!");
            return -1;
        }
        int count = 0;
        for (int i = 0; i < this.adjMatriz.length; i++) {
            if (this.adjMatriz[node][i] != 0) {
                count++;
            }
        }
        return count;
    }

    public int highestDegree() {
        int count = 0;
        for (int i = 0; i < this.adjMatriz.length; i++) {
            if (count < this.alfaDegree(i)) {
                count = this.alfaDegree(i);
            }
        }
        return count;
    }

    public int lowestDegree() {
        int count = 0;
        for (int i = 0; i < this.adjMatriz.length; i++) {
            if (count > this.alfaDegree(i)) {
                count = this.alfaDegree(i);
            }
        }
        return count;
    }

    public Graph complement() {
        Graph g = new Graph(this.countNodes);
        for (int i = 0; i < this.adjMatriz.length; i++) {
            for (int j = 0; j < this.adjMatriz[i].length; j++) {
                if ((this.adjMatriz[i][j] == 0) && (i != j)) {
                    g.addEdge(i, j, 1);
                }
            }
        }
        return g;
    }

    public boolean subGraph(Graph g2) {
        if (g2.countEdges > this.countEdges || g2.countNodes > this.countNodes) {
            return false;
        }
        for (int i = 0; i < this.adjMatriz.length; i++) {
            for (int j = 0; j < this.adjMatriz.length; j++) {
                if ((g2.adjMatriz[i][j] != 0) && this.adjMatriz[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public float density() {
        float d = 0;
        d = (float) this.countEdges / (this.countNodes * (this.countNodes - 1));
        return d;
    }

    public boolean oriented() {

        for (int i = 0; i < this.adjMatriz.length; i++) {
            for (int j = 0; j < this.adjMatriz.length; j++) {
                if (adjMatriz[i][j] == adjMatriz[j][i] && adjMatriz[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;

    }

    public ArrayList<Integer> busca_largura(int s) {
        int desc[] = new int[this.countNodes];
        ArrayList<Integer> Q = new ArrayList<Integer>();
        Q.add(s);
        ArrayList<Integer> R = new ArrayList<Integer>();
        R.add(s);
        desc[s] = 1;
        while (Q.size() > 0) {
            int u = Q.remove(0);
            for (int j = 0; j < this.adjMatriz[u].length; j++) {
                if (desc[j] == 0) {
                    Q.add(j);
                    R.add(j);
                    desc[j] = 1;
                }
            }
        }
        return R;
    }

    public boolean isConex() {
        ArrayList<Integer> array = this.busca_largura(0);
        if (array.size() == this.countNodes) {
            return true;
        } else {
            return false;
        }
    }

    public void add_unoriented_edge(int source, int sink, int weight) {
        this.addEdge(source, sink, weight);
        this.addEdge(sink, source, weight);
    }

    public ArrayList<Integer> busca_profundidade(int s) {

        int desc[] = new int[this.countNodes];

        for (int i = 0; i < this.countNodes; i++) {
            desc[i] = 0;
        }

        ArrayList<Integer> S = new ArrayList<Integer>();
        S.add(s);
        ArrayList<Integer> R = new ArrayList<Integer>();
        R.add(s);
        desc[s] = 1;

        while (S.size() > 0) {
            int u = S.get(S.size() - 1);
            int v = notDescAdj(u, desc);
            if (v != -1) {
                S.add(v);
                R.add(v);
                desc[v] = 1;
            } else {
                S.remove(S.size() - 1);
            }
        }
        return R;
    }

    public int notDescAdj(int u, int[] desc) {
        for (int v = 0; v < this.adjMatriz[u].length; v++) {
            if (this.adjMatriz[u][v] != 0 && desc[v] == 0) {
                return v;
            }
        }
        return -1;
    }

    private void DFS_REC_AUX(int u, int[] desc, ArrayList R) {
        desc[u] = 1;
        R.add(u);
        for (int i = 0; i < this.adjMatriz[u].length; i++) {
            if (this.adjMatriz[u][i] != 0 && desc[i] == 0) {
                DFS_REC_AUX(i, desc, R);
            }
        }
    }

    public ArrayList<Integer> DFS_REC(int s) {
        int desc[] = new int[this.countNodes];
        for (int i = 0; i < this.countNodes; i++) {
            desc[i] = 0;
        }
        ArrayList<Integer> R = new ArrayList<Integer>();
        DFS_REC_AUX(s, desc, R);
        return R;
    }

    public ArrayList ORD_TOP() {

        int desc[] = new int[this.countNodes];
        ArrayList<Integer> R = new ArrayList<Integer>();

        for (int i = 0; i < this.countNodes; i++) {
            if (desc[i] == 0) {
                ORD_TOP_AUX(i, desc, R);
            }
        }
        return R;
    }

    private void ORD_TOP_AUX(int u, int[] desc, ArrayList R) {
        desc[u] = 1;
        for (int v = 0; v < adjMatriz[u].length; v++) {
            if (this.adjMatriz[u][v] != 0 && desc[v] == 0) {
                ORD_TOP_AUX(v, desc, R);
            }
        }
        R.add(0, u);
    }

    public int[] CONNECTED_COMP() {

        int L[] = new int[this.countNodes];
        int comp = 0;

        for (int i = 0; i < this.countNodes; i++) {
            if (L[i] == 0) {
                comp = comp + 1;
                CONNECTED_COMP_AUX(i, L, comp);
            }
        }
        return L;
    }

    public void CONNECTED_COMP_AUX(int u, int[] desc, int comp) {

        desc[u] = comp;

        for (int v = 0; v < adjMatriz[u].length; v++) {
            if (this.adjMatriz[u][v] != 0 && desc[v] == 0) {
                CONNECTED_COMP_AUX(v, desc, comp);
            }
        }
    }

    public boolean HAS_CYCLE_ORIENTED(int s) {
        int desc[] = new int[this.countNodes];
        ArrayList<Integer> Q = new ArrayList<Integer>();
        Q.add(s);
        ArrayList<Integer> R = new ArrayList<Integer>();
        R.add(s);
        desc[s] = 1;
        while (Q.size() > 0) {
            int u = Q.remove(0);
            for (int j = 0; j < this.adjMatriz[u].length; j++) {
                if (this.adjMatriz[u][j] != 0) {
                    if (desc[j] == 0) {
                        Q.add(j);
                        R.add(j);
                        desc[j] = 1;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean HAS_CYCLE(int s) {
        for (int i = 0; i < this.countNodes; i++) {
            boolean flag = HAS_CYCLE_ORIENTED(i);
            if (flag == true) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.adjMatriz.length; i++) {
            for (int j = 0; j < this.adjMatriz.length; j++) {
                str += this.adjMatriz[i][j] + "\t";
            }
            str += "\n";
        }
        return str;
    }
}
