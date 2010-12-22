package by.bsu.fami.openshop.algorithms;

import java.net.URL;

import by.bsu.fami.openshop.interfaces.Algorithmized;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * Represents the "home page".
 * @author eigenein
 *
 */
public class HomeAlgorithm implements Algorithmized {

	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.algorithms.home.title");
	}
	
	@Override
	public URL getUrl() {
		return ResourcesProvider.get().getUrl("openshop.algorithms.home.url");
	}

	@Override
	public boolean hasVisualization() {
		return false;
	}
	
}
