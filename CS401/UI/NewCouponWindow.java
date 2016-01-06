package CS401.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import CS401.CouponSystem;

public class NewCouponWindow {

	private JFrame frame;
	private DefaultTableModel model;

	// UI elements
	private JButton btnEnter;
	private JTextField[] txtSearch = new JTextField[5];
	private JComboBox<String> statusType;

	public NewCouponWindow() {
		// create a window frame
		frame = new JFrame("Add new coupon");
		frame.setSize(650, 400);

		frame.setLocationByPlatform(true);
		frame.setLayout(new GridBagLayout()); // create grid bag layout
		initComponents();
	}

	public void show() {
		frame.setVisible(true);
	}

	public void close() {
		frame.setVisible(false);
	}

	// add a row to data table
	public void addRow(Object[] columns) throws Exception {
		if (columns.length != model.getColumnCount())
			throw new Exception("Column length error.");
		model.addRow(columns);
	}

	public void initComponents() {
		GridBagConstraints constrain = new GridBagConstraints();

		// Line 1 in UI
		constrain.gridx = 0;
		constrain.gridy = 0; // row
		// constrain.anchor= GridBagConstraints.WEST;
		constrain.insets = new Insets(5, 5, 5, 5);
		constrain.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(new JLabel("Name: "), constrain);

		txtSearch[0] = new JTextField(100);
		txtSearch[0].setMinimumSize(new Dimension(200, 25));
		constrain.gridx = 2;
		constrain.gridy = 0;
		constrain.gridwidth = 2;
		frame.add(txtSearch[0], constrain);

		// Line 2
		constrain.gridx = 0;
		constrain.gridy = 1;
		frame.add(new JLabel("Price: "), constrain);

		txtSearch[1] = new JTextField(100);
		txtSearch[1].setMinimumSize(new Dimension(200, 25));
		constrain.gridx = 2;
		constrain.gridy = 1;
		frame.add(txtSearch[1], constrain);

		// Line 3
		constrain.gridx = 0;
		constrain.gridy = 2;
		frame.add(new JLabel("Discount: "), constrain);

		txtSearch[2] = new JTextField(100);
		txtSearch[2].setMinimumSize(new Dimension(200, 25));
		constrain.gridx = 2;
		constrain.gridy = 2;
		frame.add(txtSearch[2], constrain);

		// Line 4
		constrain.gridx = 0;
		constrain.gridy = 3;
		frame.add(new JLabel("Expiration period: "), constrain);

		txtSearch[3] = new JTextField(100);
		txtSearch[3].setMinimumSize(new Dimension(200, 25));
		constrain.gridx = 2;
		constrain.gridy = 3;
		frame.add(txtSearch[3], constrain);

		// Line 5
		constrain.gridx = 0;
		constrain.gridy = 4;
		frame.add(new JLabel("Provider: "), constrain);

		txtSearch[4] = new JTextField(100);
		txtSearch[4].setMinimumSize(new Dimension(200, 25));
		constrain.gridx = 2;
		constrain.gridy = 4;
		frame.add(txtSearch[4], constrain);

		// Line 6
		constrain.gridx = 0;
		constrain.gridy = 5;
		frame.add(new JLabel("Status: "), constrain);

		statusType = new JComboBox<String>(new String[] { "Unused", "Used" });
		constrain.gridx = 2;
		constrain.gridy = 5;
		frame.add(statusType, constrain);

		// Line 7
		btnEnter = new JButton("Create");
		constrain.gridx = 2;
		constrain.gridy = 7;
		constrain.anchor = GridBagConstraints.LINE_START;
		frame.add(btnEnter, constrain);
	}

	// bind click event to buttons
	public void bindEvent(CouponSystem system) {
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					system.createCoupon(transTxt());
					system.display();
					close();
					
				} catch (Exception e1) {
					MainWindow.showError(e1.getMessage());
				}
			}
		});

	}

	// form user's input into a string
	private String transTxt() {
		String aStr = "";
		for (int i = 0; i < txtSearch.length; i++) {
			aStr += txtSearch[i].getText() + ",";
		}
		if (statusType.getSelectedItem().toString().equals("Unused")) {
			aStr += "false" + ",";
		} else
			aStr += "true" + ",";
		return aStr;
	}

}
