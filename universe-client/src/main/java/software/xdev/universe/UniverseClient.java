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

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import org.eclipse.microprofile.config.ConfigProvider;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;


/**
 * Client to communicate with the sessionize API.
 */
public class UniverseClient
{
	public static final String PROPERTY_SESSIONIZE_API_KEY = "universe.api.key";
	public static final String FAILED_TO_EXECUTE_CALL_TO_SESSIONIZE_API = "Failed to execute call to Sessionize-API.";
	
	private String apiKey = null;
	
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
	
	public String main()
	{
		String schema = "type Query{hello: String}";
		
		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);
		
		RuntimeWiring runtimeWiring = newRuntimeWiring()
			.type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
			.build();
		
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		
		GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
		ExecutionResult executionResult = build.execute("{hello}");
		
		return executionResult.getData().toString();
	}
}
