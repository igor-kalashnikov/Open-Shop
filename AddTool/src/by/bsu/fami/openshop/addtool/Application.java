package by.bsu.fami.openshop.addtool;

import java.io.*;
import java.util.logging.Logger;
import java.util.zip.*;

public class Application {

	private static final Logger logger = 
		Logger.getLogger(Application.class.getName());
	
	private void printHelp() {
		System.out.println("Usage: java -jar AddTool.jar Open-Shop.jar directory-to-add");
	}
	
	private void run(String[] args) {
		if (args.length != 2) {
			printHelp();
		} else {
			addResource(args[0], args[1]);
		}
	}
	
	private void addResource(String jarFile, String resourceDirectory) {
		ZipInputStream input = null;
		try {
			input = new ZipInputStream(new FileInputStream(jarFile));
		} catch (Exception e) {
			logger.severe(e.toString());
		}
	}
	
	public static void main(String[] args) {
		new Application().run(args);
	}

}
