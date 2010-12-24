package by.bsu.fami.openshop.algorithms;

import java.net.URL;

import by.bsu.fami.openshop.interfaces.Algorithmized;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * Represents a fake algorithm for the pages that added in config.
 * @author eigenein
 *
 */
public class FakeAlgorithm implements Algorithmized {

	private String pageName;
	
	/**
	 * Initializes a new instance of fake algorithm for the given page name.
	 */
	public FakeAlgorithm(String pageName) {
		this.pageName = pageName;
	}
	
	@Override
	public URL getUrl() {
		return ResourcesProvider.get().getUrl("openshop.pages." + pageName + ".url");
	}

	@Override
	public boolean hasVisualization() {
		return false;
	}

	@Override
	public void startVisualization() {
		// Do nothing.
	}
	
	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.pages." + pageName + ".title");
	}

}
