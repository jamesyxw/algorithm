package graph;

public class Trie {
	
	TrieNode root;
	
	public Trie () {
		this.root = new TrieNode();
	}
	
	/**
	 * Add a word to a vertex
	 * @param vertex
	 * @param word
	 */
	public void addWord (String word) {
		if (word == null || word.isEmpty()) {
			this.root.words++;
			return;
		}
		
		this.root.prefixes++;
		TrieNode p = this.root;
		for (int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			if (p.edges.get(c) == null) {
				p.edges.put(c, new TrieNode());
			} 
			p = p.edges.get(c);
		}
		p.isEnd = true;
	}
	
	/**
	 * Check if a word exists
	 * @param word
	 * @return
	 */
	public boolean exists (String word) {
		if (word == null || word.isEmpty()) {
			return true;
		}
		
		TrieNode end = this.searchNode(this.root, word);
		return end.isEnd;
	}
	
	/**
	 * Check how many words contains the prefix
	 * @param prefix
	 * @return
	 */
	public int countPrefixes (String prefix) {
		if (prefix == null || prefix.isEmpty()) {
			return 0;
		}
		
		TrieNode end = this.searchNode(this.root, prefix);
		return end.prefixes;
	}
	
	/**
	 * Check how many words exists
	 * @param word
	 * @return
	 */
	public int countWords (String word) {
		if (word == null || word.isEmpty()) {
			return 0;
		}
		
		TrieNode end = this.searchNode(this.root, word);
		return end.words;
	}
	
	/**
	 * Find the node that is the end for the input str
	 * @param start
	 * @param str
	 * @return
	 */
	public TrieNode searchNode (TrieNode start, String str) {
		if (str == null || str.isEmpty()) {
			return start;
		}
		
		TrieNode p = start;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (p.edges.get(c) == null) {
				return null;
			}
			p = p.edges.get(c);
		}
		
		return p;
	}
	
	

}
