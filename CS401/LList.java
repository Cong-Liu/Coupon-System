package CS401;

public class LList<T> implements List<T> {

	// Data members
	protected LLNode<T> head, // Reference to the beginning of the list
			cursor; // Reference to current cursor position


	LList() // Default constructor: Creates an empty list
	{
		head = null;
		cursor = null;
	}

	// get the head node
	public LLNode<T> getHead(){
		return head;
	}
	
	
	public void insertBefore(T newElement) // Insert before cursor
	{
		if (isEmpty()) {
			head.setElement(newElement);
			cursor = head;
		} else {
			LLNode<T> prev = head;
			LLNode<T> newNode = new LLNode<T>(newElement, cursor);
			if (cursor == head) {
				head = newNode;
				cursor = head;
			} else {
				while (prev.getNext() != cursor) {
					prev = prev.getNext();
				}

				prev.setNext(newNode);
				cursor = newNode;
			}
		}
	}

	@Override
	public void insert(T newElement) {
		// Inserts newElement after the cursor. If the list is empty, then
		// newElement is inserted as the first (and only) element in the list.
		// In either case, moves the cursor to newElement.

		if (!isEmpty()) {
			LLNode<T> newNode = new LLNode<T>(newElement, cursor.getNext());
			cursor.setNext(newNode);
			cursor = newNode;
		} else {
			LLNode<T> newNode = new LLNode<T>(newElement, null);
			head = newNode;
			cursor = newNode;
		}
	}

	@Override
	public void remove() {
		// Removes the element marked by the cursor from a list. Moves the
		// cursor to the next element in the list. Assumes that the first list
		// element "follows" the last list element.
		if (isEmpty()) {
			System.out.println("The list is empty.");
		} else {
			LLNode<T> prev = head;
			if (cursor == head) {
				head = head.getNext();
				cursor = head;
			} else {
				while (prev.getNext() != cursor) {
					prev = prev.getNext();
				}
				if (cursor.getNext() == null) {
					prev.setNext(null);
					cursor = head;
				} else {
					prev.setNext(cursor.getNext());
					cursor = cursor.getNext();
				}
			}
		}
	}

	public void replace(T newElement) {
		// Replaces the element marked by the cursor with newElement and
		// leaves the cursor at newElement.
		LLNode<T> prev = head;
		LLNode<T> newNode = new LLNode<T>(newElement, cursor.getNext());
		if (cursor == head) {
			newNode.setNext(cursor.getNext());
			head = newNode;
			cursor = newNode;
		} else {
			while (prev.getNext() != cursor) {
				prev = prev.getNext();
			}
			prev.setNext(newNode);
			cursor = newNode;
		}
	}

	public void clear() {
		// Removes all elements in a list
		head = null;
		cursor = null;
	}

	public boolean isEmpty() {
		// Returns true if list is empty, else returns false
		if (head == null & cursor == null)
			return true;
		else
			return false;
	}

	public boolean isFull() {
		// Returns true if list is full, else returns false
		// Always returns false because Java automatically
		// generates an OutOfMemory error otherwise.
		return false;
	}

	public boolean gotoBeginning() {
		// If list not empty, moves cursor to beginning of list & returns true,
		// else returns false
		if (!isEmpty()) {
			cursor = head;
			return true;
		}
		return false;
	}

	public boolean gotoEnd() {
		// If list not empty, moves cursor to end of list & returns true,else
		// returns false
		cursor = head;
		if (!isEmpty()) {
			while (cursor.getNext() != null) {
				cursor = cursor.getNext();
			}
			return true;
		}
		return false;
	}

	public boolean gotoNext() {
		// If cursor not at end of list, moves cursor to next element in list &
		// returns true
		// else returns false
		if (cursor.getNext() != null) {
			cursor = cursor.getNext();
			return true;
		} else
			return false;
	}

	public boolean gotoPrior() {
		// If cursor not at beginning of list, moves cursor to preceding element
		// in list & returns true, else returns false
		if (head == cursor)
			return false;
		else {
			LLNode<T> prev = head;
			while (prev.getNext() != cursor) {
				prev = prev.getNext();
			}
			cursor = prev;
			return true;
		}
	}

	public LLNode<T> getCursor() {
		// Returns the Node at the cursor
		return cursor;
	}

	public void showStructure() {
		// Outputs the elements in a list. If the list is empty, outputs
		// "Empty list". This operation is intended for testing and
		// debugging purposes only.
		LLNode<T> temp;
		if (isEmpty()) {
			System.out.println("Empty List.");
		} else {
			System.out.println("Cursor is " + cursor.getElement());
			for (temp = head; temp != null; temp = temp.getNext()) {
				System.out.print(temp.getElement() + " ");
			}
		}
	}
	
	public String toString() {
		if (gotoBeginning()) {
			String str = "";
			while (cursor != null) {
				T temp = cursor.getElement();
				str += temp.toString()+ "\n";
				cursor = cursor.getNext();
			}
			return str;
		}
		return "Empty List. ";
	}
}
