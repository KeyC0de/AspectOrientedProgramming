package Askisi2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 
 *  A doubly linked list
 *  headNode --> next --> next --> next --> ... --> tailNode.next --> null
 *  tailNode's next pointer should be null
 *  null <-- headNode.prev <-- prev <-- prev <-- ... <-- tailNode.prev
 *  headNode's prev pointer should be null
 */
public class ThreadSafeNLList {
	private Node headNode, tailNode;
	private int size;
		
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
	
	public ThreadSafeNLList () { 
		headNode = null;
		tailNode = null;
		size = 0;
	}
	
	// Insert element at the head of the linked list
	public void prepend(Object ob) {
		// Enables write lock. Mutually exlusive access
		writeLock.lock(); 
		try {
			Node currentNode = new Node(ob);
			size++;
			
			// If this is the first node (tailNode) inserted then it should point to null
			// Otherwise it should point to the previous inserted node, ie. the previoud headNode
			if (size > 1) {
				headNode.prev = currentNode;
				currentNode.next = headNode;
			}
			else
				tailNode = currentNode;
			headNode = currentNode;
		}
		/**
		 * When guarding a critical section with a Lock, and the critical section may throw exceptions, 
		 * it is important to call the unlock() method from inside a finally-clause. Doing so makes sure 
		 * that the Lock is unlocked so other threads can lock it.
		 */
		finally { writeLock.unlock(); }
	}
	
	// Removes from the list, erases and returns the headNode
	public Object pop() {
		writeLock.lock();
		try {
			Node temp = headNode;
			headNode.next.prev = null;
			headNode = headNode.next;
			
			return temp.data;
		}	
		finally { writeLock.unlock(); }
	}
	
	// Returns the headNode's datum
	public Object head() {
		readLock.lock(); // lock suitable for reader threads
		try {
			return headNode.data;
		}		
		finally { readLock.unlock(); }
	}
	
	public Object tail() { return tailNode.data; }
	public int size() {	return size; }
	
	// Print the values of the list's nodes, from head to tail
	public void printList() {
		try {
			if (size == 0) throw new ListEmptyException();
			
		    Node node = headNode;
		    
		    while( node != null ) {
		    	System.out.print("[" + node.data + "] ");
		    	node = node.next;
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private class Node {
		Object data;
		@SuppressWarnings(value = "unused")
		Node prev;
		Node next;
		
		Node(Object val) {
			this.data = val;
			this.next = null;
			this.prev = null;
		}	
	}

}
