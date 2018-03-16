# GraphQL support for EMF models
[![Build Status](https://travis-ci.org/ObeoNetwork/GraphQLEMF.svg?branch=master)](https://travis-ci.org/ObeoNetwork/GraphQLEMF)

In this subject, the candidates will need to setup a Java based server using regular servlets along with GraphQL Java [1]. The students will have to determine what does a GraphQL API to manipulate EMF model should look like and implement it. This generic API should allow anyone to navigate and manipulate any EMF model in the server. This API should be enough to query both the resources and their meta-models. For this exercise, you can just define a resource set in your server with a specific set of meta-models (ecore, library, etc, for example) along with a couple of sample models.
On top of this generic API, the students will have to investigate the creation of a GraphQL schema dynamically from the definitions of the meta-models.
With this dynamic GraphQL API, the GraphQL clients should be able to navigate in the graph of data using the concept of their business.
For those who success in completing both tasks, the GraphQL API could be improved with support for EMF Edit and even with an investigation of GraphQL Subscriptions.
1: https://github.com/graphql-java/graphql-java

## Getting started

```sh
# Clone repository
git clone git@github.com:ObeoNetwork/GraphQLEMF.git
cd GraphQLEMF
```

## Usage

```sh
mvn install
mvn test
mvn jetty:run -e
```

Now you can open http://localhost:8080/

An example GraphQL query might look like:
```
{
  allEPackages {
    # EPackage attributes
    name
    nsURI
    nsPrefix

    # EPackage methods
    EClassifiers {
      name
    }
    EFactoryInstance {
      # EFactory
      EPackage {
        # EPackage attributes
        name
        # …
      }
    }
    ESubpackages {
      # EPackage
      name

      # EPackages methods
      ESuperPackage {
        name
        nsURI
        nsPrefix
      }
    }

  }
}
```
