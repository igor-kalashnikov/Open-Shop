package by.bsu.fami.openshop.algorithms;

import java.net.URL;

import by.bsu.fami.openshop.interfaces.Algorithmized;
import by.bsu.fami.openshop.pack.MyPanel;
import by.bsu.fami.openshop.pack.Resh;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * Represents the search algorithm for Open Shop.
 * @author eigenein
 *
 */
public class SearchAlgorithm implements Algorithmized {

	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.algorithms.search.title");
	}
	
	@Override
	public URL getUrl() {
		return ResourcesProvider.get().getUrl("openshop.algorithms.search.url");
	}

	@Override
	public boolean hasVisualization() {
		return true;
	}

	@Override
	public void startVisualization() {
		Resh frame = new Resh(
				ResourcesProvider.get().getString("openshop.algorithms.search.title"), 
				MyPanel.SEARCH);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
