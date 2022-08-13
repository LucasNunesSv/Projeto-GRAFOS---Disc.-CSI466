import java.util.Arrays;

public class Graph {

    private int countNodes;
    private int countEdges;
    private int[][] adjMatriz;
    
    public Graph(int numNodes){
        this.countNodes = numNodes;
        this.countEdges = 0;
        this.adjMatriz = new int[numNodes][numNodes];
    }

    public void addEdge(int source, int sink, int weight){
        if(source < 0 || source >= this.adjMatriz.length || sink < 0 || sink >= this.adjMatriz.length || weight <=0){
            System.out.println("\n!!! Invalid edg: So: " + source + " /Si: " + sink + " /We: " + weight + "\n");
            return;
        } 
        this.countEdges++;
        this.adjMatriz[source][sink] = weight;
    }

    public int alfaDegree(int node){
        
        if(node >= 4){
            System.out.println("\n !!! Invalid node number !!!");
            return -1;
        }

        int count = 0;
        for(int i=0; i<this.adjMatriz.length; i++){
            if(this.adjMatriz[node][i] != 0){
                count ++;
            }
        }
        return count;
    }

    public int highestDegree(){
        int count = 0;
        for(int i=0; i<this.adjMatriz.length; i++){
            if(count < this.alfaDegree(i)){
                count = this.alfaDegree(i);
            }
        }
        return count;
    }

    public int lowestDegree(){
        int count = 0;
        for(int i=0; i<this.adjMatriz.length; i++){
            if(count > this.alfaDegree(i)){
                count = this.alfaDegree(i);
            }
        }
        return count;
    }
    
    public Graph complement(){
        Graph g = new Graph(this.countNodes);
        for(int i=0; i<this.adjMatriz.length; i++){
            for(int j=0; j<this.adjMatriz[i].length; j++){
                if((this.adjMatriz[i][j] == 0) && (i!=j)){
                    g.addEdge(i, j, 1);
                }
            }
        }
        return g;
    }

    public boolean subGraph(Graph g2){
        if(g2.countEdges > this.countEdges || g2.countNodes > this.countNodes){
            return false;
        }
        for(int i=0; i<this.adjMatriz.length; i++){
            for(int j=0; j<this.adjMatriz.length; j++){
                if((g2.adjMatriz[i][j] != 0) && this.adjMatriz[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i=0; i<this.adjMatriz.length; i++){
            for(int j=0; j<this.adjMatriz.length; j++){
                str += this.adjMatriz[i][j] + "\t";
            }
            str += "\n";
        }
        return str;
    }
}