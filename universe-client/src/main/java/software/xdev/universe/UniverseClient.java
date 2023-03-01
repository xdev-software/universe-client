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
package software.xdev.universe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.microprofile.config.ConfigProvider;


/**
 * Client to communicate with the sessionize API.
 */
public class UniverseClient
{
	public static final String PROPERTY_SESSIONIZE_API_KEY = "universe.api.key";
	public static final String FAILED_TO_EXECUTE_CALL_TO_SESSIONIZE_API = "Failed to execute call to Sessionize-API.";
	public static final String UNIVERSE_GRAPHQL_URL = "https://www.universe.com/graphql";
	private String apiKey = null;
	
	public static final String requestBuyers(String eventId)
	{
		return " \"query\": \"query GraphqlExample {\n"
			+ "    event(id: " + eventId + ") {\n"
			+ "      orders {\n"
			+ "        totalCount\n"
			+ "        nodes(limit: 2) {\n"
			+ "          id\n"
			+ "          state\n"
			+ "          createdAt\n"
			+ "          orderItems {\n"
			+ "            totalCount\n"
			+ "          }\n"
			+ "          timeSlot {\n"
			+ "            startAt\n"
			+ "            endAt\n"
			+ "          }\n"
			+ "          buyer {\n"
			+ "            name\n"
			+ "            email\n"
			+ "          }\n"
			+ "        }\n"
			+ "      }\n"
			+ "    }\n"
			+ "  }\"";
	}
	
	public static CloseableHttpResponse callGraphQLService(String url, String query)
		throws URISyntaxException, IOException
	{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		URI uri = new URIBuilder(request.getURI())
			.addParameter("query", query)
			.build();
		request.setURI(uri);
		return client.execute(request);
	}
	
	public UniverseClient withApiKey(final String apiKey)
	{
		this.apiKey = apiKey;
		return this;
	}
	
	private String getApiKey()
	{
		if(this.apiKey == null)
		{
			this.apiKey = ConfigProvider.getConfig().getValue(PROPERTY_SESSIONIZE_API_KEY, String.class);
			if(this.apiKey == null)
			{
				throw new RuntimeException(
					"Sessionize API-Key not configured. Please set Property '" + PROPERTY_SESSIONIZE_API_KEY
						+ "' in the properties or call method #withApiKey.");
			}
		}
		return this.apiKey;
	}
	
	public String main() throws IOException, URISyntaxException
	{
		HttpResponse httpResponse = callGraphQLService(UNIVERSE_GRAPHQL_URL, requestBuyers("63d23a98cf489d0021b396b3"
		));
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
			.getContent(), StandardCharsets.UTF_8)))
		{
			return reader.lines().parallel().collect(Collectors.joining("\n"));
		}
		//
		// String schema = "type Query{hello: String}";
		//
		// SchemaParser schemaParser = new SchemaParser();
		// TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);
		//
		// RuntimeWiring runtimeWiring = newRuntimeWiring()
		// 	.type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
		// 	.build();
		//
		// SchemaGenerator schemaGenerator = new SchemaGenerator();
		// GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		//
		// GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
		// ExecutionResult executionResult = build.execute("{hello}");
		//
		// return executionResult.getData().toString();
	}
}
