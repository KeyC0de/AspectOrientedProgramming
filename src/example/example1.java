import java.util.Map;


public class example1 {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		example1 aopDemo = new example1();
		
		aopDemo.methodOne(5);
		aopDemo.methodOne(3, "Hello");
		methodTwo("Goodbye");
		
		Map<String, String> sys;
		sys = System.getenv();
		System.out.println(sys);
		
	}
	
	public static void methodOne(int var) {
		System.out.println("MethodOne called with " + var);
	}
	
	public void methodOne(int var, String str) {
		System.out.println("MethodOne called with " + var + " and " + str);
	}
	
	public static void methodTwo(String str) {
		System.out.println("MethodTwo called with " + str);
	}
	
}
