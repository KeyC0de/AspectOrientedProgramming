public aspect AspectDemo {
	
	// Pointcut for methodOne. 
	// Overloaded functions are pointcutted only the first time
	pointcut f1(): call(* example.example1.method*(..)); // or the following 
		// execution(* example.example1.method*(..));
	
	before(): f1() {
		System.out.println("before methodOne ...");
	}
	
	after(): f1() {
		System.out.println("after methodOne ...");
	}
	
}
