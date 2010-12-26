package by.bsu.fami.openshop.openables;

import java.awt.*;

import javax.swing.*;
import by.bsu.fami.openshop.interfaces.Openable;

public abstract class PictureAndHtmlOpenable extends HtmlOpenable implements Openable {

	@Override
	public Component getUI() {
		initialize();
		return uiPanel;
	}

	@Override
	public void initialize() {
		super.initialize();
		if (uiPanel == null) {
			imageLabel = new JLabel(getIcon());
			imageLabel.setHorizontalAlignment(JLabel.CENTER);
			imageLabel.setVerticalAlignment(JLabel.CENTER);
			uiPanel = new JPanel();
			uiPanel.setLayout(new BorderLayout());
			uiPanel.add(imageLabel, BorderLayout.NORTH);
			uiPanel.add(super.getUI(), BorderLayout.CENTER);
			uiPanel.setBackground(Color.WHITE);
		}
	}

	protected abstract Icon getIcon();
	
	private JPanel uiPanel = null;
	
	private JLabel imageLabel = null;

}
