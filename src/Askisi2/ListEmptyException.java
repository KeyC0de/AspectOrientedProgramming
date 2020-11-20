package Askisi2;

@SuppressWarnings(value = "serial")
public class ListEmptyException extends Exception {
	public String toString() {
		return "List is empty!";
	}
}