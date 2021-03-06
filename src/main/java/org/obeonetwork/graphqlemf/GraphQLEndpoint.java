package org.obeonetwork.graphqlemf;

import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.SchemaParser;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

/**
 * GraphQL Endpoint
 * 
 * @author simon
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

  /**
   * Constructor
   */
  public GraphQLEndpoint() {
    super(buildSchema());
  }

  private static GraphQLSchema buildSchema() {
    EPackageRepository ePackageRepository = new EPackageRepository();
    return SchemaParser.newParser().file("schema.graphqls").resolvers(new Query(ePackageRepository))
        .build().makeExecutableSchema();
  }
}
