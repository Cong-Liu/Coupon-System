package CS401;

public class LLNode<T> {

	// Data members
    private T element;         // List element
    private LLNode<T> next;         // Reference to the next element
    
    // Constructor
    public LLNode ( T elem, LLNode<T> nextPtr )
        // Creates a list node containing element elem and next pointer
        // nextPtr.
    {
    	element = elem;
    	next = nextPtr;
    }
    
    // Class Methods used by client class
    public LLNode<T> getNext( )                    // Return reference to next element
    {
    	return next;
    }
    
	public LLNode<T> setNext( LLNode<T> nextVal )  // Set reference to next element
    {                                       //  & return that reference
    	next = nextVal;
    	return next;
    }
    
    public T getElement( )             // Return the element in the current node
    {
    	return element;
    }
    
    void setElement(T newElem)         // Set current element to newElem 
    {                                 
    	element = newElem;
    }
}
