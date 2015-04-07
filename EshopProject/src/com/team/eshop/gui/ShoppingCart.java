package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import com.team.eshop.files.Files;
import com.team.eshop.products.Product;
import com.team.eshop.shoptest.TheShop;

public class ShoppingCart extends JPanel {

	private static ShoppingCart shoppingCart = new ShoppingCart();

	private ShoppingCart() {

	}

	public static ShoppingCart getInstance() {
		return shoppingCart;
	}

	private List<Product> cart = new ArrayList<Product>();

	JPanel card = new JPanel();
	int a = 0;

	public void addProducts(String ids) throws IOException,
			ClassNotFoundException {

		for (String key : TheShop.getAllProducts().keySet()) {

			for (Product value : TheShop.getAllProducts().get(key)) {

				if (ids.equals(value.getId()) && value.isSelected() == false
						&& value.getAvailability() > 0) {

					if (value.getBuffer() <= 3) {
						a += 1;
						value.setBuffer(a);
						cart.add(value);
						cartItemsCounter(cart);
						value.setSelected(true);
					} else if (value.getBuffer() > 3) {
						JOptionPane.showMessageDialog(null,
								"Limit for this Product");
					}
					if (value.getAvailability() == 0) {
						JOptionPane.showMessageDialog(null,
								"The product is out of stock");
					}

				}
			}

		}

	}

	public static void cartItemsCounter(List<Product> list) {

		Gui.getArea().setEditable(false);
		Gui.getArea().setBackground(Color.GRAY);
		Gui.getArea().setForeground(Color.WHITE);
		int counter = 1;
		if (list.size() == 0) {
			Gui.getArea().setText(" ");
			Gui.getArea().setText("The cart is empty ");
		} else if (list.size() == 1) {
			Gui.getArea().setText(" ");
			Gui.getArea().setText("You have added 1 item in the Cart");
		} else {
			Gui.getArea().setText(" ");
			counter = list.size();
			String s = "You have added " + list.size() + "" + " "
					+ " items in the Cart";
			Gui.getArea().setText(s);
		}
		Gui.getMenu().add(Gui.getArea());
	}

	public void checkCart(JPanel cardPanel, CardLayout cl, String id) {

		JTextArea productInfo = new JTextArea();
		JPanel panel = new JPanel();
		JPanel btnPanel = new JPanel();

		JButton remove = new JButton("remove");
		JButton submitButton = new JButton("submit");

		btnPanel.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		Table t = Table.getInstance();
		t.createTable(cart, panel, remove, submitButton, productInfo);

		btnPanel.add(remove);
		btnPanel.add(submitButton);
		panel.add(btnPanel);
		
		cardPanel.add(panel, "1s");
		cl.show(cardPanel, "1s");

	}

}
