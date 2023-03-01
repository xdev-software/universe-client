[![Latest version](https://img.shields.io/maven-central/v/com.xdev-software/universe-client)](https://mvnrepository.com/artifact/com.xdev-software/universe-client)
[![Build](https://img.shields.io/github/actions/workflow/status/xdev-software/universe-client/checkBuild.yml?branch=develop)](https://github.com/xdev-software/universe-client/actions/workflows/checkBuild.yml?query=branch%3Adevelop)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=xdev-software_universe-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=xdev-software_universe-client)

# universe client for java

A simple Java API for easy usage of [universe](https://universe.com/).
Here is the [documentation of the actual universe API](https://universe.com/api).

## Usage

You can set the **API-Key** and the **API-URL** through the ``microprofile-config.properties`` (
See [microprofile-config-template.properties](universe-client-demo/src/main/resources/META-INF/microprofile-config-template.properties))
or with ``#withApiKey`` and ``#withUrl`` (
see [Demo](universe-client-demo/src/main/java/software/xdev/universe/demo/Demo.java)).

### Example

```java
final universeClient client = new universeClient()
	.withApiKey("TEST-API")
	.withUri("https://your_conference.universe.com/api");
client.getAlluniverseSessions().forEach(
	session->logger.info(session.getSpeakers()+" - "+session.getName())
);
```

## Installation

[Installation guide for the latest release](https://github.com/xdev-software/universe-client/releases/latest#Installation)

## Support

If you need support as soon as possible and you can't wait for any pull request, feel free to
use [our support](https://xdev.software/en/services/support).

## Contributing

See the [contributing guide](./CONTRIBUTING.md) for detailed instructions on how to get started with our project.

## Dependencies and Licenses

View the [license of the current project](LICENSE) or
the [summary including all dependencies](https://xdev-software.github.io/universe-client/dependencies/)
