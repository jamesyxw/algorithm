package graph;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Dijkstra {
	
	public static void dijkstra(Graph graph, String start) throws Exception {
		if (graph == null || start == null) {
			return;
		}
		
		if (graph.getVertex(start) == null) {
			throw new Exception("Unknown Vertex");
		}
		
		NavigableSet<Vertex> q = new TreeSet<Vertex>();
		final Vertex source = graph.getVertex(start);
		
		//Create a TreeSet for all the vertex to achieve O(log|V|) runtime on remove and add
		for (Vertex v : graph.getAllVertexes()) {
			v.dist = Integer.MAX_VALUE;
			v.prev = null;
			q.add(v);
		}
		
		source.dist = 0;
		
		while (!q.isEmpty()) {
			//Get the vertex with the shortest dist (to source) 
			Vertex u = q.pollFirst();
			
			for (Vertex neighbor : u.neighbors.keySet()) {
				Integer alt = u.dist + graph.getDist(u.key, neighbor.key);
				if (alt.compareTo(neighbor.dist) < 0) {
					neighbor.dist = alt;
					neighbor.prev = u;
				}
			}
		}
	}

}
