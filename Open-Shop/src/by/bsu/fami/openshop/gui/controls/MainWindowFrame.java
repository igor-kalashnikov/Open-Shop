package by.bsu.fami.openshop.gui.controls;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.text.html.*;

import by.bsu.fami.openshop.algorithms.*;
import by.bsu.fami.openshop.interfaces.*;
import by.bsu.fami.openshop.resources.FakePagesLoader;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * Represents the main window.
 * @author eigenein
 *
 */
public class MainWindowFrame extends JFrame implements Navigateable {

	private static final long serialVersionUID = -1845506127795597936L;

	private static final Logger logger = 
		Logger.getLogger(MainWindowFrame.class.getName());
	
	/**
	 * Initializes a new instance.
	 */
	public MainWindowFrame() {
		super(ResourcesProvider.get().getString("openshop.mainWindow.title"));
		
		/* Common. */
		setSize(800, 600);
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
		
		/* Editor pane. */
		editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setBackground(Color.white);
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e instanceof HTMLFrameHyperlinkEvent) {
                        ((HTMLDocument)editorPane.getDocument())
                                .processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e);
                    } else {
                        navigate(e.getURL(), true);
                    }
                }
            }
        });
        
        editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(new JScrollPane(editorPane), BorderLayout.CENTER);
        editorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
        /* Algorithms List. */
        algorithmsPanel = new JPanel();
        algorithmsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        algorithmsPanel.setLayout(new BorderLayout());
        
        Vector<Algorithmized> algorithms = new Vector<Algorithmized>();
        algorithms.add(new HomeAlgorithm());
        algorithms.add(new ApproximateAlgorithm());
        algorithms.add(new SearchAlgorithm());
        algorithms.addAll(new FakePagesLoader().loadFakePages());
        algorithmsList = new JList(algorithms);
        algorithmsList.setSize(150, 0);
        algorithmsList.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Algorithmized element = 
					(Algorithmized)algorithmsList.getSelectedValue();
				visualizationAvailablePanel.setVisible(element.hasVisualization());
				navigate(element.getUrl(), true);
        	}
		});
        
        algorithmsPanel.add(algorithmsList, BorderLayout.CENTER);
        
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
			public void actionPerformed(ActionEvent arg0) {
				Algorithmized element = 
					(Algorithmized)algorithmsList.getSelectedValue();
				element.startVisualization();
			}
		});
        visualizationAvailablePanel.add(showVisualizationButton, BorderLayout.EAST);
        visualizationAvailablePanel.setVisible(false);
        
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		getContentPane().add(editorPanel, BorderLayout.CENTER);
		
		getContentPane().add(algorithmsPanel, BorderLayout.WEST);
		
		editorPanel.add(visualizationAvailablePanel, BorderLayout.NORTH);
		
		/* Adding of a menu. */
		
		setJMenuBar(menuBar);
		
		menuBar.add(fileMenu);
		
		fileMenu.add(quitMenuItem);

		/* Toolbar. */
		goBackButton = new JButton(ResourcesProvider.get().getString(
				"openshop.toolbar.goBackButton.text"));
		goBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				logger.info("History index is " + historyIndex);
				if (historyIndex > 0) {
					historyIndex -= 1;
					navigate(navigateHistory.get(historyIndex), false);
				}
			}
		});
		goForwardButton = new JButton(ResourcesProvider.get().getString(
				"openshop.toolbar.goForwardButton.text"));
		goForwardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				logger.info("History index is " + historyIndex);
				if (historyIndex < navigateHistory.size() - 1) {
					historyIndex += 1;
					navigate(navigateHistory.get(historyIndex), false);
				}
			}
		});
		toolbar = new JToolBar();
		toolbar.add(goBackButton);
		toolbar.add(goForwardButton);
				
		getContentPane().add(toolbar, BorderLayout.NORTH);
		
		/* Create an empty history and navigate to home. */
		navigateHistory = new ArrayList<URL>();
		navigate(new HomeAlgorithm().getUrl(), true);
	}
	
	private final JPanel statusBar;
	private final JLabel developersLabel;
	private final JEditorPane editorPane;
	private final JPanel editorPanel;
	private final JList algorithmsList;
	private final JMenuBar menuBar;
	private final JMenu fileMenu;
	private final JMenuItem quitMenuItem;
	private final JPanel algorithmsPanel;
	private int historyIndex = -1;
	private final ArrayList<URL> navigateHistory;
	private final JPanel visualizationAvailablePanel;
	private final JLabel visualizationAvailableLabel;
	private final JButton showVisualizationButton;
	private final JToolBar toolbar;
	private final JButton goBackButton;
	private final JButton goForwardButton;
	
	@Override
	public void navigate(URL url, boolean addToHistory) {
		if (addToHistory) {
			navigateHistory.add(url);
			historyIndex += 1;
		}
		logger.info("Navigating to " + url + " setting historyIndex to " + historyIndex);
		/* Hotfix. I know that this is the bad code. */
		int size = algorithmsList.getModel().getSize();
		for (int i = 0; i < size; i++) {
			Algorithmized algorithmized = (Algorithmized)algorithmsList.getModel().getElementAt(i);
			if (algorithmized.getUrl().equals(url)) {
				algorithmsList.setSelectedIndex(i);
				visualizationAvailablePanel.setVisible(
						algorithmized.hasVisualization());
				break;
			}
		}
		try {
			editorPane.setPage(url);
		} catch (IOException e) {
			editorPane.setText(e.toString());
			logger.warning(e.toString());
		}
	}
	
}
