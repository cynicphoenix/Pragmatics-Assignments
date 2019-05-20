# FACTORY PATTERN

## Overview

Suppose you are working at a new startup where you have created a collection of specialized algorithms. Interface of each algorithm is well defined in terms of input and output of the algorithm. For example:

- // Class: AlgorithmFn1
public java.util.List compute(java.util.Map<String, Float> data) 

- // Class: AlgorithmFn2
public java.util.Map<String, Integer> compute(java.util.Collection data) 

and so on…

The startup plans to sell these specialized algorithms as libraries (.jar file) to its client organizations for use in their software applications. Internal implementation of these algorithms keep evolving without affecting the interfaces of algorithms. 


You need to implement suitable set of classes (in Java) which can be used by the client organizations to:

Obtain new instances of concrete classes that implement the respective algorithms.

Shield the client’s code from the future changes in your implementations of the algorithms. That is, you need to design suitable classes which will exploit the object oriented programming principles for creating families of related objects without specifying the names of concrete classes of such objects.


As usual, you should submit well documented source code for your solution along with a proper manual that describes how to build and run your program, main design/logic of your program etc. At the very least your manual should describe how your design and program meets the above mentioned requirements.

## Directories

```bash
|-factory_algo
    |-2017csb1189
      |-algorithms.jar
      |-main.java
    |-algorithms
      |-GetAlgorithFactory.java
      |-MathAlgorithm.java
      |-SortingAlgorithm.java
      |-bubblesort.java
      |-gcd.java
      |-insertionsort.java
      |-lcm.java
      |-mergesort.java
      |-power.java
      |-quicksort.java
      |-selectionsort.java
```

To Execute :

```bash
javac -cp algorithms.jar: main.java
java -cp algorithms.jar: main
```

