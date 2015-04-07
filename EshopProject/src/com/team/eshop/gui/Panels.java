package com.team.eshop.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.team.eshop.files.Files;
import com.team.eshop.products.Product;

public class Panels extends JPanel {
	private static Panels panel = new Panels();

	private Panels() {

	}

	public static Panels getInstance() {
		return panel;
	}

	public static void addText(int id, String fileName, JTextArea text)
			throws IOException {
		Map<Integer, String> map = new HashMap<Integer, String>();
		FileInputStream fstream = new FileInputStream(fileName);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String strLine;

		int counter = 0;
		while ((strLine = br.readLine()) != null) {
			String[] parts = strLine.split("~");

			for (int i = 0; i < parts.length; i++) {

				counter++;
				map.put(counter,
						parts[i].replaceAll("--LB--", System.lineSeparator()));
				break;
			}

		}

		text.setText(map.get(id));

	}

	public Map<Integer, String> addImages(String fileName) throws IOException {

		Map<Integer, String> map = new HashMap<Integer, String>();

		FileInputStream fstream = new FileInputStream(fileName);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String strLine;
		int counter = 0;
		while ((strLine = br.readLine()) != null) {
			counter++;
			map.put(counter, strLine);

		}
		return map;

	}

	public void detailsPanel(int id, JTable table, String fileName,
			JPanel panel, final String ids, final JPanel cardPanel, final CardLayout cl)
			throws IOException, ClassNotFoundException {

		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel textAreaPanel = new JPanel();
		textAreaPanel.setBackground(Color.WHITE);
		JTextArea area = new JTextArea();
		Font font = new Font("Verdana", Font.ITALIC, 13);
		area.setFont(font);
		area.setForeground(Color.darkGray);
		addText(id, fileName, area);
		textAreaPanel.add(area);

		JButton checkCart = new JButton("Check Cart");
		JButton addButton = new JButton("Add to Cart");
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.WHITE);

		JPanel cartButtonPanle = new JPanel();
		cartButtonPanle.setLayout(new BoxLayout(cartButtonPanle,
				BoxLayout.PAGE_AXIS));
		cartButtonPanle.setBackground(Color.WHITE);

		cartButtonPanle.add(textAreaPanel);
		cartButtonPanle.add(addButton);
		cartButtonPanle.add(checkCart);
		cartButtonPanle.add(emptyPanel);
		panel.add(cartButtonPanle);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					ShoppingCart cart = ShoppingCart.getInstance();
					cart.addProducts(ids);

				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		checkCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ShoppingCart cart = ShoppingCart.getInstance();
				cart.checkCart(cardPanel, cl, ids);

			}
		});
	}

}
