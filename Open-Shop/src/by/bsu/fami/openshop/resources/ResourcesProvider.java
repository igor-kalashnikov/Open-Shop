package by.bsu.fami.openshop.resources;

import java.awt.Image;
import java.util.*;
import java.util.logging.*;

/**
 * This class is used to load all the resources in the application.
 * Never use any hardcoded strings. All the strings should be saved in 
 * Strings.properties and loaded via this class.
 * @author eigenein
 *
 */
public class ResourcesProvider {
	
	private static final Logger logger = 
		Logger.getLogger(ResourcesProvider.class.getName());
	
	private static final ResourcesProvider instance = new ResourcesProvider();
	
	/**
	 * Gets the instance of the resources provider.
	 */
	public static ResourcesProvider get() {
		return instance;
	}
	
	private ResourceBundle stringsBundle;
	
	private ImageBundle imagesBundle;
	
	private ResourceBundle loadBundle(String name) {
		Locale currentLocale = Locale.getDefault();
		try {
			return ResourceBundle.getBundle(name, currentLocale);
		} catch (MissingResourceException e) {
			logger.severe(e.toString());
			return null;
		}
	}
	
	private ResourcesProvider() {
		stringsBundle = loadBundle("by.bsu.fami.openshop.resources.strings");
		imagesBundle = 
			new ImageBundle(loadBundle("by.bsu.fami.openshop.resources.images"));
		
		if (stringsBundle == null || imagesBundle == null) {
			System.exit(0);
		}
	}
	
	/**
	 * Gets the string with the specified key.
	 * @param key The string key.
	 * @param defaultValue The default value.
	 * @return A string value for the specified key.
	 */
	public String getString(String key, String defaultValue) {
		try {
			return stringsBundle.getString(key);
		} catch (MissingResourceException e) {
			logger.warning(e.toString());
			return defaultValue;
		}
	}
	
	/**
	 * Gets the string with the specified key.
	 * @param key The string key.
	 * @return A string value for the specified key or <code>null</code> 
	 * if not found.
	 */
	public String getString(String key) {
		return getString(key, null);
	}
	
	public Image getImage(String key) {
		return imagesBundle.getImage(key);
	}
	
}
