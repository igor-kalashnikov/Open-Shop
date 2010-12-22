package by.bsu.fami.openshop.gui.controls;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.text.html.*;

import by.bsu.fami.openshop.algorithms.*;
import by.bsu.fami.openshop.interfaces.*;
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
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e instanceof HTMLFrameHyperlinkEvent) {
                        ((HTMLDocument)editorPane.getDocument())
                                .processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e);
                    } else {
                        navigate(e.getURL());
                    }
                }
            }
        });
        
        editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(editorPane, BorderLayout.CENTER);
        editorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
        /* Algorithms List. */
        algorithmsPanel = new JPanel();
        algorithmsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        algorithmsPanel.setLayout(new BorderLayout());
        
        algorithmsList = new JList(new Algorithmized[] { 
        		new HomeAlgorithm(),
        		new ApproximateAlgorithm(),
        		new SearchAlgorithm()
        });
        algorithmsList.setSize(150, 0);
        
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
        
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		getContentPane().add(editorPanel, BorderLayout.CENTER);
		
		getContentPane().add(algorithmsPanel, BorderLayout.WEST);
		
		/* Adding of a menu. */
		
		setJMenuBar(menuBar);
		
		menuBar.add(fileMenu);
		
		fileMenu.add(quitMenuItem);

		/* Create an empty history and navigate to home. */
		navigateHistory = new ArrayList<URL>();
		navigate(new HomeAlgorithm().getUrl());
		algorithmsList.setSelectedIndex(0);
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

	private final ArrayList<URL> navigateHistory;
	
	@Override
	public void navigate(URL url) {
		navigateHistory.add(url);
		logger.info("Navigating to " + url);
		try {
			editorPane.setPage(url);
		} catch (IOException e) {
			editorPane.setText(e.toString());
			logger.warning(e.toString());
		}
	}
	
}
