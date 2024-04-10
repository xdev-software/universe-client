package software.xdev;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import software.xdev.universe.UniverseClient;
import software.xdev.universe.UniverseConfiguration;
import software.xdev.universe.requests.get_attendees.Attendee;
import software.xdev.universe.requests.get_attendees.DefaultQuestions;
import software.xdev.universe.requests.get_buyers.Buyer;
import software.xdev.universe.requests.get_events.Event;


public final class Application
{
	public static void main(final String[] args)
	{
		final UniverseClient client = new UniverseClient(new UniverseConfiguration(
			System.getProperty("applicationId"),
			System.getProperty("applicationSecret"),
			System.getProperty("redirectUri"),
			System.getProperty("authorizationCode"),
			System.getProperty("bearerToken")
		));
		
		// STEP 1: Get Authorization Code
		// After getting the authorization code it must be set in the microprofile-config.properties
		// or through client.getConfig().withAuthorizationCode()
		client.withAuthorizationCode(openBrowserToGetAuthorizationCodeAndWaitForInput(client));
		
		// STEP 2: Get Bearer Token
		// After getting the bearer token it must be set in the microprofile-config.properties
		// or through client.getConfig().withBearerToken()
		client.withBearerToken(client.requestBearerToken().getAccessToken());
		
		// STEP 3: Get Host Id
		final String hostId = client.requestHostId();
		
		// STEP 4: Get Events
		final List<Event> events = client.requestEvents(hostId);
		events.forEach(event -> System.out.println(("Event: " + event.getTitle() + "(id:" + event.getId() + ")")));
		
		// STEP 5: Get Buyers
		final List<Buyer> buyers = client.requestBuyersInEvent(events.get(0).getId(), 5, 0);
		buyers.forEach(buyer -> System.out.println(("Buyer: " + buyer.getName())));
		
		// STEP 6: Get Attendees
		final List<Attendee> attendees = client.requestAttendeesInEvent(events.get(0).getId(), 5, 0);
		attendees.forEach(attendee ->
			System.out.println(
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
	
	private static String openBrowserToGetAuthorizationCodeAndWaitForInput(final UniverseClient client)
	{
		if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
		{
			try
			{
				Desktop.getDesktop().browse(new URI(client.getUrlToGetAuthorizationCode()));
			}
			catch(final IOException | URISyntaxException ex)
			{
				throw new RuntimeException(ex);
			}
		}
		try(final Scanner scanner = new Scanner(System.in))
		{
			System.out.println("Please input displayed authorization code:");
			return scanner.nextLine();
		}
	}
	
	private Application()
	{
	}
}
