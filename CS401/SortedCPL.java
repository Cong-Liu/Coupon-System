package CS401;


//------- provide a sorted linked list-----



public class SortedCPL extends LList<Coupon> {

	private CouponList couponData;
	private String sortMode;

	public SortedCPL() {
		super();
		couponData = new CouponList();
		sortMode = "";
	}

	
	//------non-default constructor, insertion sort in constructor------
	public SortedCPL(CouponList data, String sortBy) throws Exception {
		couponData = data;
		sortMode = sortBy;

		LLNode<Coupon> currData = couponData.getHead();
		while (currData != null) {
			insert(currData, sortMode);
			currData = currData.getNext();
		}
	}

	// insert method, call different comparable method by given key word,
	public void insert(LLNode<Coupon> newNode, String key) throws Exception {
		cursor = head;
		Coupon elem = newNode.getElement();
		LLNode<Coupon> temp = new LLNode<Coupon>(elem, null);
		// empty
		if (isEmpty()) {
			head = temp;
		}
		// one node
		else if (head.getNext() == null) {
			if (head.getElement().compareTo(elem, key) <= 0) {
				head.setNext(temp);
			} else {
				temp.setNext(head);
				head = temp;
			}
		}
		// more than two nodes
		else {
			while (cursor != null) {
				// check if cursor node < new node
				if (cursor.getElement().compareTo(elem, key) <= 0) {
					if (cursor.getNext() == null) {
						cursor.setNext(temp);
						break;
					}
					// if cursor.next > new node, insert newNode after cursor
					else if (cursor.getNext().getElement().compareTo(elem, key) > 0) {
						temp.setNext(cursor.getNext());
						cursor.setNext(temp);
						break;
					} else {
						cursor = cursor.getNext(); // return to while loop
					}
				} else {
					temp.setNext(cursor);
					head = temp;
					break;
				}

			}
		}
	}

	
	public CouponList getSortedList() {
		return couponData;
	}
}
