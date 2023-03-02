package software.xdev.universe.demo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import software.xdev.universe.UniverseClient;
import software.xdev.universe.requests.get_buyers.Buyer;
import software.xdev.universe.requests.get_events.Event;


public class Demo
{
	private static final Logger logger = LogManager.getLogger(Demo.class);
	
	public static void main(final String[] args) throws IOException, URISyntaxException
	{
		final UniverseClient client = new UniverseClient();
		
		// STEP 1: Get Authorization Code
		// After getting the authorization code it must be set in the microprofile-config.properties
		// or through client.getConfig().withAuthorizationCode()
		openBrowserToGetAuthorizationCode();
		
		// STEP 2: Get Bearer Token
		// After getting the bearer token it must be set in the microprofile-config.properties
		// or through client.getConfig().withBearerToken()
		final String bearerToken = client.requestBearerToken();
		
		// STEP 3: Get Host Id
		final String hostId = client.requestHostId();
		
		// STEP 4: Get Events
		final List<Event> events = client.requestEvents(hostId);
		events.forEach(event -> logger.info("Event: " + event.getTitle() + "(id:" + event.getId() + ")"));
		
		// STEP 5: Get Buyers
		final List<Buyer> buyers = client.requestBuyersInEvent(events.get(0).getId());
		buyers.forEach(buyer -> logger.info("Buyer: " + buyer.getName()));
	}
	
	private static void openBrowserToGetAuthorizationCode() throws URISyntaxException, IOException
	{
		final UniverseClient client = new UniverseClient();
		if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
		{
			Desktop.getDesktop().browse(new URI(client.getUrlToGetAuthorizationCode()));
		}
	}
}
