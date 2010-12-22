package by.bsu.fami.openshop.algorithms;

import java.net.URL;

import by.bsu.fami.openshop.interfaces.Algorithmized;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * Represents the approximate algorithm for Open Shop.
 * @author eigenein
 *
 */
public class ApproximateAlgorithm implements Algorithmized {

	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.algorithms.approximate.title");
	}
	
	@Override
	public URL getUrl() {
		return ResourcesProvider.get().getUrl("openshop.algorithms.approximate.url");
	}

	@Override
	public boolean hasVisualization() {
		return true;
	}
	
}
