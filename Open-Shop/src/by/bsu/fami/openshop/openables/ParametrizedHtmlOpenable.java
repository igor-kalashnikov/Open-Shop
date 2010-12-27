package by.bsu.fami.openshop.openables;

import java.net.URL;
import java.util.logging.Logger;

public class ParametrizedHtmlOpenable extends HtmlOpenable {

	private final URL url;
	
	private static final Logger logger = 
		Logger.getLogger(ParametrizedHtmlOpenable.class.getName());
	
	public ParametrizedHtmlOpenable(URL url, String title) {
		logger.info(url + " [" + title + "]");
		this.url = url;
		this.title = title;
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

}
