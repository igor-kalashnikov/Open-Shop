package by.bsu.fami.openshop.openables;

import java.awt.*;

import javax.swing.*;

import by.bsu.fami.openshop.enums.CommonOption;
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
			initializeMachinesCountBox();		
			initializeInterruptsBox();
			initializeServingTimeBox();
			initializeAddingTimesBox();
			initializePrecedenceRelationsBox();
			initializeTargetFunctionBox();
			initializeProblemField();
			/* Adding all. */
			initializeSettingsPanel();
			/* Target panel. */
			initializeUiPanel();
		}
	}

	private void initializeUiPanel() {
		uiPanel = new JPanel(new BorderLayout());
		uiPanel.add(settingsPanel, BorderLayout.NORTH);
	}

	private void initializeProblemField() {
		problemField = new JTextField();
		problemField.setEditable(false);
	}

	private void initializeTargetFunctionBox() {
		targetFunctionBox = new JComboBox(new CommonOption[] {
				CommonOption.TARGET_FUNCTION_C_MAX,
				CommonOption.TARGET_FUNCTION_L_MAX,
				CommonOption.TARGET_FUNCTION_C_J,
				CommonOption.TARGET_FUNCTION_U_J,
				CommonOption.TARGET_FUNCTION_T_J,
				CommonOption.TARGET_FUNCTION_W_J_C_J,
				CommonOption.TARGET_FUNCTION_W_J_U_J,
				CommonOption.TARGET_FUNCTION_W_J_T_J
		});
	}

	private void initializePrecedenceRelationsBox() {
		precedenceRelationsBox = new JComboBox(new CommonOption[] {
				CommonOption.PRECEDENCE_RELATIONS_NONE,
				CommonOption.PRECEDENCE_RELATIONS_PREC,
				CommonOption.PRECEDENCE_RELATIONS_TREE,
				CommonOption.PRECEDENCE_RELATIONS_IN_TREE,
				CommonOption.PRECEDENCE_RELATIONS_OUT_TREE,
				CommonOption.PRECEDENCE_RELATIONS_CHAINS
		});
	}

	private void initializeAddingTimesBox() {
		jobsAddingTimesBox = new JComboBox(new CommonOption[] {
				CommonOption.ADDING_TIMES_0,
				CommonOption.ADDING_TIMES_ANY
		});
	}

	private void initializeSettingsPanel() {
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
	}

	private void initializeServingTimeBox() {
		servingTimeBox = new JComboBox(new CommonOption[] {
				CommonOption.SERVING_TIME_IDENTITY,
				CommonOption.SERVING_TIME_ANY
		});
	}

	private void initializeInterruptsBox() {
		interruptsBox = new JComboBox(new CommonOption[] {
				CommonOption.INTERRUPTS_DENIED,
				CommonOption.INTERRUPTS_ALLOWED
		});
	}

	private void initializeMachinesCountBox() {
		machinesCountBox = new JComboBox(new CommonOption[] {
				CommonOption.MACHINES_COUNT_ANY,
				CommonOption.MACHINES_COUNT_1,
				CommonOption.MACHINES_COUNT_2,
				CommonOption.MACHINES_COUNT_3,
				CommonOption.MACHINES_COUNT_M
		});
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
