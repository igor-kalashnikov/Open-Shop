package by.bsu.fami.openshop;

import java.util.logging.*;

import javax.swing.UIManager;

import by.bsu.fami.openshop.gui.controls.MainWindowFrame;
import by.bsu.fami.openshop.gui.controls.SplashFrame;
import by.bsu.fami.openshop.resources.*;

/**
 * The application entry point.
 * @author eigenein
 *
 */
public class Application {

	private static final Logger logger = 
		Logger.getLogger(Application.class.getName());
	
	private static SplashFrame splash;
	
	private static MainWindowFrame mainWindow;
	
	public static void main(String[] args) {
		logger.info(ResourcesProvider.get().getString("openshop.log.starting"));
		
		setLookAndFeel();
		
		splash = new SplashFrame();
		splash.setVisible(true);
		// TODO: Make this to do a real stuff.
		try {
			for (float progress = 0.0f; progress <= 1.0f; progress += 0.010f) {
				splash.updateProgress(progress);
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			logger.warning(e.toString());
		} finally {
			splash.setVisible(false);
		}
		
		mainWindow = new MainWindowFrame();
		mainWindow.setVisible(true);
	}
	
	private static void setLookAndFeel() {
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) { 
	    	logger.warning(e.toString());
	    }
	}

}
