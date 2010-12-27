package by.bsu.fami.openshop.openables;

import java.net.URL;
import java.util.logging.Logger;

import by.bsu.fami.openshop.interfaces.Showable;

public class ParametrizedHtmlOpenable extends HtmlOpenable {

	private final URL url;
	
	private static final Logger logger = 
		Logger.getLogger(ParametrizedHtmlOpenable.class.getName());
	
	public ParametrizedHtmlOpenable(URL url, String title, Showable visualization) {
		logger.info(url + " [" + title + "]");
		this.url = url;
		this.visualization = visualization;
		this.title = title;
	}
	
	private final String title;
	
	private final Showable visualization;
	
	@Override
	public boolean hasVisualization() {
		return visualization != null;
	}
	
	@Override
	public void showVisualization() {
		visualization.show();
	}
	
	@Override
	protected URL getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return title;
	}

}
