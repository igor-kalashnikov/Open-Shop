package by.bsu.fami.openshop.resources;

import java.util.ArrayList;
import java.util.Collection;

import by.bsu.fami.openshop.algorithms.FakeAlgorithm;
import by.bsu.fami.openshop.interfaces.Algorithmized;

/**
 * Loads fake pages from resources.
 * @author eigenein
 *
 */
public class FakePagesLoader {

	private Algorithmized loadFakePage(String pageName) {
		return new FakeAlgorithm(pageName);
	}
	
	public Collection<Algorithmized> loadFakePages() {
		String[] pageNames = ResourcesProvider.get().getString("openshop.pages", "").split(", ");
		ArrayList<Algorithmized> fakePages = new ArrayList<Algorithmized>();
		for (String pageName : pageNames) {
			if (!pageName.isEmpty()) {
				fakePages.add(loadFakePage(pageName));
			}
		}
		return fakePages;
	}
	
}
