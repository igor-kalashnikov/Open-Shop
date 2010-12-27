package by.bsu.fami.openshop.caches;

import java.util.Hashtable;

import javax.swing.tree.DefaultMutableTreeNode;

public class SearchResultsNodesCache {

	private static SearchResultsNodesCache instance = new SearchResultsNodesCache();
	
	public static SearchResultsNodesCache get() {
		return instance;
	}
	
	private SearchResultsNodesCache() {
		cache = new Hashtable<String, DefaultMutableTreeNode>();
	}
	
	private final Hashtable<String, DefaultMutableTreeNode> cache;
	
	public boolean contains(String query) {
		return cache.containsKey(query);
	}
	
	public void putResult(String query, DefaultMutableTreeNode result) {
		cache.put(query, result);
	}
	
	public DefaultMutableTreeNode getResult(String query) {
		return cache.get(query);
	}
	
}
