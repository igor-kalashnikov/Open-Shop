package by.bsu.fami.openshop.resources;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * The class provides an images resource bundle.
 * @author eigenein
 *
 */
public class ImageBundle {

	private static final Logger logger = 
		Logger.getLogger(ImageBundle.class.getName());
	
	private final ResourceBundle underlyingBundle;
	
	/**
	 * Initializes a new instance.
	 */
	public ImageBundle(ResourceBundle underlyingBundle) {
		this.underlyingBundle = underlyingBundle;
		imagesTable = new Hashtable<String, Image>();
	}
	
	private final Hashtable<String, Image> imagesTable;
	
	/**
	 * Gets the specified image from bundle or <code>null</code> if not found.
	 */
	public Image getImage(String key) {
		if (imagesTable.containsKey(key)) {
			return imagesTable.get(key);
		} else {
			try {
				String path = underlyingBundle.getString(key);
				URL url = ImageBundle.class.getResource(path);
				Image image = ImageIO.read(url);
				imagesTable.put(key, image);
				return image;
			} catch (IOException e) {
				logger.warning(e.toString());
				return null;
			} catch (MissingResourceException e) {
				logger.warning(e.toString());
				return null;
			}
		}
	}
	
}
