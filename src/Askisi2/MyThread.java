package Askisi2;


import java.io.PrintWriter;

/** 
 *  A ReadWriteLock maintains a pair of associated locks,
 *  one for read-only operations and one for writing.
 *  The read lock may be held simultaneously by multiple reader threads,
 *  so long as there are no writers. The write lock is exclusive.
 *  
 *  Reader threads can read shared data simultaneously. A read operation does not block other 
 *  read operations. But write operation is exclusive. This means all readers and other 
 *  writers are blocked when a writer thread holds the lock for modifing shared data.
 *  
 *  If multiple threads are accessing an object for reading data, it does not 
 *  make sense to use a synchronized block or any other mutually exclusive locks. 
 *  The ReadWriteLock offers two main methods Lock readLock() and Lock writeLock(). As the 
 *  name suggests, the readLock() method is to acquire read-Lock and writeLock is called 
 *  for acquiring the write-Lock.
 */

class MyThread extends Thread {
	static NikLList myList = new NikLList(); 
	PrintWriter pw = new PrintWriter(System.out, true); // works with char streams. generally more efficient than System.out
	
	private byte timeout;
	private boolean stopped;
	
	MyThread(String name) {
		setName(name);
		timeout = 5;
		stopped = false;
		start(); // begin execution of the thread
	}

	// Entry point of thread
	public void run() {
		int n = 1;

		while (!stopped && n > 0) {
			// Does some operations
			myList.prepend(5);
			myList.head();
			myList.prepend(10);
			myList.prepend(34);
			myList.head();
			myList.prepend("Nick");
			myList.head();
			myList.prepend(5.78);
			myList.prepend(Boolean.TRUE);
			myList.pop();
			myList.prepend("543.9603");
			myList.head();
			
			--n;
		}
		// myList.printList();

		synchronized (this) {
			if (this.stopped) pw.println(MyThread.currentThread().getName() + " quits.");
		}
	}
	
	// Methods below called by the aspects inside Reader-Writer synchronized context
	void decTimeout() { --timeout; /*pw.println("dec'd");*/ }
	byte getTimeout() { return timeout; }
	void markForTermination() { this.stopped = true; }
	
}
