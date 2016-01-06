package CS401;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Coupon {

	private String name;
	private double discount;
	private int expDate;
	private boolean status;
	private String provider;
	private double price;

	// default constructor

	public Coupon() {
		name = "Undefined";
		expDate = -1;
		status = false;
		discount = -1;
		provider = "default";
		price = 0;
	}

	// non-default constructor
	public Coupon(String name, double price, double discount, int date,
			String provider, boolean b) throws Exception {
		if (name.length() > 50) {
			throw new Exception("Name is not valid");
		}
		this.name = name;
		if (discount > 1 || discount < 0) {
			throw new Exception("Discount is out of range");
		}
		this.price = price;
		this.discount = discount;
		if (date >= 365) {
			throw new Exception("Expire date is not valid");
		}
		expDate = date;
		if (provider.length() > 20) {
			throw new Exception("Provider is not valid");
		}
		this.provider = provider;
		status = b;
	}

	// non-default constructor, used for reading and writing to the file
	public Coupon(String line) throws Exception {
		String[] str = line.split(",");
		if (str.length != 6) {
			throw new Exception("Can't read the file");
		}

		if (str[0].length() > 50) {
			throw new Exception("Name is not valid");
		}
		name = str[0];

		price = Double.parseDouble(str[1]);

		double dis = Double.parseDouble(str[2]);
		if (dis > 1 || dis < 0) {
			throw new Exception("Discount is out of range");
		}
		discount = dis;

		if (Integer.parseInt(str[3]) >= 365) {
			throw new Exception("Expire date is not valid");
		}
		expDate = Integer.parseInt(str[3]);

		if (str[4].length() > 20) {
			throw new Exception("Provider is not valid");
		}
		provider = str[4];
		status = Boolean.parseBoolean(str[5]);
	}

	// ---------- getters and setters --------

	public void setName(String name) throws Exception {
		if (name.length() > 50) {
			throw new Exception("Name is not valid");
		}
		this.name = name;
	}

	public void setPrice(double p) throws Exception {
		if (p < 0)
			throw new Exception("Price is not valid");
		price = p;
	}

	public void setExpDate(int date) throws Exception {
		if (date >= 365) {
			throw new Exception("Expire date is not valid");
		}
		expDate = date;
	}

	public void setStatus(boolean b) {
		status = b;
	}

	public void setDiscount(double dis) throws Exception {
		if (dis > 1 || dis < 0) {
			throw new Exception("Discount is out of range");
		}
		discount = dis;
	}

	public void setProvider(String p) throws Exception {
		if (p.length() > 20) {
			throw new Exception("Provider is not valid");
		}
		provider = p;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getDiscount() {
		return discount;
	}

	public int getDate() {
		return expDate;
	}

	public boolean getStatus() {
		return status;
	}

	public String getProvider() {
		return provider;
	}

	// ------------ add comparable methods -----------------

	public int compareName(Coupon cp) {
		return name.compareToIgnoreCase(cp.getName());
	}

	public int compareDiscount(Coupon cp) {
		if (discount > cp.getDiscount())
			return 1;
		else if (discount < cp.getDiscount())
			return -1;
		else
			return 0;
	}

	public int compareExpDate(Coupon cp) {
		if (expDate > cp.getDate())
			return 1;
		else if (expDate < cp.getDate())
			return -1;
		else
			return 0;
	}

	public int comparePrice(Coupon cp) {
		if (price > cp.getPrice())
			return 1;
		else if (price > cp.getPrice())
			return -1;
		else
			return 0;
	}

	public int compareStatus(Coupon cp) {
		if (status == cp.getStatus())
			return 1;
		else
			return -1;
	}

	public int compareProvider(Coupon cp) {
		return provider.compareTo(cp.getProvider());
	}

	// ------- total comparable method-----

	public int compareTo(Coupon cp, String key) throws Exception {
		// String mode = key;
		switch (key) {
		case ("Name"):
			return compareName(cp);

		case ("Discount rate"):
			return compareDiscount(cp);

		case ("Expiration period"):
			return compareExpDate(cp);

		case ("Status"):
			return compareStatus(cp);

		case ("Provider"):
			return compareProvider(cp);

		case ("Price"):
			return comparePrice(cp);

		default:
			throw new Exception("Sort mode is not valid");
		}
	}
	
	

	// format coupon data, for displaying on table
	public String showList() {
		NumberFormat formatter = new DecimalFormat("#0.00%");
		String discount = formatter.format(this.discount);
		String statusType = "";
		if (this.status)
			statusType = "Used";
		else
			statusType = "Unused";
		String astr = name + "," + price + "," + discount + "," + expDate + ","
				+ provider + "," + statusType + ",";
		return astr;
	}

	
	// format coupon data, for reading and writing in data file. 
	public String toString() {
		String astr = name + "," + price + "," + discount + "," + expDate + ","
				+ provider + "," + status + ",";
		return astr;
	}

}
