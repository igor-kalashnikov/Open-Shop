package by.bsu.fami.openshop.caches;

import java.net.URL;
import java.util.*;
import java.util.logging.*;

import by.bsu.fami.openshop.interfaces.Openable;
import by.bsu.fami.openshop.openables.ParametrizedHtmlOpenable;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class ProblemsCache {

	private static final Logger logger = 
		Logger.getLogger(ProblemsCache.class.getName());
	
	private static final ProblemsCache instance = new ProblemsCache();
	
	private ProblemsCache() {
		cache = new Hashtable<String, Openable[]>();
		problemsQueries = ResourcesProvider.get().getString("openshop.tree.tasksTypes.results.list").split(" ");
		PREFIX = ResourcesProvider.get().getString("openshop.tree.tasksTypes.results.prefix");
		SUFFIX = ResourcesProvider.get().getString("openshop.tree.tasksTypes.results.suffix");
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
		logger.info("Searching [" + query + "]");
		String[] queryTokens = query.split("\\|");
		ArrayList<Openable> results = new ArrayList<Openable>();
		int index = 0;
		for (String sample : problemsQueries) {
			String[] sampleTokens = sample.split("\\|");
			if (intersects(sampleTokens, queryTokens)) {
				URL url = ResourcesProvider.class.getResource(PREFIX + index + SUFFIX);
				results.add(new ParametrizedHtmlOpenable(url, sample));
			}
			index += 1;
		}
		Openable[] resultsArray = new Openable[results.size()];
		return results.toArray(resultsArray);
	}
	
	private boolean intersects(String[] sampleTokens, String[] queryTokens) {
		for (String sampleToken : sampleTokens) {
			for (String queryToken : queryTokens) {
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
	
	private final String PREFIX;
	
	private final String SUFFIX;
	
}
