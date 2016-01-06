package CS401;

public class CouponList extends LList<Coupon> {
	private String msg = ""; // used for display the count of search from the existing list
	
	// search coupon by name, return a coupon list with all the coupon whose name contain search key
	public CouponList search(String key){
		msg = "Coupon location: ";
		int count = 0;
		CouponList searchList = new CouponList();
		LLNode<Coupon> searchNode = head;
		while(searchNode!= null){
			String name = searchNode.getElement().getName().toLowerCase();
			int test = name.indexOf(key.toLowerCase());
			if(test != -1){
				searchList.insert(searchNode.getElement());
				msg += (count+" ");
			}searchNode = searchNode.getNext();
			count++;
		}return searchList;
	}
	
	// return the location of found coupon in unsorted list
	public String getmsg(){
		return msg;
	}
}
