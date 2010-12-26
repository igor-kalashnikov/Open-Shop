package by.bsu.fami.openshop.interfaces;

import java.awt.Component;

public interface Openable {

	public Component getUI();
	
	public boolean hasVisualization();
	
	public void showVisualization();
	
	public void initialize();
	
}
