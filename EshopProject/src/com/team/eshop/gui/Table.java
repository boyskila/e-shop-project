package com.team.eshop.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.team.eshop.files.Files;
import com.team.eshop.products.Product;

public class Table extends JTable {

	private String[] columnNames = { "Type", "Value" };
	private Object[] value = { "Per piece", "Per box" };
	private JComboBox comboBox = new JComboBox(value);
	static ShoppingCart cart = ShoppingCart.getInstance();
	private static JTable table;
	private static DefaultTableModel model = new DefaultTableModel();
	private List<Double> purchasedProducts = new ArrayList<Double>();
	private static Table tables = new Table();

	public static Table getInstance() {
		return tables;
	}

	Table() {

	}

	public void createTable(final List<Product> list, JPanel panel,
			JButton removeBtn, JButton submitButton, JTextArea productInfo) {

		Object[] values = { "Per piece", "Per box" };
		final JComboBox combo = new JComboBox(values);
		int counter = 1;
		final DefaultCellEditor cellEdit = new DefaultCellEditor(combo);

		model.addColumn("BrandAndModel");
		model.addColumn("SelectPrice");
		model.addColumn("Price");
		model.addColumn("SelectProduct");
		if (model.getColumnCount() > 4) {
			model.setColumnCount(4);

		}

		for (Product e : list) {
			if (e.isSelected() == true) {
				e.setSelected(false);
				Object[] product = { e.getBrand() + " " + e.getModel(),
						"Select Unit Price ", " ", false };

				model.addRow(product);

			}
		}
		table = new JTable(model) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;

				default:
					return Boolean.class;

				}

			}

			public boolean isCellEditable(int row, int column) {
				if (column == 1 || column == 3)
					return true;
				else
					return false;

			}

			public TableCellEditor getCellEditor(int row, int column) {
				int modelColumn = convertColumnIndexToModel(column);

				if (modelColumn == 1)
					return cellEdit;
				else
					return super.getCellEditor(row, column);

			}

		};

		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object c = (Object) e.getSource();

				if (c == combo) {
					JComboBox cb = (JComboBox) e.getSource();
					String s = (String) cb.getSelectedItem();
					for (Product prod : list) {
						if (s.equals("Per piece")
								&& (prod.getBrand() + " " + prod.getModel())
										.equals(model.getValueAt(
												table.getSelectedRow(), 0))) {
							model.setValueAt(prod.getPricePerPiece(),
									table.getSelectedRow(), 2);

						} else if (s.equals("Per box")
								&& (prod.getBrand() + " " + prod.getModel())
										.equals(model.getValueAt(
												table.getSelectedRow(), 0))) {

							table.getModel().setValueAt(prod.getPricePerBox(),
									table.getSelectedRow(), 2);

						}
					}
				}
			}
		});

		table.setRowHeight(30);
		table.setGridColor(Color.GREEN);

		panel.add(table);
		cart.cartItemsCounter(list);
		removeButton(list, removeBtn, productInfo);
		submitButton(list, submitButton, panel, productInfo);
	}

	private static BigDecimal truncateDecimal(double x, int numberofDecimals) {

		return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals,
				BigDecimal.ROUND_CEILING);

	}

	public static void removeButton(final List<Product> list1, JButton removeBtn,
			final JTextArea prodInfo) {
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				prodInfo.setText(" ");
				for (int i = 0; i < model.getRowCount() + 10; i++) {

					for (int row = 0; row < model.getRowCount(); row++) {

						if ((Boolean) model.getValueAt(row, 3) == true) {

							model.removeRow(row);

							list1.get(row).setSelected(false);
							list1.remove(row);
							cart.cartItemsCounter(list1);

						}

					}
				}

			}

		});
	}

	public void submitButton(final List<Product> list, final JButton submitButton,
			final JPanel panel, final JTextArea productInfo) {
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Product> orderList = new ArrayList<Product>();
				OrderForm orderForm = OrderForm.getInstance();
				orderForm.createOrderForm(orderList, panel, submitButton,
						productInfo);
				panel.add(productInfo);
				for (int i = 0; i < model.getRowCount(); i++) {
					if ((Boolean) model.getValueAt(i, 3) == true) {
						if (list.get(i) != null) {
							if (model.getValueAt(i, 2).equals(" ")) {
								JOptionPane.showMessageDialog(null,
										"Please select unit price of "
												+ list.get(i).getBrand() + " "
												+ list.get(i).getModel());
								productInfo.setText(" ");

							} else {
								double str = (double) model.getValueAt(i, 2);
								list.get(i).setChoosenPrice(str);
								orderList.add(list.get(i));

								moneyLimit(str);
								productInfo.append(list.get(i).getBrand() + " "
										+ list.get(i).getModel() + "    "
										+ model.getValueAt(i, 2) + " lv \\ "
										+ truncateDecimal(str / 1.95, 0)
										+ " eu" + "\n");

							}

							list.get(i).setSelected(false);
						}
					}

				}
				
				

			}

		});
	}

	public void moneyLimit(double b) {
		double total = 0;
		for (int i = 0; i < model.getRowCount(); i++) {
			total += b;
		}
		if (total >= 2000) {
			JOptionPane.showMessageDialog(null,
					"You have reached the limit of your account");

		}

	}

	public Table(List<Product> list, JPanel panel) {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		for (Product pants2 : list) {

			if (pants2.getAvailability() < 5) {

				Object[][] data = {
						{ "Brand", ((Product) pants2).getBrand() },
						{ "Model", ((Product) pants2).getModel() },
						{ "Color", ((Product) pants2).getColor() },
						{ "Size", ((Product) pants2).getSize() },
						{ "Availability", ((Product) pants2).getAvailability() } };
				DefaultTableModel model = new DefaultTableModel(data,
						columnNames);
				JTable table = new JTable(model);
				table.setRowHeight(30);
				p.add(table);
				panel.add(p);

			}
		}

	}

	public Table(final List<Product> list, final String Id, JPanel pane, JLabel imageLable)
			throws ClassNotFoundException, IOException {

		setBackground(Color.WHITE);

		for (Product pants2 : list) {

			if (((Product) pants2).getId().equals(Id)) {

				Object[][] data = { { "Brand", pants2.getBrand() },
						{ "Model", pants2.getModel() },
						{ "Color", pants2.getColor() },
						{ "Size", pants2.getSize() }, { "Check Unit price", },
						{ "Availability", pants2.getAvailability() } };

				final DefaultCellEditor cellEdit = new DefaultCellEditor(comboBox);

				DefaultTableModel model = new DefaultTableModel(data,
						columnNames) {
					@Override
					public boolean isCellEditable(int row, int column) {
						if (column == 0 && row == 4)
							return true;
						else
							return false;

					}
				};

				final JTable table = new JTable(model) {

					public TableCellEditor getCellEditor(int row, int column) {
						int modelColumn = convertColumnIndexToModel(column);

						if (modelColumn == 0 && row == 4)
							return cellEdit;
						else
							return super.getCellEditor(row, column);
					}
				};

				table.setRowHeight(30);
				JPanel tablePanel = new JPanel();
				table.setPreferredSize(new Dimension(250, 200));
				tablePanel.setBackground(Color.WHITE);
				tablePanel.add(table);
				tablePanel.add(imageLable);
				pane.add(tablePanel);

				comboBox.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						Object c = (Object) e.getSource();

						if (c == comboBox) {
							JComboBox cb = (JComboBox) e.getSource();
							String s = (String) cb.getSelectedItem();
							for (Product product : list) {
								if (s.equals("Per piece")
										&& product.getId().equals(Id)) {

									table.getModel().setValueAt(
											product.getPricePerPiece() + " lv",
											table.getSelectedRow(), 1);

								} else if (s.equals("Per box")
										&& product.getId().equals(Id)) {

									table.getModel().setValueAt(
											product.getPricePerBox() + " lv",
											table.getSelectedRow(), 1);
								}
							}

						}

					}

				});
			}

		}
	}

}