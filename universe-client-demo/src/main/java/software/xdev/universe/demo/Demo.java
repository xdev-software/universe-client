package software.xdev.universe.demo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import software.xdev.universe.UniverseClient;
import software.xdev.universe.requests.get_attendees.Attendee;
import software.xdev.universe.requests.get_attendees.DefaultQuestions;
import software.xdev.universe.requests.get_bearer_token.GetBearerTokenResponse;
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
		final String authorizationCode = openBrowserToGetAuthorizationCodeAndWaitForInput();
		client.getConfig().withAuthorizationCode(authorizationCode);
		
		// STEP 2: Get Bearer Token
		// After getting the bearer token it must be set in the microprofile-config.properties
		// or through client.getConfig().withBearerToken()
		final GetBearerTokenResponse bearerToken = client.requestBearerToken();
		client.getConfig().withBearerToken(bearerToken.getAccessToken());
		
		// STEP 3: Get Host Id
		final String hostId = client.requestHostId();
		
		// STEP 4: Get Events
		final List<Event> events = client.requestEvents(hostId);
		events.forEach(event -> logger.info("Event: " + event.getTitle() + "(id:" + event.getId() + ")"));
		
		// STEP 5: Get Buyers
		final List<Buyer> buyers = client.requestBuyersInEvent(events.get(0).getId(), 5, 0);
		buyers.forEach(buyer -> logger.info("Buyer: " + buyer.getName()));
		
		// STEP 6: Get Attendees
		final List<Attendee> attendees = client.requestAttendeesInEvent(events.get(0).getId(), 5, 0);
		attendees.forEach(attendee ->
			logger.info(
				"Attendee: "
					+ "\n     Name: " + attendee.getTypedAnswerToQuestion(DefaultQuestions.FIRST_NAME)
					+ " " + attendee.getTypedAnswerToQuestion(DefaultQuestions.LAST_NAME)
					+ "\n    Email: " + attendee.getTypedAnswerToQuestion(DefaultQuestions.EMAIL)
					+ "\n  Company: " + attendee.getTypedAnswerToQuestion(DefaultQuestions.COMPANY)
					+ "\n      Job: " + attendee.getTypedAnswerToQuestion(DefaultQuestions.JOB_TITLE)
					+ "\n  Country: " + attendee.getTypedAnswerToQuestion(DefaultQuestions.COUNTRY)
			)
		);
	}
	
	private static String openBrowserToGetAuthorizationCodeAndWaitForInput() throws URISyntaxException, IOException
	{
		final UniverseClient client = new UniverseClient();
		if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
		{
			Desktop.getDesktop().browse(new URI(client.getUrlToGetAuthorizationCode()));
		}
		try(Scanner scanner = new Scanner(System.in))
		{
			System.out.println("Please input displayed authorization code:");
			return scanner.nextLine();
		}
	}
}
