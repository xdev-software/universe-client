[![Latest version](https://img.shields.io/maven-central/v/software.xdev/universe-client?logo=apache%20maven)](https://mvnrepository.com/artifact/software.xdev/universe-client)
[![Build](https://img.shields.io/github/actions/workflow/status/xdev-software/universe-client/checkBuild.yml?branch=develop)](https://github.com/xdev-software/universe-client/actions/workflows/checkBuild.yml?query=branch%3Adevelop)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=xdev-software_universe-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=xdev-software_universe-client)

# Universe client for Java

A simple Java client for the [Universe API](https://developers.universe.com).

## Usage

The two basic entries are the ``ApplicationId`` and the ``RedirectUri``. They **must** be set.

If these two entries are set, you need to get ``AuthorizationCode``. This is a one-time-only code, which
you get by calling the URL provided by ``client.getUrlToGetAuthorizationCode()``.

With this ``AuthorizationCode`` you can request a ``BearerToken``. You can can request the ``BearerToken`` only 
one time with one ``AuthorizationCode``. After that the ``AuthorizationCode`` is invalidated.
This can be achieved with ``client.requestBearerToken()``.
The ``BearerToken`` can be used for all your future requests. It is usually valid for one month.

If the ``BearerToken`` is set, you can use the actual API calls.

### Example
Full example, see [Demo](universe-client-demo/src/main/java/software/xdev/Application.java).

```java
UniverseClient client = new UniverseClient(yourConfig);

// Get Events
List<Event> events = client.requestEvents(hostId);
events.forEach(event -> logger.info("Event: " + event.getTitle() + "(id:" + event.getId() + ")"));

// Get Attendees
List<Attendee> attendees = client.requestAttendeesInEvent(events.get(0).getId(), 5, 0);
```

## GraphQL
We are using the [Universe GraphQL API](https://developers.universe.com/docs/graphql).
Here are some thing to help with orientation with GraphQL.

You can test requests on [Universes GraphiQL (Explorer)](https://www.universe.com/graphiql);

Get Schema:
```
{
  __schema {
    queryType {
      fields {
        name
      }
    }
  }
}
```

## Installation
[Installation guide for the latest release](https://github.com/xdev-software/universe-client/releases/latest#Installation)


## Support
If you need support as soon as possible and you can't wait for any pull request, feel free to use [our support](https://xdev.software/en/services/support).

## Contributing
See the [contributing guide](./CONTRIBUTING.md) for detailed instructions on how to get started with our project.

## Dependencies and Licenses
View the [license of the current project](LICENSE) or the [summary including all dependencies](https://xdev-software.github.io/universe-client/dependencies)
