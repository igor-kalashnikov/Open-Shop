package by.bsu.fami.openshop.gui.controls;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import by.bsu.fami.openshop.caches.OpenablesCache;
import by.bsu.fami.openshop.interfaces.Openable;
import by.bsu.fami.openshop.interfaces.Showable;
import by.bsu.fami.openshop.openables.*;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * Represents the main window.
 * @author eigenein
 *
 */
public class MainWindowFrame extends JFrame {

	private static final long serialVersionUID = -1845506127795597936L;

	private static final Logger logger = 
		Logger.getLogger(MainWindowFrame.class.getName());
	
	/**
	 * Initializes a new instance.
	 */
	public MainWindowFrame() {
		super(ResourcesProvider.get().getString("openshop.mainWindow.title"));
		
		/* Common. */
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		
		/* Developers label. */
		developersLabel = 
			new JLabel(ResourcesProvider.get().getString("openshop.developers"));
		
		/* Status bar. */
		statusBar = new JPanel();
		statusBar.setLayout(new BorderLayout());
		statusBar.add(developersLabel, BorderLayout.CENTER);
		statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));

		/* Contents pane. */
		contentsPane = null;
		
        /* Contents panel. */
        contentsPanel = new JPanel();
        contentsPanel.setLayout(new BorderLayout());
        contentsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
        /* Navigation. */
        treeModel = new DefaultTreeModel(createNavigationNodes());
        navigationTree = new JTree(treeModel);
        expandNavigationTree();
        navigationTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent evt) {
				Openable openable = getCurrentlySelectedOpenable();
				if (openable != null) {
					if (contentsPane != null) {
						contentsPanel.remove(contentsPane);
					}
					contentsPane = new JScrollPane(openable.getUI());
					contentsPanel.add(contentsPane, BorderLayout.CENTER);
					contentsPanel.updateUI();
					visualizationAvailablePanel.setVisible(openable.hasVisualization());
				}
			}
		});
        navigationPanel = new JPanel();
        navigationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        navigationPanel.setLayout(new BorderLayout());
        navigationPanel.add(new JScrollPane(navigationTree), BorderLayout.CENTER);
        
        /* Menubar. */
        menuBar = new JMenuBar();
        
        /* Menubar -> File */
        fileMenu = new JMenu(ResourcesProvider.get().getString("openshop.menu.file.title"));
        
        /* Menubar -> File -> Quit */
        quitMenuItem = new JMenuItem(ResourcesProvider.get().getString("openshop.menu.file.quit.title"));
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        quitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
        
        /* Visualization available panel. */
        
        visualizationAvailableLabel = new JLabel(
        		ResourcesProvider.get().getString("openshop.visualizationLabel.text"));
        visualizationAvailablePanel = new JPanel(new BorderLayout());
        visualizationAvailablePanel.add(visualizationAvailableLabel, 
        		BorderLayout.CENTER);
        visualizationAvailablePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        showVisualizationButton = new JButton(
        		ResourcesProvider.get().getString("openshop.showVisualizationButton.text"));
        showVisualizationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				getCurrentlySelectedOpenable().showVisualization();
			}
		});
        visualizationAvailablePanel.add(showVisualizationButton, BorderLayout.EAST);
        visualizationAvailablePanel.setVisible(false);
        
        /* Add components. */
        
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		getContentPane().add(contentsPanel, BorderLayout.CENTER);
		getContentPane().add(navigationPanel, BorderLayout.WEST);
		contentsPanel.add(visualizationAvailablePanel, BorderLayout.NORTH);
		
		/* Adding of a menu. */
		
		setJMenuBar(menuBar);
		
		menuBar.add(fileMenu);
		
		fileMenu.add(quitMenuItem);
	}
	
	private void expandNavigationTree() {
		for (int i = 0; i < navigationTree.getRowCount(); i++) {
			navigationTree.expandRow(i);
		}
	}

	private DefaultMutableTreeNode createNavigationNodes() {
		logger.info("Creating nodes ...");
		/* Creating root node. */
		root = new DefaultMutableTreeNode(
				ResourcesProvider.get().getString("openshop.tree.root"));
		/* Creating child nodes. */
		final DefaultMutableTreeNode introductionNode = 
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(IntroductionOpenable.class));
		final DefaultMutableTreeNode definitionsNode = 
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(DefinitionsOpenable.class));
		final DefaultMutableTreeNode algorithmicStatusNode =
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(AlgorithmicalStatusOpenable.class));
		final DefaultMutableTreeNode classificationNode =
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(ClassificationOpenable.class));
		tasksTypesNode =
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(TasksTypesOpenable.class));
		final DefaultMutableTreeNode task1Node =
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(WithoutPreemptionOpenable.class));
		final DefaultMutableTreeNode task2Node =
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(WithPreemptionOpenable.class));
		final DefaultMutableTreeNode task3Node =
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(WithNowaitOpenable.class));
		final DefaultMutableTreeNode task4Node =
			new DefaultMutableTreeNode(OpenablesCache.get().getOpenable(WithTransportationDelaysOpenable.class));
		/* Adding to root. */
		root.add(introductionNode);
		root.add(definitionsNode);
		root.add(algorithmicStatusNode);
		root.add(classificationNode);
		root.add(tasksTypesNode);
		/* Adding to classifications. */
		classificationNode.add(task1Node);
		classificationNode.add(task2Node);
		classificationNode.add(task3Node);
		classificationNode.add(task4Node);
		/* Done. */
		return root;
	}
	
	private Openable getCurrentlySelectedOpenable() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
    		navigationTree.getLastSelectedPathComponent();
		if ((selectedNode != null) && (selectedNode.getUserObject() instanceof Openable)) {
			return (Openable)selectedNode.getUserObject();
		} else {
			return null;
		}
	}
	
	public void selectSearchResult(DefaultMutableTreeNode resultsNode, boolean append) {
		logger.info("Selecting results node. Append: " + append);
		if (append) {
			treeModel.insertNodeInto(resultsNode, tasksTypesNode, 0);
			for (int i = 0; i < resultsNode.getChildCount(); i++) {
				attachAdditionalInfo((DefaultMutableTreeNode)resultsNode.getChildAt(i));
			}
		}
		navigationTree.setSelectionPath(new TreePath(resultsNode.getPath()));
	}
	
	public void attachAdditionalInfo(DefaultMutableTreeNode node) {
		if (node.getUserObject() instanceof ProblemHtmlOpenable) {
			attachAdditionalInfo((ProblemHtmlOpenable)node.getUserObject(), node);
		}
	}
	
	private void attachAdditionalInfo(Openable openable,
			DefaultMutableTreeNode node) {
		ProblemHtmlOpenable problemHtmlOpenable = (ProblemHtmlOpenable)openable;
		String key = "openshop.tree.taskTypes." + problemHtmlOpenable.getAlias();
		String additionalInfo = ResourcesProvider.get().getString(key, null);
		if (additionalInfo != null) {
			String[] entries = additionalInfo.split(" ");
			for (String entry : entries) {
				String visualizationClassName = 
					ResourcesProvider.get().getString(key + "." + entry + ".visualization", null);
				Showable visualization = null;
				if (visualizationClassName != null) {
					try {
						Class<?> clazz = Class.forName(visualizationClassName);
						visualization = (Showable)clazz.newInstance();
					} catch (Exception e) {
						logger.warning(e.toString());
					}
				}
				treeModel.insertNodeInto(new DefaultMutableTreeNode(
						new ParametrizedHtmlOpenable(
								ResourcesProvider.get().getUrl(key + "." + entry + ".url"), 
								ResourcesProvider.get().getString(key + "." + entry),
								visualization
							)
						),
						node,
						0
				);
				logger.info("Attached [" + entry + "]");
			}
		}
	}
	
	private final JPanel statusBar;
	private final JLabel developersLabel;
	private final JPanel contentsPanel;
	private final JMenuBar menuBar;
	private final JMenu fileMenu;
	private final JMenuItem quitMenuItem;
	private final JPanel navigationPanel;
	private final JPanel visualizationAvailablePanel;
	private final JLabel visualizationAvailableLabel;
	private final JButton showVisualizationButton;
	private final JTree navigationTree;
	private JScrollPane contentsPane;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode tasksTypesNode;
	private DefaultTreeModel treeModel;
	
}
