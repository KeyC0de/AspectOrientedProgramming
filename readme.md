<h1 align="center">
	<a href="https://github.com/KeyC0de/AspectOrientedProgramming_Java">Aspect Oriented Programming</a>
</h1>
<hr>


Aspect Oriented Programming (AOP) is a programming paradigm that aims to increase modularity by allowing the separation of cross-cutting concerns. It does so by adding additional behavior to an existing code without modifying the code itself, instead separately specifying which code is modified via a 'pointcut' specification, such as "log all function calls when function's name begins with 'set'". This allows behaviors that are not central to the business logic (such as logging) to be added to a program without cluttering the code, core to the functionality.

- The AspectConfiguration specifies which aspect applies to which method of an object
- Each aspect is essentially a method (or a separate class containing a group of those aspect-methods) written in an aspect-oriented language (or annotation, or through an idiom in an existing language).
- Aspects are wrapped 'before', 'after' or 'around' methods. For example we can specify in the aspect configuration that an aspect will get called before or after a certain method in an object. This means that we don't make changes to our existing codebase, but only on the Aspect configuration.

Steps:

1. Identify the cross-cutting concerns and write them as aspects
2. Configure where the aspects apply - which methods and when they trigger

No matter all this fancy lingo. This is basically just another general purpose design pattern, also known as "Execute Around Pointer" in C++. Its intent is to help with code modularization and loose coupling. To do that in C++ we provide a smart pointer object, typically as a nested class in the class we want to "execute around" which transparently executes actions before or after each function call on the external class object, given that the actions performed are the same for all functions.

I have developed this demo on my Windows 8.1 x86_64 computer, using IntelliJ IDEA 2018.1 for Java and Microsoft Visual Studio 2017 for C++. The C++ part lies in the cpp folder.

For the Java aspectJ implementation you can find in-depth details in java_aspectj.pdf in Greek (sorry but you won't really be missing much).


# Contribute

Please submit any bugs you find through GitHub repository 'Issues' page with details describing how to replicate the problem. If you liked it or you learned something new give it a star, clone it, contribute to it whatever. Enjoy.


# License

Distributed under the GNU GPL V3 License. See "GNU GPL license.txt" for more information.


# Contact

email: *nik.lazkey@gmail.com*</br>
website: *www.keyc0de.net*


# Acknowledgements

AOP kiczales-ECOOP1997
