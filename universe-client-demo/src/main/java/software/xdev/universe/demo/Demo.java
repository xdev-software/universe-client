package software.xdev.universe.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import software.xdev.universe.UniverseApiException;
import software.xdev.universe.UniverseClient;


public class Demo
{
	private static final Logger logger = LogManager.getLogger(Demo.class);
	
	public static void main(final String[] args) throws UniverseApiException
	{
		final UniverseClient client = new UniverseClient();
		logger.info(client.main());
	}
}
