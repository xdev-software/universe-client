/*
 * Copyright © 2023 XDEV Software (https://xdev.software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
