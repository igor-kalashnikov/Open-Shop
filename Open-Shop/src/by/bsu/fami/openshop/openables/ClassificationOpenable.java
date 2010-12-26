package by.bsu.fami.openshop.openables;

import java.awt.Component;

import javax.swing.*;

import by.bsu.fami.openshop.interfaces.Openable;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class ClassificationOpenable implements Openable {

	@Override
	public Component getUI() {
		initialize();
		return uiPanel;
	}

	@Override
	public boolean hasVisualization() {
		return false;
	}

	@Override
	public void initialize() {
		if (uiPanel == null) {
			textLabel = new JLabel(ResourcesProvider.get().getString("openshop.tree.classification.text"));
			textLabel.setHorizontalTextPosition(JLabel.CENTER);
			textLabel.setVerticalTextPosition(JLabel.CENTER);
			uiPanel = new JPanel();
			uiPanel.add(textLabel);
		}
	}

	@Override
	public void showVisualization() {
		// Do nothing.
	}
	
	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.tree.classification");
	}
	
	private JLabel textLabel = null;
	
	private JPanel uiPanel = null;

}
