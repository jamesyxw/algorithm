package graph;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Dijkstra {

    /**
     * The implementation of the Dijkstra's algorithm is based on the Wikipedia's implementation
     * @param graph
     * @param start
     * @throws Exception
     */
    public static void dijkstra(Graph graph, String start) throws Exception {
        if (graph == null || start == null) {
            return;
        }

        if (graph.getVertex(start) == null) {
            throw new Exception("Unknown Vertex");
        }

        //Create a TreeSet for all the vertex to achieve O(log|V|) runtime on remove and add
        NavigableSet<Vertex> q = new TreeSet<Vertex>();
        Vertex source = graph.getVertex(start);
        source.dist = 0;
        
        for (Vertex v : graph.getAllVertexes()) {
            v.dist = Integer.MAX_VALUE;
            v.prev = null;
            q.add(v);
        }

        while (!q.isEmpty()) {
            //Get the vertex with the shortest dist (to source)
            Vertex u = q.pollFirst();

            for (Vertex neighbor : u.neighbors.keySet()) {
                Integer alt;
                if (u.distTo(neighbor) == null) {
                    alt = Integer.MAX_VALUE;
                } else {
                    alt = u.dist + u.distTo(neighbor);
                }
                 
                if (alt.compareTo(neighbor.dist) < 0) {
                    neighbor.dist = alt;
                    neighbor.prev = u;
                }
            }
        }
    }
}
