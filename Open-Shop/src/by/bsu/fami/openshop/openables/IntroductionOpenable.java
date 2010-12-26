package by.bsu.fami.openshop.openables;

import java.net.URL;

import by.bsu.fami.openshop.resources.ResourcesProvider;

public class IntroductionOpenable extends HtmlOpenable {

	@Override
	protected URL getUrl() {
		return ResourcesProvider.get().getUrl("openshop.tree.introduction.url");
	}
	
	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.tree.introduction");
	}

}
