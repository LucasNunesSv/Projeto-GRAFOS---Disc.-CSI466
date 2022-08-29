import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Graph {

    private int countNodes;
    private int countEdges;
    private int[][] adjMatriz;

    public Graph(int numNodes) {
        this.countNodes = numNodes;
        this.countEdges = 0;
        this.adjMatriz = new int[numNodes][numNodes];
    }

    public void addEdge(int source, int sink, int weight) {
        if (source < 0 || source > this.adjMatriz.length - 1 || sink < 0 || sink > this.adjMatriz.length -1  || weight <= 0) {
            System.out.println("\n!!! Invalid edg: So: " + source + " /Si: " + sink + " /We: " + weight + "\n");
            return;
        }
        this.countEdges++;
        this.adjMatriz[source][sink] = weight;
    }

    public void addEdgeUnriented(int source, int sink, int weight){
        if (source < 0 || source > this.adjMatriz.length - 1 || sink < 0 || sink > this.adjMatriz.length -1  || weight <= 0) {
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

    public ArrayList<Integer> busca_largura(int s){
        int desc[] = new int[this.countNodes];
        ArrayList<Integer> Q = new ArrayList<Integer>();
        Q.add(s);
        ArrayList<Integer> R = new ArrayList<Integer>();
        R.add(s);
        desc[s] = 1;
        while(Q.size() > 0){
            int u = Q.remove(0);
            for(int j=0; j<this.adjMatriz[u].length; j++){
                if(desc[j] == 0){
                    Q.add(j);
                    R.add(j);
                    desc[j] = 1;
                }
            }
        }
        return R;
    }

    public boolean isConex(){
        ArrayList<Integer> array = this.busca_largura(0);
        if(array.size() == this.countNodes){
            return true;
        }else{
            return false;
        }
    }

    public void add_unoriented_edge(int source, int sink, int weight){
        this.addEdge(source, sink, weight);
        this.addEdge(sink, source, weight);
    }

    public ArrayList<Integer> busca_profundidade(int s){

        int desc[] = new int[this.countNodes];

        for(int i=0; i<this.countNodes; i++){
            desc[i] = 0;
        }

        ArrayList<Integer> S = new ArrayList<Integer>();
        S.add(s);
        ArrayList<Integer> R = new ArrayList<Integer>();
        R.add(s);
        desc[s] = 1;

        while(S.size() > 0){
            int u = S.get(S.size()-1);
            int v = notDescAdj(u, desc);
            if(v != -1){
                S.add(v);
                R.add(v);
                desc[v] = 1;
            }else{
                S.remove(S.size() - 1);
            }
        }
        return R;
    }

    public int notDescAdj(int u, int[] desc){
        for(int v=0; v<this.adjMatriz[u].length; v++){
            if(this.adjMatriz[u][v] != 0 && desc[v] == 0){
                return v;
            }
        }
        return -1;
    }

    public void DFS_REC_AUX(int u, int[] desc, ArrayList R){
        desc[u] = 1;
        R.add(u);
        for(int i=0; i<this.adjMatriz[u].length; i++){
            if(this.adjMatriz[u][i] != 0 && desc[i] == 0){
                DFS_REC_AUX(i, desc, R);
            }
        }
    }

    public ArrayList<Integer> DFS_REC(int s){ 
        int desc[] = new int[this.countNodes];
        for(int i=0; i<this.countNodes; i++){
            desc[i] = 0;
        }
        ArrayList<Integer> R = new ArrayList<Integer>();
        DFS_REC_AUX(s,desc,R);
        return R;
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
