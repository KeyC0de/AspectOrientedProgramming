
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Aspect utilizes an efficient & reliable Readers-Writers Lock to acquire and insert Objects 
// in a doubly Linked List and thus making it Thread-safe.

// perthis : creates one aspect object for every object that is the executing object (ie. this) 
// intercepted by the llistThreadStart pointcut
public aspect NikLList_aspect_Ts perthis(llistThreadStart()) {
	
	// Creating the necessary Reader-Writer Double-Lock object
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	// Give the locks to the reader and the writer 
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();
	
	String reference;
	
	// The pointcuts
	pointcut llistThreadStart() 		: execution (public * NikLList.*(..));
	pointcut writerPrepend(Object obj) 	: execution (public void NikLList.prepend(Object)) && args(obj);
	pointcut writerPop() 			: execution (public Object NikLList.pop());
	pointcut readerHead() 			: execution (public Object NikLList.head());
	
	// Checks the thread attempting to enter prepend() 
	// If it doesn't succeed its timeout value will be decreased by one. If this occurs timenout number 
	// of times the thread will halt
	before(Object obj) : writerPrepend(obj) {
		// stores the name of the thread right before it enters the prepend() method
		reference = Thread.currentThread().getName();
	}
	
	// The advice runs "around" NikLList.prepend() method
	void around(Object obj) : writerPrepend(obj) {
	
		// A thread fails to enter a critical section if it is preempted / interrupted by another thread.
		writeLock.lock();
		
		MyThread current = (MyThread) Thread.currentThread();
		
		if (!MyThread.currentThread().getName().equals(reference))
			current.decTimeout(); // Thread preempted before entering critical section
			if (current.getTimeout() <= 0)
				current.markForTermination();
		
		try {
			proceed(obj); // Run NikLList.prepend() method
		}
		finally { writeLock.unlock(); }
	}
	
	Object around() : writerPop() {
		writeLock.lock();
		
		Object returnVal;
		try {
			returnVal = proceed(); // Run Askisi2.NikLList.pop() method
			
			// Forward the returnVal to MyThread.java
			return returnVal;	
		}
		finally { writeLock.unlock(); }
	}
	
	Object around()	: readerHead() {
		readLock.lock(); // lock access
		
		Object returnVal;
		try {
			// Execute the method() in the .java file and receive its return value
			returnVal = proceed(); // Run Askisi2.NikLList.head() method
			
			return returnVal;
		}
		/** Always releasing resources, the lock in this case, with a finally block */
		finally { readLock.unlock(); }
	}

}