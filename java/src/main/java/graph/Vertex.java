package graph;

import java.util.*;

public class Vertex implements Comparable<Vertex>{
	
	public Integer dist = Integer.MAX_VALUE;
	public String key;
	public Map<Vertex, Integer> neighbors = new HashMap<Vertex, Integer>();
	public Vertex prev = null;
	
	public Vertex (String key) {
		this.key = key;
	}

	public int compareTo(Vertex o) {
		if (this.dist.equals(o.dist)) {
			return this.key.compareTo(o.key);
		}
		return this.dist.compareTo(o.dist);
	}

}
