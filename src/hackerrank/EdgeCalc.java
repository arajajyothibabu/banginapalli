package hackerrank;

import java.util.*;

class Vertex {
    Integer x, y;

    public Vertex(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return x.equals(vertex.x) && y.equals(vertex.y);
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    public void print() {
        System.out.print(this.x + "-" + this.y);
    }
}

class Edge {
    Vertex start, end;
    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }

    static Boolean isValidEdge(Vertex start, Vertex end){
        Integer xDiff = Math.abs(start.x - end.x),
                yDiff = Math.abs(start.y - end.y),
                xyDiff = Math.abs(start.x - end.y);
        return xDiff <= 1 && yDiff <= 1 && xyDiff <= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (start.equals(edge.start) && end.equals(edge.end)) ||
                (end.equals(edge.start) && start.equals(edge.end));
    }

    @Override
    public int hashCode() {

        return Objects.hash(start, end);
    }

    public void print(){
        this.start.print();
        System.out.print(" <--> ");
        this.end.print();
        System.out.println();
    }
}

public class EdgeCalc {

    public Integer max(Integer a, Integer b){
        return a >= b ? a : b;
    }

    public Integer min(Integer a, Integer b){
        return a <= b ? a : b;
    }

    public ArrayList<Vertex> adjacentVertices(Integer[][] matrix, Vertex vertex){
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for(Integer x = vertex.x; x < min(vertex.x + 2, matrix.length); x++){
            for(Integer y = vertex.y; y < min(vertex.y + 2, matrix[x].length); y++){
                if(matrix[x][y] == 1){
                    Vertex v = new Vertex(x, y);
                    if(!v.equals(vertex)){
                        vertices.add(v);
                    }
                }
            }
        }
        return vertices;
    }

    public Integer calculate(Integer[][] matrix){
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for(Integer x = 0; x < matrix.length; x++){
            for(Integer y = 0; y < matrix[x].length; y++){
                if(matrix[x][y] == 1){
                    Vertex vertex = new Vertex(x, y);
                    ArrayList<Vertex> adjacent = adjacentVertices(matrix, vertex);
                    for(Vertex v : adjacent){
                        Edge edge = new Edge(vertex, v);
                        if(!edges.contains(edge)){
                            //edge.print();
                            edges.add(edge);
                        }
                    }
                }
            }
        }
        return edges.size();
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        Integer n = sc.nextInt(), m = sc.nextInt();
        Integer[][] matrix = new Integer[n][m];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                matrix[r][c] = sc.nextInt();
            }
        }
        EdgeCalc edgeCalc = new EdgeCalc();
        System.out.print(edgeCalc.calculate(matrix));

    }

}
