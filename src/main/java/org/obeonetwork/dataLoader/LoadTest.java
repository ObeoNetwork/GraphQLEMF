package org.obeonetwork.dataLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class LoadTest {

  public static void main(String[] args) {
    // Loading the existing model
    EMFModelLoad loader = new EMFModelLoad();

    ResourceSet resSet = loader.load();

    // StateMachine
    EPackage packageRoot = loader.loadEcore(resSet);

    EList<EClassifier> eClassifiers = packageRoot.getEClassifiers();

    List<EClass> eclasses = eClassifiers.stream()
        .filter(eClassifier -> (eClassifier instanceof EClass))
        .map(eClassifier -> (EClass) eClassifier).collect(Collectors.toList());

    for (EClass eClass : eclasses) {
      System.out.println(">> getName from main MachineState : " + eClass.getName());
      List<ENamedElement> elements = new ArrayList<>();
      EList<EAttribute> attributes = eClass.getEAllAttributes();
      elements.addAll(attributes);
      // EList<EReference> reference = eClass.getEAllContainments();
      // elements.addAll(reference);
      EList<EOperation> operations = eClass.getEAllOperations();
      elements.addAll(operations);
      // Permet d'afficher le nom de la super classe
      EList<EClass> types = eClass.getEAllSuperTypes();
      elements.addAll(types);
      EList<EReference> otherRef = eClass.getEAllReferences();
      elements.addAll(otherRef);

      for (ENamedElement element : elements) {
        System.out.println(element.getName());
      }

      // System.out.println(reference);
      //
      // for (EReference eReference : reference) {
      // System.out.println("REFERENCE--->" + eReference.getName());
      // // System.out.println(eAttribute.getName() + " : " + eAttribute.getDefaultValue());
      // // System.out.println(eAttribute.getUpperBound() + " : " + eAttribute.getDefaultValue());
      // }
      //
      // for (EAttribute eAttribute : attributes) {
      // System.out.println("ATTRIBUTE--->" + eAttribute.getName());
      // }
      // for (EOperation eOperation : operations) {
      // System.out.println("OPERATION--->" + eOperation.getName());
      // }
      // for (EOperation eOperation : operations) {
      // System.out.println("OPERATION--->" + eOperation.getName());
      // }
      //

      System.out.println("------");
      // for (EOperation operation : eClass.getEOperations()) {
      // System.out.println(operation.getName());
      // eClass.getOperationID(operation);
      // }
    }
    System.out.println("-------------------");
    /******** CODE A GARDER -> PERMET DE LIRE LE .xmi **********/
    // EObject xmi = loader.loadXMI(resSet);
    // EList<EObject> xmiContents = xmi.eContents();
    // for (EObject eObject : xmiContents) {
    // System.out.println(eObject.eClass().getName());
    // List<EStructuralFeature> sf = eObject.eClass().getEAllStructuralFeatures();
    // for (EStructuralFeature feature : sf) {
    // Object value = eObject.eGet(feature);
    // System.out.println(feature.getName() + " : " + value);
    // // System.out.println(value);
    // }
    // }
  }
}
