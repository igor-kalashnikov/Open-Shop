package by.bsu.fami.openshop.interfaces;

import java.net.URL;

/**
 * An interface for Open Shop algorithm.
 * @author eigenein
 *
 */
public interface Algorithmized {
	
	/**
	 * Gets whether the algorithm has visualization.
	 */
	public boolean hasVisualization();
	
	/**
	 * Get the algorithm URL.
	 */
	public URL getUrl();
	
	/**
	 * Starts a visualization.
	 */
	public void startVisualization();

}
