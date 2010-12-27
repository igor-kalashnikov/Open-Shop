package by.bsu.fami.openshop.openables;

import java.net.URL;

public class ParametrizedHtmlOpenable extends HtmlOpenable {

	private final URL url;
	
	public ParametrizedHtmlOpenable(URL url) {
		this.url = url;
	}
	
	@Override
	protected URL getUrl() {
		return url;
	}

}
