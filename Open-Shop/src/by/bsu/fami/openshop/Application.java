package by.bsu.fami.openshop;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import by.bsu.fami.openshop.caches.OpenablesCache;
import by.bsu.fami.openshop.gui.controls.MainWindowFrame;
import by.bsu.fami.openshop.gui.controls.SplashFrame;
import by.bsu.fami.openshop.openables.*;
import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * The application entry point.
 * @author eigenein
 *
 */
public class Application {

	public static final Logger LOGGER = 
		Logger.getLogger(Application.class.getName());
	
	private static SplashFrame splash;
	
	private static MainWindowFrame mainWindow;
	
	public static void main(String[] args) {
		LOGGER.info(ResourcesProvider.get().getString("openshop.log.starting"));
		
		setLookAndFeel();
		
		loadOpenablesAndShowProgress();
		
		mainWindow = new MainWindowFrame();
		mainWindow.setVisible(true);
	}

	private static void loadOpenablesAndShowProgress() {
		splash = new SplashFrame();
		splash.setVisible(true);
		ArrayList<Class<?>> openablesClassesList = createOpenablesClassesList();
		float openablesCount = openablesClassesList.size();
		float progress = 0.0f;
		for (Class<?> clazz : openablesClassesList) {
			OpenablesCache.get().getOpenable(clazz);
			progress += 1.0f / openablesCount;
			splash.updateProgress(progress);
		}
		splash.setVisible(false);
	}
	
	private static void setLookAndFeel() {
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) { 
	    	LOGGER.warning(e.toString());
	    }
	}
	
	private static ArrayList<Class<?>> createOpenablesClassesList() {
		ArrayList<Class<?>> openablesClassesList = new ArrayList<Class<?>>();
		openablesClassesList.add(IntroductionOpenable.class);
		openablesClassesList.add(DefinitionsOpenable.class);
		openablesClassesList.add(AlgorithmicalStatusOpenable.class);
		openablesClassesList.add(ClassificationOpenable.class);
		openablesClassesList.add(WithoutPreemptionOpenable.class);
		openablesClassesList.add(WithPreemptionOpenable.class);
		openablesClassesList.add(WithNowaitOpenable.class);
		openablesClassesList.add(WithTransportationDelaysOpenable.class);
		openablesClassesList.add(TasksTypesOpenable.class);
		return openablesClassesList;
	}
	
	public static void selectSearchResult(DefaultMutableTreeNode resultsNode, boolean append) {
		mainWindow.selectSearchResult(resultsNode, append);
	}

}
