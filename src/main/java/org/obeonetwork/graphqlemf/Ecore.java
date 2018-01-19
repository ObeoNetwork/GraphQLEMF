package org.obeonetwork.graphqlemf;

/**
 * @author Simon Prévidente
 *
 */
public class Ecore {

  private final String name;
  private final String nsURI;
  private final String nsPrefix;

  /**
   * @param name
   * @param nsURI
   * @param nsPrefix
   */
  public Ecore(String name, String nsURI, String nsPrefix) {
    super();
    this.name = name;
    this.nsURI = nsURI;
    this.nsPrefix = nsPrefix;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @return nsURI
   */
  public String getNsURI() {
    return nsURI;
  }

  /**
   * @return nsPrefix
   */
  public String getNsPrefix() {
    return nsPrefix;
  }

}
