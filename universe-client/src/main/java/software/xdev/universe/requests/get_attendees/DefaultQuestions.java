package software.xdev.universe.requests.get_attendees;

public class DefaultQuestions
{
	public static Attendee.TypedQuestion<String> COMPANY = new Attendee.TypedQuestion<>(
		"Company / Organisation",
		value -> value);
	public static Attendee.TypedQuestion<String> JOB_TITLE =
		new Attendee.TypedQuestion<>("Job Title", value -> value);
	public static Attendee.TypedQuestion<String> COUNTRY = new Attendee.TypedQuestion<>(
		"Country",
		value -> value);
	public static Attendee.TypedQuestion<String> FIRST_NAME = new Attendee.TypedQuestion<>(
		"First Name",
		value -> value);
	public static Attendee.TypedQuestion<String> LAST_NAME = new Attendee.TypedQuestion<>(
		"Last Name",
		value -> value);
	public static Attendee.TypedQuestion<String> EMAIL = new Attendee.TypedQuestion<>(
		"Email",
		value -> value);
}
