package by.bsu.fami.openshop.openables;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.JEditorPane;

import by.bsu.fami.openshop.interfaces.Openable;

public abstract class HtmlOpenable implements Openable {

	private static final Logger logger = 
		Logger.getLogger(HtmlOpenable.class.getName());
	
	protected abstract URL getUrl();

	@Override
	public Component getUI() {
		initialize();
		return editorPane;
	}
	
	@Override
	public void initialize() {
		if (editorPane == null) {
			try {
				URL url = getUrl();
				logger.info("Creating an editor pane for " + url.toString());
				editorPane = new JEditorPane(url);
			} catch (IOException e) {
				logger.severe(e.toString());
			}
			editorPane.setEditable(false);
	        editorPane.setBackground(Color.WHITE);
		}
	}
	
	private JEditorPane editorPane = null;
	
}
