package by.bsu.fami.openshop.gui.controls;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.logging.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.text.html.*;

import by.bsu.fami.openshop.resources.ResourcesProvider;

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
		setSize(640, 480);
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
		page = new JEditorPane();
        page.setEditable(false);
        page.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e instanceof HTMLFrameHyperlinkEvent) {
                        ((HTMLDocument)page.getDocument())
                                .processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e);
                    } else {
                        try {
                            page.setPage(e.getURL());
                        } catch (IOException ioe) {
                            page.setText("IOE: " + ioe);
                            logger.warning(ioe.toString());
                        }
                    }
                }
            }
        });
		
        /* Menubar. */
        menuBar = new JMenuBar();
        
        /* Menubar -> File */
        fileMenu = new JMenu(ResourcesProvider.get().getString("openshop.menu.file.title"));
        
        /* Menubar -> File -> Quit */
        quitMenuItem = new JMenuItem(ResourcesProvider.get().getString("openshop.menu.file.quit.title"));
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        quitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
        
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		getContentPane().add(page, BorderLayout.CENTER);
		
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(quitMenuItem);
	}
	
	private final JPanel statusBar;
	
	private final JLabel developersLabel;
	
	private final JEditorPane page;
	
	private final JMenuBar menuBar;
	
	private final JMenu fileMenu;
	
	private final JMenuItem quitMenuItem;
	
}
