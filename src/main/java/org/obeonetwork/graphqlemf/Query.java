package org.obeonetwork.graphqlemf;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

/**
 * @author Simon Prévidente
 *
 */
public class Query implements GraphQLRootResolver {

  private final EPackageRepository ePackageRepository;

  /**
   * @param ePackageRepository
   */
  public Query(EPackageRepository ePackageRepository) {
    this.ePackageRepository = ePackageRepository;
  }

  /**
   * @return links list
   */
  public List<EPackage> allEPackages() {
    return ePackageRepository.getAll();
  }
}
