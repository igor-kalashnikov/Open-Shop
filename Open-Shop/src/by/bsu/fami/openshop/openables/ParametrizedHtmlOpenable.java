package by.bsu.fami.openshop.openables;

import java.net.URL;

public class ParametrizedHtmlOpenable extends HtmlOpenable {

	private final URL url;
	
	public ParametrizedHtmlOpenable(URL url, String title) {
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
