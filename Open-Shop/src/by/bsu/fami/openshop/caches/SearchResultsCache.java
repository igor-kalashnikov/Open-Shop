package by.bsu.fami.openshop.caches;

import java.util.*;
import java.util.logging.*;

import by.bsu.fami.openshop.interfaces.Openable;
import by.bsu.fami.openshop.openables.ProblemHtmlOpenable;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class SearchResultsCache {

	private static final Logger logger = 
		Logger.getLogger(SearchResultsCache.class.getName());
	
	private static final SearchResultsCache instance = new SearchResultsCache();
	
	private SearchResultsCache() {
		cache = new Hashtable<String, Openable[]>();
		problemsQueries = ResourcesProvider.get().getString("openshop.tree.tasksTypes.results.list").split(" ");
	}
	
	public static SearchResultsCache get() {
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
		logger.info("Searching [" + query + "]");
		String[] queryTokens = query.split("\\|");
		ArrayList<Openable> results = new ArrayList<Openable>();
		int index = 0;
		for (String sample : problemsQueries) {
			String[] sampleTokens = sample.split("\\|");
			if (intersects(sampleTokens, queryTokens)) {
				results.add(new ProblemHtmlOpenable(index));
			}
			index += 1;
		}
		Openable[] resultsArray = new Openable[results.size()];
		return results.toArray(resultsArray);
	}
	
	private boolean intersects(String[] sampleTokens, String[] queryTokens) {
		for (String sampleToken : sampleTokens) {
			if (sampleToken.length() == 0) {
				continue;
			}
			for (String queryToken : queryTokens) {
				if (queryToken.length() == 0) {
					continue;
				}
				if (sampleToken.equals(queryToken)) {
					return true;
				}
			}
		}
		return false;
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
