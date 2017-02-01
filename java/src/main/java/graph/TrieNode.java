package graph;

import java.util.*;

public class TrieNode {
	Integer words;
	Integer prefixes;
	Map<Character, TrieNode> edges;
	Boolean isEnd;
	
	TrieNode() {
		this.words = 0;
		this.prefixes = 0;
		this.isEnd = false;
		this.edges = new HashMap<Character, TrieNode>();
	}
}
