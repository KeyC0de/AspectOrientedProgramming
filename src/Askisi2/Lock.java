package Askisi2;

abstract class Lock {
	boolean isLocked = false;
	Thread lockedBy  = null;

	public synchronized void lock() throws InterruptedException {
		Thread callingThread = Thread.currentThread();

		while (isLocked && lockedBy != callingThread) {
			wait();
		}
		isLocked = true;
		lockedBy = callingThread;
	}

	public synchronized void unlock() {
		if (Thread.currentThread() == this.lockedBy) {

			isLocked = false;
			notify();
		}
	}
	
	abstract void toDo();

}
