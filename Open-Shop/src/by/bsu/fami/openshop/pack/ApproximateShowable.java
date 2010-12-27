package by.bsu.fami.openshop.pack;

import by.bsu.fami.openshop.interfaces.Showable;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class ApproximateShowable implements Showable {

	@Override
	public void show() {
		Resh resh = new Resh(ResourcesProvider.get().getString("openshop.tree.taskTypes.O__C_max.approximate"), MyPanel.APPROXIMATE);
		resh.setSize(600, 600);
		resh.setLocationRelativeTo(null);
		resh.setVisible(true);
	}

}
