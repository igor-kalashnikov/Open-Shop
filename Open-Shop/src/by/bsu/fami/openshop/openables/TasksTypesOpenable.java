package by.bsu.fami.openshop.openables;

import java.awt.*;

import javax.swing.*;

import by.bsu.fami.openshop.interfaces.Openable;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class TasksTypesOpenable implements Openable {

	@Override
	public Component getUI() {
		initialize();
		return uiPanel;
	}

	@Override
	public void initialize() {
		if (uiPanel == null) {
			machinesCountBox = new JComboBox();		
			interruptsBox = new JComboBox();
			servingTimeBox = new JComboBox();
			jobsAddingTimesBox = new JComboBox();
			precedenceRelationsBox = new JComboBox();
			targetFunctionBox = new JComboBox();
			problemField = new JTextField();
			problemField.setEditable(false);
			/* Adding all. */
			settingsPanel = new JPanel(new GridLayout(18, 1));
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.servingSystem")));
			settingsPanel.add(machinesCountBox);
			settingsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.characteristics")));
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.characteristics.interrupts")));
			settingsPanel.add(interruptsBox);
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.characteristics.servingTime")));
			settingsPanel.add(servingTimeBox);
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.characteristics.addingTimes")));
			settingsPanel.add(jobsAddingTimesBox);
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.characteristics.precedenceRelations")));
			settingsPanel.add(precedenceRelationsBox);
			settingsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.targetFunction")));
			settingsPanel.add(targetFunctionBox);
			settingsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
			settingsPanel.add(new JLabel(ResourcesProvider.get().getString("openshop.tree.tasksTypes.problem")));
			settingsPanel.add(problemField);
			
			/* Target panel. */
			uiPanel = new JPanel(new BorderLayout());
			uiPanel.add(settingsPanel, BorderLayout.NORTH);
		}
	}
	
	@Override
	public String toString() {
		return ResourcesProvider.get().getString("openshop.tree.tasksTypes");
	}
	
	private JPanel uiPanel = null;
	
	private JPanel settingsPanel;
	
	private JComboBox machinesCountBox;
	
	private JComboBox interruptsBox;
	
	private JComboBox servingTimeBox;
	
	private JComboBox jobsAddingTimesBox;
	
	private JComboBox precedenceRelationsBox;
	
	private JComboBox targetFunctionBox;
	
	private JTextField problemField;

}
