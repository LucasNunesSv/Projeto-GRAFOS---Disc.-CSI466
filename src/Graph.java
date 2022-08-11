import java.util.Arrays;

public class Graph {

    private int countNodes;
    private int coountEdges;
    private int[][] adjMatriz;
    
    public Graph(int numNodes){
        this.countNodes = numNodes;
        this.coountEdges = 0;
        this.adjMatriz = new int[numNodes][numNodes];
    }


    public void addEdge(int source, int sink, int weight){
        if(source < 0 || source >= this.adjMatriz.length || sink < 0 || sink >= this.adjMatriz.length || weight <=0){
            System.out.println("\n!!! Invalid edg: So: " + source + " /Si: " + sink + " /We: " + weight + "\n");
            return;
        } 
        this.coountEdges++;
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