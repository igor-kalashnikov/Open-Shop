package by.bsu.fami.openshop.openables;

import java.net.URL;

import by.bsu.fami.openshop.caches.SearchResultsCache;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class ProblemHtmlOpenable extends HtmlOpenable {

	private final URL url;
	
	public ProblemHtmlOpenable(int index) {
		this.url = ResourcesProvider.class.getResource(prefix + index + suffix);;
		this.title = SearchResultsCache.get().getProblemsQueries()[index];
		alias = title.replace('|', '_');
	}
	
	private final String title;
	
	@Override
	protected URL getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
	public String getAlias() {
		return alias;
	}
	
	private final String alias;
	
	private static final String prefix = ResourcesProvider.get().getString("openshop.tree.tasksTypes.results.prefix");
	
	private static final String suffix = ResourcesProvider.get().getString("openshop.tree.tasksTypes.results.suffix");

}
