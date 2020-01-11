# BigInteger

Background
Integer.MAX_VALUE is the maximum value of a Java int: 2147483647. If you want to work with even bigger integers, you have the option of using the type long, which has the maximum value of Long.MAX_VALUE = 9223372036854775807.

But what if this is not enough? What if you are working on something like an astronomy application, and need to keep track of things such as number of stars in the universe? This is of the order of 1023, larger than the maximum long value. For situations like this, you need to be able to work with an integer type that can hold arbitrarily large or small positive and negative values, with any number of digits. There is no built-in type in the language for this, so you need to craft your own. In this assignment, you will do exactly this, by implementing a class called BigInteger, with a representative small set of operations.

The trick is to store an integer as a linked list of digits. For example, the integer 754 will be stored as:

   4 -> 5 -> 7
Why are the digits stored backward? It's because computations such as adding or multiplying big integers are easier to do if the linked list stores digits in ascending order of positional value. So the least significant digit is in the first node of the linked list, and the most significant digit is in the last node.
This is a simple linked list, NOT circular, with a front pointer. The sign (positive or negative), is stored separately in a boolean field in the program.

Also, there can never be zeros at the end of the list. Such zeros will be insignificant. So, for instance, 00754 will be stored as:

   4 -> 5 -> 7 (NOT 4 -> 5 -> 7 -> 0 -> 0)
Consequently, the value 0 will be stored as an EMPTY LINKED LIST. The number of digits is 0, and it is not negative.

Implementation and Point Assignment
At the bottom of the Autolab assignment page, under Handouts you will see a biginteger_project.zip file, which is an Eclipse project file. Download it to your computer. DO NOT unzip it.

Instead, follow the instructions on the Eclipse page under the section "Importing a Zipped Project into Eclipse" to get the entire project into your Eclipse workspace.

You will see a project called BigInteger with classes BigInteger, DigitNode, and BigTest in package bigint. The DigitNode class implements the linked list node that will hold a digit of a big integer linked list.

You need to fill in the implementation of the following methods in the BigInteger class:
Method	Points
parse	15
add	30
multiply	25
Note: When parsing an input string as an integer, you can use the Character.isDigit(char) method to tell if a character is a digit.

Make sure to read the comments that precede classes, fields, and methods for code-specific details that do not appear here.

Observe the following rules while working on BigInteger.java:

You may NOT add/delete any import statements.
You may NOT add any classes.
You may NOT add/delete any fields.
You may NOT modify the headers of any of the given methods.
You may NOT delete any methods.
You MAY add helper methods if needed, as long as you make them private.
Also, you may NOT make any changes to the DigitNode class (you will only be submitting BigInteger.java). When we test your submission, we will use the exact same version of DigitNode that we shipped to you.
WARNING!!!

If your linked list stores digits with most significant digit first, you will get ZERO for the corresponding test case, even if the result is mathematically correct. NO EXCEPTIONS.
If your linked list stores insignificant zeros, you will get ZERO for the corresponding test case, even if the result is mathematically correct. NO EXCEPTIONS.
If you use an array anywhere in your program , you will get ZERO. NO EXCEPTIONS.
If you convert the input to a Java numeric type value (such as int or long) and then do arithmetic on it, instead of working with individual digits stored in a linked list, you will get ZERO. NO EXCEPTIONS.

Running/Testing
Use the class BigTest to test your implementation. Carefully read the code in the file to get a good idea of how the BigInteger methods are used.

Here's a sample run of BigTest:

(p)arse, (a)dd, (m)ultiply, or (q)uit? => p
Enter integer => 125
Value = 125

(p)arse, (a)dd, (m)ultiply, or (q)uit? => p
Enter integer => -126
Value = -126

(p)arse, (a)dd, (m)ultiply, or (q)uit? => p
Enter integer => +1
Value = 1

(p)arse, (a)dd, (m)ultiply, or (q)uit? => p
Enter integer => 005
Value = 5

(p)arse, (a)dd, (m)ultiply, or (q)uit? => p
Enter integer => 123xy56
Incorrect Format

(p)arse, (a)dd, (m)ultiply, or (q)uit? => a
Enter first integer => 12
Enter second integer => -13
Sum: -1

(p)arse, (a)dd, (m)ultiply, or (q)uit? => a
Enter first integer => 16756726
Enter second integer => 0
Sum: 16756726

(p)arse, (a)dd, (m)ultiply, or (q)uit? => m
Enter first integer => 12
Enter second integer => 200
Product: 2400

(p)arse, (a)dd, (m)ultiply, or (q)uit? => m
Enter first integer => 178
Enter second integer => -156
Product: -27768

(p)arse, (a)dd, (m)ultiply, or (q)uit? => m
Enter first integer => -16
Enter second integer => -05
Product: 80

(p)arse, (a)dd, (m)ultiply, or (q)uit? => q
NOTE: These are just sample tests, you will need to make several of your own for each method to make sure your implementation works correctly. Also, read the Grading Process section to understand what we look at to assess whether your methods run correctly or not. (It's NOT the printed output.)


Submitting
Submit your BigInteger.java source file in Autolab.

Refer to the instructions in the Eclipse page, under the section The Eclipse Workspace to know how to locate BigInteger.java on your computer for uploading.


