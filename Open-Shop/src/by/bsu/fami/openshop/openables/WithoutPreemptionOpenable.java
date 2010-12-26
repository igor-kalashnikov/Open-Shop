package by.bsu.fami.openshop.openables;

import java.net.URL;

import javax.swing.Icon;

import by.bsu.fami.openshop.resources.ResourcesProvider;

public class WithoutPreemptionOpenable extends PictureAndHtmlOpenable {

	@Override
	protected Icon getIcon() {
		return ResourcesProvider.get().getIcon("openshop.tree.classification.withoutPreemption");
	}

	@Override
	protected URL getUrl() {
		return ResourcesProvider.get().getUrl("openshop.tree.classification.withoutPreemption.url");
	}
	
	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.tree.classification.withoutPreemption");
	}

}
