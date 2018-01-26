package org.obeonetwork.dataLoader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EMFModelLoad {

	public ResourceSet load() {
        // Register the XMI resource factory for the .website extension

        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("*", new XMIResourceFactoryImpl());

        // Obtain a new resource set
        return new ResourceSetImpl();
	}
	
    public EPackage loadEcore(ResourceSet resSet) {
        // Get the resource
        Resource resource = resSet.getResource(URI
                .createURI("model/fsm.ecore"), true);
                
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        EPackage ePackage = (EPackage) resource.getContents().get(0);
        
        Registry pcr = resSet.getPackageRegistry();
        pcr.put(ePackage.getNsURI(), ePackage);
        
        return ePackage;
    }

    public EObject loadXMI(ResourceSet resSet) {
        // Get the resource
        Resource resource = resSet.getResource(URI
                .createURI("model/StateMachine.xmi"), true);
                
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        EObject state = resource.getContents().get(0);
        return state;
    }

}