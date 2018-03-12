import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
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
   * Test the number of Eclasses
   */
  @Test
  public void testNbEclass() {
    assertEquals(10, eclasses.size());
  }

  /**
   * Test the names expected for Eclasses
   */
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
}
