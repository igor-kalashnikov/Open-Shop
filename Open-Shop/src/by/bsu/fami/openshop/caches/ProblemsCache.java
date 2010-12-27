package by.bsu.fami.openshop.caches;

import java.util.Hashtable;

import by.bsu.fami.openshop.interfaces.Openable;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class ProblemsCache {

	private static final ProblemsCache instance = new ProblemsCache();
	
	private ProblemsCache() {
		cache = new Hashtable<String, Openable[]>();
		problemsQueries = ResourcesProvider.get().getString("openshop.tree.tasksTypes.results.list").split(" ");
	}
	
	public static ProblemsCache get() {
		return instance;
	}
	
	public Openable[] getResult(String query) {
		if (cache.contains(query)) {
			return cache.get(query);
		} else {
			Openable[] openable = performNewSearch(query);
			cache.put(query, openable);
			return openable;
		}
	}
	
	private Openable[] performNewSearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public int length() {
		return problemsQueries.length;
	}
	
	public String[] getProblemsQueries() {
		return problemsQueries;
	}
	
	private final Hashtable<String, Openable[]> cache;
	
	private final String[] problemsQueries;
	
}
