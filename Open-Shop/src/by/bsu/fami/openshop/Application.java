package by.bsu.fami.openshop;

import java.util.logging.*;

import by.bsu.fami.openshop.resources.*;

/**
 * The application entry point.
 * @author eigenein
 *
 */
public class Application {

	private static final Logger logger = 
		Logger.getLogger(Application.class.getName());
	
	public static void main(String[] args) {
		logger.info(ResourcesProvider.get().getString("openshop.log.starting"));
	}

}
