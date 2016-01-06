package CS401;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import CS401.UI.*;

public class CouponSystem {
	private MainWindow window;
	private CouponList userDataBase;
	private SortedCPL sortlist;

	// constructor
	public CouponSystem(MainWindow window) throws Exception {
		userDataBase = new CouponList();
		this.window = window;

	}

	// read data from given file and create unsorted list
	public CouponList loadFile(String filename) throws Exception {
		// read data file
		userDataBase.clear();
		Scanner readFile;
		
		try {
			readFile = new Scanner(new FileReader(filename));
			while (readFile.hasNext()) {
				String line = readFile.nextLine();

				try {
					Coupon c = new Coupon(line);
					userDataBase.insert(c);
				} catch (Exception e) {
					e.printStackTrace();
					readFile.close();
					throw new Exception("File is not valid: "+line);
				}
			}
		} catch (FileNotFoundException e) {
			throw new Exception("File is not found. ");
		}
		
		readFile.close();
		return userDataBase;
		// create unsorted list

	}

	// create a new coupon by given string
	public void createCoupon(String astr) throws Exception {
		Coupon newCoupon = new Coupon(astr);
		userDataBase.insert(newCoupon);
		writeFile("coupon list.txt");
	}

	// search unsorted list by given string, display all the results in GUI
	public void search(String name) throws Exception {

		window.clearRows();
		LLNode<Coupon> node = userDataBase.search(name).getHead();
		if (node == null) {
			MainWindow.showMessage(null, "Coupon is not found. ");

		} else {
			while (node != null) {
				window.addRow(node.getElement().showList().split(","));
				node = node.getNext();
			}
			MainWindow.showMessage(null, userDataBase.getmsg());

		}

	}

	// display list in GUI
	public void display() throws Exception {
		listCoupon(userDataBase);
	}

	// create sorted list by given key
	public void sort(String sortBy) throws Exception {
		sortlist = new SortedCPL(userDataBase, sortBy);

		// show sorted list in UI
		listCoupon(sortlist);
	}

	
	// display list in GUI by given list
	public void listCoupon(LList<Coupon> list) throws Exception {
		window.clearRows();
		LLNode<Coupon> curr = list.getHead();
		while (curr != null) {
			window.addRow(curr.getElement().showList().split(","));
			curr = curr.getNext();
		}
	}

	// write file
	public void writeFile(String fileName) throws Exception {

		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println(userDataBase.toString());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			throw new Exception("The Character Encoding is not supported. ");
		}

	}

}
