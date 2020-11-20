package Askisi2;

public class Ask2TestLList {

	public static void main(String[] args) {
		
		System.out.println("Main thread starting.");
		
		int num = 21;
		// Create a REFERENCE array of threads
		MyThread[] mt = new MyThread[num];
		
		// Create the threads
		for (int i = 0; i < num; i++) { 
			mt[i] = new MyThread("Child#"+i);		
		}
		
		try {
			for (int i = 0; i < num; i++) {
				mt[i].join();
				//MyThread.pw.println("Child#" + i + " joined.");
			}
		}
		catch (InterruptedException exc) {
			exc.printStackTrace();
		}
		
	}

}
