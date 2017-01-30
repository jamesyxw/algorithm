package graph;

import java.util.*;

public class Graph {
	public Map<String, Vertex> graph;
	
	public Graph() {
		this.graph = new HashMap<String, Vertex>();
	}
	
	public void addEdge(String start, String dest, Integer dist) {
		if (!this.graph.containsKey(start)) {
			this.graph.put(start, new Vertex(start));
		}
		
		if (!this.graph.containsKey(dest)) {
			this.graph.put(dest, new Vertex(dest));
		}
		
		//add dest to start's neighbor and their distance info
		this.graph.get(start).neighbors.put(this.graph.get(dest), dist);
		this.graph.get(dest).neighbors.put(this.graph.get(start), dist);
	}
	
	public Integer getDist(String start, String dest) throws Exception {
		//Check if start and dest exist
		if (!this.graph.containsKey(start) || !this.graph.containsKey(dest)) {
			throw new Exception ("Unknow vertex");
		}
		//Check if they are connected
		if (!this.graph.get(start).neighbors.containsKey(this.graph.get(dest))) {
			throw new Exception (start + " and " + dest + " are not connected");
		}
		
		return this.graph.get(start).neighbors.get(this.graph.get(dest));
	}
	
	public Vertex getVertex (String start) {
		return this.graph.get(start);
	}
	
	public Set<Vertex> getAllVertexes() {
		return new HashSet<Vertex>(this.graph.values());
	}

}
