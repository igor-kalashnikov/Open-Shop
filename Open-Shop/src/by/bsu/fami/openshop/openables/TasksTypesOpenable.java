package by.bsu.fami.openshop.openables;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import by.bsu.fami.openshop.Application;
import by.bsu.fami.openshop.caches.SearchResultsCache;
import by.bsu.fami.openshop.caches.SearchResultsNodesCache;
import by.bsu.fami.openshop.enums.CommonOption;
import by.bsu.fami.openshop.interfaces.Openable;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public class TasksTypesOpenable implements Openable, ItemListener {

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
			initializeSearchButton();
			/* Adding all. */
			initializeSettingsPanel();
			/* Target panel. */
			initializeUiPanel();
			/* Update ... */
			updateProblemField();
		}
	}

	private void initializeSearchButton() {
		searchButton = new JButton(ResourcesProvider.get().getString("openshop.tree.tasksTypes.searchButton"));
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				searchProblems();
			}
		});
	}

	private void searchProblems() {
		String query = problemField.getText();
		DefaultMutableTreeNode resultsNode = null;
		if (SearchResultsNodesCache.get().contains(query)) {
			resultsNode = SearchResultsNodesCache.get().getResult(query);
			Application.selectSearchResult(resultsNode, false);
		} else {
			resultsNode = performNewSearch(problemField.getText());
			Application.selectSearchResult(resultsNode, true);
			SearchResultsNodesCache.get().putResult(query, resultsNode);
		}
	}

	private DefaultMutableTreeNode performNewSearch(String query) {
		Openable[] openables = SearchResultsCache.get().getResult(query);
		DefaultMutableTreeNode resultsNode = new DefaultMutableTreeNode(query + 
				" (" + openables.length + ")");
		for (Openable openable : openables) {
			resultsNode.add(createSearchResultSubnode(openable));
		}
		return resultsNode;
	}
	
	private DefaultMutableTreeNode createSearchResultSubnode(Openable openable) {
		return new DefaultMutableTreeNode(openable);
	}
	
	private void initializeUiPanel() {
		uiPanel = new JPanel(new BorderLayout());
		uiPanel.add(settingsPanel, BorderLayout.NORTH);
	}

	private void initializeProblemField() {
		problemField = new JTextField();
		problemField.setEditable(true);
	}

	private void initializeTargetFunctionBox() {
		targetFunctionBox = new JComboBox(new CommonOption[] {
				CommonOption.TARGET_FUNCTION_EMPTY,
				CommonOption.TARGET_FUNCTION_C_MAX,
				CommonOption.TARGET_FUNCTION_L_MAX,
				CommonOption.TARGET_FUNCTION_C_J,
				CommonOption.TARGET_FUNCTION_U_J,
				CommonOption.TARGET_FUNCTION_T_J,
				CommonOption.TARGET_FUNCTION_W_J_C_J,
				CommonOption.TARGET_FUNCTION_W_J_U_J,
				CommonOption.TARGET_FUNCTION_W_J_T_J
		});
		targetFunctionBox.addItemListener(this);
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
		precedenceRelationsBox.addItemListener(this);
	}

	private void initializeAddingTimesBox() {
		jobsAddingTimesBox = new JComboBox(new CommonOption[] {
				CommonOption.ADDING_TIMES_0,
				CommonOption.ADDING_TIMES_ANY
		});
		jobsAddingTimesBox.addItemListener(this);
	}

	private void initializeSettingsPanel() {
		settingsPanel = new JPanel(new GridLayout(19, 1));
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
		settingsPanel.add(searchButton);
	}

	private void initializeServingTimeBox() {
		servingTimeBox = new JComboBox(new CommonOption[] {
				CommonOption.SERVING_TIME_IDENTITY,
				CommonOption.SERVING_TIME_ANY
		});
		servingTimeBox.addItemListener(this);
	}

	private void initializeInterruptsBox() {
		interruptsBox = new JComboBox(new CommonOption[] {
				CommonOption.INTERRUPTS_DENIED,
				CommonOption.INTERRUPTS_ALLOWED
		});
		interruptsBox.addItemListener(this);
	}

	private void initializeMachinesCountBox() {
		machinesCountBox = new JComboBox(new CommonOption[] {
				CommonOption.MACHINES_COUNT_EMPTY,
				CommonOption.MACHINES_COUNT_ANY,
				CommonOption.MACHINES_COUNT_1,
				CommonOption.MACHINES_COUNT_2,
				CommonOption.MACHINES_COUNT_3,
				CommonOption.MACHINES_COUNT_M
		});
		machinesCountBox.addItemListener(this);
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
	
	private JButton searchButton;

	@Override
	public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			updateProblemField();
		}
	}

	private void updateProblemField() {
		StringBuilder builder = new StringBuilder();
		for (Component component : settingsPanel.getComponents()) {
			if (component instanceof JComboBox) {
				CommonOption option = (CommonOption)((JComboBox)component).getSelectedItem();
				if (option.getOptionValue() != null) {
					if (builder.length() != 0) {
						builder.append("|");
					}
					builder.append(option.getOptionValue());
				}
			}
		}
		problemField.setText(builder.toString());
	}
	
	@Override
	public boolean hasVisualization() {
		return false;
	}
	
	@Override
	public void showVisualization() {
		// Do nothing.
	}

}
