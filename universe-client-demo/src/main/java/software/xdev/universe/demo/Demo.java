package software.xdev.universe.demo;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import software.xdev.universe.UniverseClient;
import software.xdev.universe.dtos.get_buyers.Buyer;


public class Demo
{
	private static final Logger logger = LogManager.getLogger(Demo.class);
	
	public static void main(final String[] args) throws IOException
	{
		final UniverseClient client = new UniverseClient();
		final List<Buyer> buyers = client.requestBuyersInEvent("EVENT_ID");
		logger.info("Amount of Buyers: " + buyers.size());
		buyers.forEach(buyer -> logger.info("Buyer: " + buyer.getName()));
	}
}
