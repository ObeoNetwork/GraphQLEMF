package org.obeonetwork.dataLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EMFModelLoad {

  private static Map<String, File> filesInFolder(String path) throws IOException {
    List<File> filesInFolder = Files.walk(Paths.get(path)).filter(Files::isRegularFile)
        .filter(Files::isReadable).map(Path::toFile).collect(Collectors.toList());
    Map<String, File> files = new HashMap<>();
    for (File file : filesInFolder) {
      files.put(file.getName(), file);
    }
    return files;
  }

  public static Map<String, File> getMetamodelsPath() throws IOException {
    return filesInFolder("src/main/resources/metamodels/");
  }

  public static Map<String, File> getModelsPath() throws IOException {
    return filesInFolder("src/main/resources/models/");
  }

  public ResourceSet load() {
    // Register the XMI resource factory for the .website extension

    Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
    Map<String, Object> m = reg.getExtensionToFactoryMap();
    m.put("*", new XMIResourceFactoryImpl());

    // Obtain a new resource set
    return new ResourceSetImpl();
  }

  public EPackage loadEcore(ResourceSet resSet, String URIpath) {
    // Get the resource
    Resource resource = resSet.getResource(URI.createURI(URIpath), true);

    // Get the first model element and cast it to the right type, in my
    // example everything is hierarchical included in this first node
    EPackage ePackage = (EPackage) resource.getContents().get(0);

    Registry pcr = resSet.getPackageRegistry();
    pcr.put(ePackage.getNsURI(), ePackage);

    return ePackage;
  }

  public EObject loadXMI(ResourceSet resSet, String URIpath) {
    // Get the resource
    Resource resource = resSet.getResource(URI.createURI(URIpath), true);

    // Get the first model element and cast it to the right type, in my
    // example everything is hierarchical included in this first node
    EObject state = resource.getContents().get(0);
    return state;
  }

}