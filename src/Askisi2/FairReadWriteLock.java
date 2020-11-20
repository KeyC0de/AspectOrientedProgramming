package Askisi2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FairReadWriteLock{

	int readAcquires, readReleases;
	boolean writer;
	Lock lock;
	Condition condition;
	ReadLock readLock;
	WriteLock writeLock;
	
	public FairReadWriteLock()
	{
		readAcquires = 0;
		readReleases = 0;
		writer = false;
		lock = new ReentrantLock();
		condition = lock.newCondition();
		readLock = new ReadLock();
		writeLock = new WriteLock();
	}
	
	public ReadLock readLock() {
		return readLock;
	}

	public WriteLock writeLock() {
		return writeLock;
	}
	
	public class ReadLock
	{
		public void lock() {
			lock.lock();
			try 
			{
				while(writer)
					condition.await();
				readAcquires++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally{
				lock.unlock();
			}
		}

		public void unlock() {
			lock.lock();
			try
			{
				readReleases++;
				if (readAcquires==readReleases)
					condition.signalAll();
			}
			finally{
				lock.unlock();
			}
		}
		
	}
	
	public class WriteLock
	{
		public void lock() {
			lock.lock();
			try
			{
				while(writer)
					condition.await();
				writer=true;
				while(readAcquires!=readReleases)
					condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally{
				lock.unlock();
			}
		}
		
		public void unlock() {
			lock.lock();
			try
			{
				writer = false;
				condition.signalAll();
			}
			finally{
				lock.unlock();
			}
		}
		
	}
}