package org.obeonetwork.graphqlemf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Prévidente
 *
 */
public class EcoreRepository {

  private final List<Ecore> ecores;

  /**
   * Constuctor
   */
  public EcoreRepository() {
    this.ecores = new ArrayList<>();

    this.ecores.add(new Ecore("fsm", "http://www.gemoc.org/legacyfsm/fsm", "fsm"));
  }

  /**
   * @return ecores list
   */
  public List<Ecore> getAll() {
    return ecores;
  }

  /**
   * @param ecore
   *          to add
   */
  public void saveEcore(Ecore ecore) {
    this.ecores.add(ecore);
  }

}
