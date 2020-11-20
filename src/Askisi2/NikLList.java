package Askisi2;

/** 
 *  A doubly linked list
 *  headNode --> prev --> prev --> prev --> ... --> tailNode.prev --> null
 *  tailNode's prev pointer should be null
 *  null <-- headNode.next <-- next <-- next <-- ... <-- tailNode.next
 *  headNode's next pointer should be null
 */
public class NikLList {
	private Node headNode, tailNode;
	private int size;
			
	public NikLList () { 
		headNode = null;
		tailNode = null;
		size = 0;
	}
	
	// Insert element at the head of the linked list
	public void prepend(Object ob) {
		Node currentNode = new Node(ob);
		size++;
		
		// If this is the first node (tailNode) inserted then it should point to null
		// Otherwise it should point to the previous inserted node, ie. the previoud headNode
		if (size > 1) {
			headNode.next = currentNode;
			currentNode.prev = headNode;
		}
		else // it's the 1st node
			tailNode = currentNode;
		
		headNode = currentNode;
	}
	
	// Removes from the list, erases and returns the headNode
	public Object pop() {
		Node temp = headNode;
		headNode.prev.next = null;
		headNode = headNode.prev;
		
		return temp.data;
	}
	
	// Returns the headNode's datum
	public Object head() {
		return headNode.data;
	}
	
	public Object tail() { return tailNode.data; }
	public int size() {	return size; }
	
	// Print the values of the list's nodes, from head to tail
	public void printList() {
		try {
			if (size == 0)
				throw new ArrayIndexOutOfBoundsException();
			
		    Node node = headNode;
		    
		    while( node != null ) {
		    	System.out.print("[" + node.data + "] ");
		    	node = node.prev;
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
		@SuppressWarnings(value = "unused")
		Node next;
		
		Node(Object val) {
			this.data = val;
			this.next = null;
			this.prev = null;
		}	
	}

}
