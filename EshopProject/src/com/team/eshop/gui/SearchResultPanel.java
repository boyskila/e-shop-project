package com.team.eshop.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.JTextField;

import com.team.eshop.products.Product;
import com.team.eshop.shoptest.TheShop;

public class SearchResultPanel extends JPanel {
	private Map<Integer, String> map;

	public SearchResultPanel() {

	}

	public <E> void searchProduct() {

		Map<String, List<Product>> allProducts = new HashMap<String, List<Product>>();

		for (Entry<String, List<Product>> entry : allProducts.entrySet()) {
			String product = entry.getKey();
			List<Product> products = entry.getValue();
			for (int i = 0; i < products.size(); i++) {

				if (products.get(i) != null) {
					System.out.println(entry.getKey() + " " + products.get(i));
				}
			}
		}
	}

	public void createSearchResultPanel(int id, JTextField searchField,
			JPanel cardPanel, CardLayout cl) {

		Panels panels = Panels.getInstance();
		JPanel homePanel = new JPanel();
		String str = searchField.getText().toLowerCase();

		for (String key : TheShop.getAllProducts().keySet()) {

			for (Product value : TheShop.getAllProducts().get(key)) {

				if (str.equals(value.getBrand().toLowerCase())
						|| str.equals(value.getModel().toLowerCase())
						|| str.equals(value.getBrand().toLowerCase() + " "
								+ value.getModel().toLowerCase())
						|| str.equals(key.toLowerCase())) {

					String txt = null;
					try {
						if (key.equals("Pants")) {

							txt = "pantsTxt.txt";
							map = panels.addImages("pantsImages.txt");

						} else if (key.equals("Jackets")) {

							txt = "jacketsText.txt";
							map = panels.addImages("jacketsImages.txt");

						} else if (key.equals("Shoes")) {

							txt = "shoesTxt.txt";
							map = panels.addImages("shoesImages.txt");

						}

						String extractedDigit = value.getId().replaceAll(
								"[^0-9]", "");
						int idCounter = Integer.parseInt(extractedDigit);

						ImageIcon img = new ImageIcon(getClass().getResource(
								map.get(idCounter)));

						JLabel sublbl = new JLabel(img);
												
						Table table = new Table(TheShop.getAllProducts().get(
								key), value.getId(), homePanel, sublbl);
						panels.detailsPanel(idCounter, table, txt, homePanel, value.getId(),cardPanel,cl);

						cardPanel.add(new JScrollPane(homePanel), idCounter
								+ "");
						cl.show(cardPanel, idCounter + "");

					} catch (ClassNotFoundException e2) {

						e2.printStackTrace();
					} catch (IOException e2) {

						e2.printStackTrace();
					}

				}

			}

		}

	}

}
