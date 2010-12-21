package by.bsu.fami.openshop;

import java.util.logging.*;

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
	
	public static void main(String[] args) {
		logger.info(ResourcesProvider.get().getString("openshop.log.starting"));
		
		splash = new SplashFrame();
		splash.setVisible(true);
		
		try {
			for (float progress = 0.0f; progress <= 1.0f; progress += 0.010f) {
				splash.updateProgress(progress);
				Thread.sleep(30);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
	}

}
