package by.bsu.fami.openshop.gui.controls;

import java.awt.*;
import java.util.logging.Logger;

import javax.swing.*;

import by.bsu.fami.openshop.interfaces.OpenShopTask;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * Represents the dialog for choosing the task.
 * @author eigenein
 *
 */
public class TaskChooser extends JDialog {

	private static final Logger logger = 
		Logger.getLogger(TaskChooser.class.getName());
	
	private static final long serialVersionUID = 5079339933250868135L;

	private TaskChooser(Frame frame, OpenShopTask[] tasks) {
		super(frame, ResourcesProvider.get().getString(
				"openshop.taskChooser.title"), true);
	}
	
	private static TaskChooser chooser;
	
	private static OpenShopTask returnValue;
	
	/**
	 * Shows the dialog.
	 * @param frame The parent frame.
	 * @param tasks The tasks to choose for.
	 * @return The selected task.
	 */
	public OpenShopTask showDialog(Frame frame, OpenShopTask[] tasks) {
		chooser = new TaskChooser(frame, tasks);
		chooser.setVisible(true);
		logger.info(returnValue.toString());
		return returnValue;
	}
	
}
