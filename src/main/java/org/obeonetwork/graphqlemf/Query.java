package org.obeonetwork.graphqlemf;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

/**
 * @author Simon Prévidente
 *
 */
public class Query implements GraphQLRootResolver {

  private final EcoreRepository ecoreRepository;

  /**
   * @param linkRepository
   */
  public Query(EcoreRepository ecoreRepository) {
    this.ecoreRepository = ecoreRepository;
  }

  /**
   * @return links list
   */
  public List<Ecore> allEcores() {
    return ecoreRepository.getAll();
  }
}
