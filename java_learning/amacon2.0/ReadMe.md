 # AMACON 2.0
 
 ## Overview 
 
 In Version 2.0, you have to extend the Amacon.com platform to make it more user-friendly and to ensure that the system is also foolproof. These new set of requirements from Amacon.com are as following:

- Version 2.0 platform should welcome recurring customers. It should be able to identify each customer by a unique username (case sensitive) and save the customer’s cart state after each session. Platform should allow the returning customer to recall their cart the next time they log in Amacon.com.
- Version 1.0 (Question 1) platform is extremely impractical for the admins as they have to re-initialise the database before every admin sessions. Version 2.0 platform should maintain the last modified state of its database across all admin and customer sessions.
- Version 2.0 platform should be robust to corner cases and should never crash. JUnit based unit testing is a good way of testing your platform effectively and comprehensively. You have to design a JUnit Test Suite for the Amacon system that contains different JUnit test cases.
- for more info [Assignment3](https://github.com/cynicphoenix/Pragmatics-Assignments/blob/master/java_learning/Assignment_3.docx?raw=true) 
 
 ## Directories

```bash
|-java_learning
    |-amacon2.0
      |-category.java
      |-customer.java
      |-database.java
      |-main.java
      |-subCategory.java
      |-products.java
      |-deleteExceptions.java
      |-insertExceptions.java
      |-outOfStockExceptions.java
      |-insufficientFundException.java
      |-productAtSamePathException.java
      |-searchException.java
      |-test.java
      |-testSuite.java
      |-runner.java
 ```
 
 ## To Execute
 
To run JUnit Suite

```bash
javac -cp junit-4.13-beta-2.jar: test.java testSuit.java runner.java
java -cp .:junit-4.13-beta-2.jar:hamcrest-core-1.3.jar: runner
```

To run code

```bash
javac customer.java product.java database.java category.java subCategory.java insertException.java insufficientFundException.java outOfStockException.java productAtSamePathException.java searchException.java main.java
java main
```


