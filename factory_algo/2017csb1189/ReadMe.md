## To Execute

There is an existing .jar file in the folder that has all the class files
So the user needs to compile only his main file
Go to the folder having ur main.java

The commands are:

```bash
javac -cp algorithms.jar: main.java
java -cp algorithms.jar: main
```

The code is a demonstration of abstract factory design pattern in Java.
The functions have been defined for integer arrays only

It functions as follows:
- There are 8 concrete classes for 5 sorting algorithms and 3 mathematical algorithms implemented in different files. They are the extensions of 2 abstract classes.
- The client does not have access to these classes rather it accesses a factory class using a integer input telling what kind of sort/math_operation he/she desires and the factory class returns an object of the concrete class as per the user demand. The factory decides what concrete class has to be called. The client has access to factory and abstract classes only.
- So if any of the concrete class experiences a change there is no change required in the code of the client as the factory takes care of the middle talk.

1) bubblesort.java , insertionsort.java , selectionsort.java , quicksort.java , mergesort.java are 5 implementations of SortingAlgorithm abstract class

2) hcf.java , lcm.java , power.java are 3 implementations of MathsAlgorithm abstract class

3) GetAlgorithmFactory.java is a factory class that acts as a middleman between the client code and the concrete classes. The client code creates an object of this factory and passes strings as arguments to its functions which are used by the factory to create objects of the concrete classes and return to the user.

User can create factory object through this:
GetAlgorithmFactory algorithmFactory = new GetAlgorithmFactory();

Sorting Algorithm can be accessed using:
SortingAlgorithm a = algorithmFactory.getSortAlgo("...");

Math Algorithm can be accessed using:
MathAlgorithm a = algorithmFactory.getMathAlgo("...");
