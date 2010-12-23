package by.bsu.fami.openshop.interfaces;

import java.net.URL;

/**
 * Represents the interface for the object that can navigate.
 * @author eigenein
 *
 */
public interface Navigateable {

	/**
	 * Navigates to the specified URL.
	 */
	public void navigate(URL url, boolean addToHistory);
	
}
