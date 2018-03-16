package org.obeonetwork.graphqlemf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.dataLoader.EMFModelLoad;

/**
 * @author Simon Prévidente
 *
 */
public class EPackageRepository {

  private final List<EPackage> ePackages;

  /**
   * Constuctor
   */
  public EPackageRepository() {
    this.ePackages = new ArrayList<>();

    try {
      Map<String, File> metamodels = EMFModelLoad.getMetamodelsPath();
      Set<String> keys = metamodels.keySet();
      for (String key : keys) {
        EMFModelLoad loader = new EMFModelLoad();
        ResourceSet resSet = loader.load();
        EPackage packageRoot = loader.loadEcore(resSet, metamodels.get(key).getPath());
        this.ePackages.add(packageRoot);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * @return ePackages list
   */
  public List<EPackage> getAll() {
    return ePackages;
  }

  /**
   * @param ePackage
   *          to add
   */
  public void saveEcore(EPackage ePackage) {
    this.ePackages.add(ePackage);
  }

}
