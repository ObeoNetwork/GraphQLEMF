package org.obeonetwork.dataLoader;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
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
			.map(eClassifier -> (EClass) eClassifier)
			.collect(Collectors.toList());

		for (EClass eClass : eclasses) {
			System.out.println(">> getName from main MachineState : " + eClass.getName());
			EList<EAttribute> attributes = eClass.getEAllAttributes();
			for (EAttribute eAttribute : attributes) {
				System.out.println(eAttribute.getName() + " : "+ eAttribute.getDefaultValue());
			}
			
			for (EOperation operation: eClass.getEOperations()) {
				System.out.println(operation.getName());
				eClass.getOperationID(operation);
			}
		}
		System.out.println("-------------------");
		EObject xmi = loader.loadXMI(resSet);
		EList<EObject> xmiContents = xmi.eContents();
		for (EObject eObject : xmiContents) {
			System.out.println(eObject.eClass().getName());
			List<EStructuralFeature> sf = eObject.eClass().getEAllStructuralFeatures();
			for (EStructuralFeature feature : sf) {
				Object value = eObject.eGet(feature);
				System.out.println(feature.getName() + " : " + value);
//				System.out.println(value);
			}
		}
	}
}
