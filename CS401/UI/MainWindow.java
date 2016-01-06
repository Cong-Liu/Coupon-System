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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import CS401.CouponSystem;

public class MainWindow {
	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;

	// UI elements
	private JButton btnLoad;
	private JButton btnNew;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JComboBox<String> sortType;
	private JButton btnSort;

	public MainWindow() {
		// create a window frame
		frame = new JFrame("Coupon Inventory System");
		frame.setSize(650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setLayout(new GridBagLayout()); // create grid bag layout
		initComponents();
	}

	public void show() {
		frame.setVisible(true);
	}

	// add a row to data table
	public void addRow(Object[] columns) throws Exception {
		if (columns.length != model.getColumnCount())
			throw new Exception("Column length error.");
		model.addRow(columns);
	}


	// remove all rows
	public void clearRows() {
		int rowCount = model.getRowCount();

		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	
	// draw buttons, tables and text fields in GUI frame
	private void initComponents() {
		GridBagConstraints constrain = new GridBagConstraints();

		// Line 1 in UI
		constrain.gridx = 0;
		constrain.gridy = 0;
		constrain.insets = new Insets(5, 5, 5, 5);
		constrain.anchor = GridBagConstraints.LINE_START;
		frame.add(new JLabel("Coupon:"), constrain);

		btnLoad = new JButton("Load from File");
		constrain.gridx = 1;
		constrain.gridy = 0;
		frame.add(btnLoad, constrain);

		btnNew = new JButton("Create new");
		constrain.gridx = 2;
		constrain.gridy = 0;
		frame.add(btnNew, constrain);

		// Line 2 in UI
		constrain.gridx = 0;
		constrain.gridy = 1;
		frame.add(new JLabel("Search:"), constrain);

		txtSearch = new JTextField(100);
		txtSearch.setMinimumSize(new Dimension(117, 25));
		constrain.gridx = 1;
		constrain.gridy = 1;
		frame.add(txtSearch, constrain);

		btnSearch = new JButton("Search");
		constrain.gridx = 2;
		constrain.gridy = 1;
		frame.add(btnSearch, constrain);

		// Line 3 in UI
		constrain.gridx = 0;
		constrain.gridy = 2;
		frame.add(new JLabel("Sort by:"), constrain);

		sortType = new JComboBox<String>(new String[] { "Name", "Price",
				"Discount rate", "Expiration period", "Provider", "Status" });
		constrain.gridx = 1;
		constrain.gridy = 2;
		frame.add(sortType, constrain);

		btnSort = new JButton("Sort");
		constrain.gridx = 2;
		constrain.gridy = 2;
		frame.add(btnSort, constrain);

		// Line 4 in UI
		constrain.gridx = 0;
		constrain.gridy = 3;
		constrain.gridwidth = 3;
		constrain.fill = GridBagConstraints.BOTH;
		constrain.weightx = 100;
		constrain.weighty = 100;
		frame.add(createTable(), constrain);
	}

	// bind click event to buttons
	public void bindEvent(CouponSystem system) {
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					system.loadFile("coupon list.txt");
					system.display();
				} catch (Exception e1) {
					showError(e1.getMessage());
				}
			}
		});

		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create NewCouponWindow

				NewCouponWindow add = new NewCouponWindow();
				add.bindEvent(system);
				add.show();
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					system.search(txtSearch.getText());
				} catch (Exception e1) {
					showError(e1.getMessage());
				}
			}
		});

		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					system.sort(sortType.getSelectedItem().toString());
				} catch (Exception e1) {
					showError(e1.getMessage());
				}
			}
		});
	}

	// create data table
	@SuppressWarnings("serial")
	private JScrollPane createTable() {
		this.model = new DefaultTableModel(new Object[] { "Name", "Price",
				"Discount rate", "Expiration period", "Provider", "Status" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		this.table = new JTable(this.model);
		this.table.setFillsViewportHeight(true);

		return new JScrollPane(table);
	}

	// pop up an information box
	public static void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	// pop up an error box, useful for exception
	public static void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
