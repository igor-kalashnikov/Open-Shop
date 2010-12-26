package by.bsu.fami.openshop.caches;

import java.util.Hashtable;
import java.util.logging.Logger;

import by.bsu.fami.openshop.interfaces.Openable;

public class OpenablesCache {

	private OpenablesCache() {
		cache = new Hashtable<Class<?>, Openable>();
	}
	
	private final static OpenablesCache instance = new OpenablesCache();
	
	public static OpenablesCache get() {
		return instance;
	}
	
	private final Hashtable<Class<?>, Openable> cache;
	
	private static final Logger logger = 
		Logger.getLogger(OpenablesCache.class.getName());
	
	public Openable getOpenable(Class<?> openableClass) {
		if (cache.containsKey(openableClass)) {
			return cache.get(openableClass);
		} else {
			return instantiateClass(openableClass);
		}
	}

	private Openable instantiateClass(Class<?> openableClass) {
		try {
			logger.info("Initializing of " + openableClass.getName());
			Openable openable = (Openable)openableClass.newInstance();
			openable.initialize();
			cache.put(openableClass, openable);
			return openable;
		} catch (Exception e) {
			logger.severe(e.toString());
			return null;
		}
	}
	
}
