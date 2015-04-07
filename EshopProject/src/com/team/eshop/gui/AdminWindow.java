package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.team.eshop.athentification.Client;
import com.team.eshop.athentification.User;
import com.team.eshop.files.Files;
import com.team.eshop.products.Pants;
import com.team.eshop.products.Product;
import com.team.eshop.shoptest.TheShop;

public class AdminWindow extends JFrame {

	private JPanel panel = new JPanel();
	private JPanel menuPanel = new JPanel();

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	JTextArea area = new JTextArea();
	private JMenuBar menu = new JMenuBar();
	private JButton wear = new JButton("WEAR");
	private JButton electronics = new JButton("ELECTRONICS");
	private JButton update = new JButton("UPDATE");
	private JButton orders = new JButton("Check Orders");
	private Map<Client, List<Product>> order = new HashMap<Client, List<Product>>();
	private JPanel card = new JPanel();
	private CardLayout cl = new CardLayout();

	public AdminWindow() {
		card.setLayout(cl);
		card.setBackground(Color.WHITE);
		add(card, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 700);
		setLocation(300, 15);
		//panel.setLayout(cl);
		// panel.setLayout(new FlowLayout());
		//panel.setPreferredSize(new Dimension(0, 700));
		//panel.setLayout(new GridLayout(0, 3));
		panel.setBackground(Color.WHITE);

		menu.add(wear);
		menu.add(electronics);
		menu.add(update);
		menu.add(orders);

		menuPanel.add(menu);

		add(menuPanel, BorderLayout.NORTH);

		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		card.add(panel4,"orders");
		card.add(panel, "wear");
		wear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Table pants = new Table(TheShop.getPantsList(), panel1);
				Table jackets = new Table(TheShop.getJackets(), panel2);
				Table shoes = new Table(TheShop.getShoes(), panel3);
				cl.show(card, "wear");
				setVisible(true);
			}
		});
		// update.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// List<Object> list = new ArrayList<Object>();
		//
		// list.add(textArea.getText());
		// for (Object object : list) {
		// // p.setPantsList();
		// }
		//
		// try {
		// f.saveFile(TheShop.getPantsList(), "pants.srz");
		// JOptionPane pane = new JOptionPane();
		// pane.showMessageDialog(null, "List is succesfull updated");
		// } catch (IOException e1) {
		//
		// e1.printStackTrace();
		// }
		// }
		// });
		orders.addActionListener(new ActionListener() {
			Files file = Files.getInstance();

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					setOrders(file.deserializes("order.srz"));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				panel4.add(area);
				for (Client key : getOrder().keySet()) {

					for (Product value : getOrder().get(key)) {

						area.append(key.toString() + "\n" + value.toString());
					}

				}
				cl.show(panel, "4");
			}
		});
		setVisible(true);
	}

	public Map<Client, List<Product>> getOrder() {
		return order;
	}

	public void setOrders(Map<Client, List<Product>> order) {
		this.order = order;
	}
}
