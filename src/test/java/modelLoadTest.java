import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.Test;
import org.obeonetwork.dataLoader.EMFModelLoad;

import junit.framework.TestCase;

/**
 * @author XX
 *
 */
public class modelLoadTest extends TestCase {
  List<EClass> eclasses;

  public void setUp() throws Exception {
    super.setUp();
    EMFModelLoad loader = new EMFModelLoad();
    ResourceSet resSet = loader.load();
    // StateMachine
    EPackage packageRoot = loader.loadEcore(resSet, "src/main/resources/metamodels/DI.ecore");
    EList<EClassifier> eClassifiers = packageRoot.getEClassifiers();
    eclasses = eClassifiers.stream().filter(eClassifier -> (eClassifier instanceof EClass))
        .map(eClassifier -> (EClass) eClassifier).collect(Collectors.toList());
  }

  public void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test the number of Eclasses.
   */
  @Test
  public void testNbEclass() {
    assertEquals(10, eclasses.size());
  }

  /**
   * Test the names expected for Eclasses.
   */
  @Test
  public void testNameEclass() {
    String[] values = { "DiagramElement", "Diagram", "Style", "Node", "Edge", "Shape",
        "LabeledEdge", "Label", "LabeledShape", "Plane" };
    ArrayList<String> expected = new ArrayList<String>(Arrays.asList(values));
    ArrayList<String> eclassList = new ArrayList<String>();
    for (EClass eClass : eclasses) {
      eclassList.add(eClass.getName());
    }
    assertEquals(expected, eclassList);
  }

  /**
   * Test if expected atributes for Eclasse Diagramm was found.
   */
  @Test
  public void testAttributeForEclassDiagram() {
    String[] values = { "name", "documentation", "resolution" };
    ArrayList<String> attributes = new ArrayList<>();
    ArrayList<String> expected = new ArrayList<String>(Arrays.asList(values));
    for (EClass eClass : eclasses) {
      if ("Diagram".equals(eClass.getName())) {
        EList<EAttribute> recup = eClass.getEAllAttributes();
        for (EAttribute elem : recup) {
          attributes.add(elem.getName());
        }
      }
    }
    assertEquals(expected, attributes);
  }

  /**
   * Test if expected operation for Eclasse Plane was found.
   */
  @Test
  public void testOperationForEclassPlane() {
    String[] values = { "plane_element_type" };
    ArrayList<String> operations = new ArrayList<>();
    ArrayList<String> expected = new ArrayList<String>(Arrays.asList(values));
    for (EClass eClass : eclasses) {
      if ("Plane".equals(eClass.getName())) {
        EList<EOperation> recup = eClass.getEAllOperations();
        for (EOperation elem : recup) {
          operations.add(elem.getName());
        }
      }
    }
    assertEquals(expected, operations);
  }

  /**
   * Test if expected refs for Eclasse DiagramElement was found.
   */
  @Test
  public void testOtherRefForEclassDiagramElement() {
    String[] values = { "owningDiagram", "owningElement", "ownedElement", "modelElement", "style" };
    ArrayList<String> otherRefs = new ArrayList<>();
    ArrayList<String> expected = new ArrayList<String>(Arrays.asList(values));
    for (EClass eClass : eclasses) {
      if ("DiagramElement".equals(eClass.getName())) {
        EList<EReference> recup = eClass.getEAllReferences();
        for (EReference elem : recup) {
          otherRefs.add(elem.getName());
        }
      }
    }
    assertEquals(expected, otherRefs);
  }

  /**
   * Test if expected superType for Eclasse DiagramElement was found.
   */
  @Test
  public void testSuperTypeForLabeledEdge() {
    String[] values = { "DiagramElement", "Edge" };
    ArrayList<String> superTypes = new ArrayList<>();
    ArrayList<String> expected = new ArrayList<String>(Arrays.asList(values));
    for (EClass eClass : eclasses) {
      if ("LabeledEdge".equals(eClass.getName())) {
        EList<EClass> recup = eClass.getEAllSuperTypes();
        for (EClass elem : recup) {
          superTypes.add(elem.getName());
        }
      }
    }
    assertEquals(expected, superTypes);
  }
}
