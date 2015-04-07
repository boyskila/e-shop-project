package com.team.eshop.gui;

import com.sun.glass.events.KeyEvent;
import com.team.eshop.athentification.Admin;
import com.team.eshop.athentification.User;
import com.team.eshop.files.Files;
import com.team.eshop.products.Jackets;
import com.team.eshop.products.Product;
import com.team.eshop.shoptest.TheShop;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Gui extends JFrame {

	private int currentCard = 1;
	private static JPanel cardPanel;
	private static CardLayout cl = new CardLayout();

	private ImageIcon basket = new ImageIcon(getClass().getResource(
			"basket.jpg"));
	private Map<Integer, String> imageMap;
	private Panels pan = Panels.getInstance();
	private static JPanel menu = new JPanel();
	private static JTextArea area = new JTextArea();

	public Gui() throws IOException, ClassNotFoundException {
		imageMap = pan.addImages("logosImages.txt");
		JPanel logoPanel = new JPanel();

		logoPanel.setBackground(Color.WHITE);

		// logoPanel.setLayout(new GridLayout(5,5));
		for (int i = 1; i < imageMap.size(); i++) {
			ImageIcon img = new ImageIcon(getClass().getResource(
					imageMap.get(i)));
			JLabel l = new JLabel(img);

			logoPanel.add(l);

		}
		JLabel basketLbl = new JLabel(basket);

		JPanel homePanel = new JPanel();
		PantsPanel pantsPanel = new PantsPanel();
		JacketsGui jacketsPanel = new JacketsGui();
		ShoesGui shoesPanel = new ShoesGui();
		ContactPanel contactPanel = new ContactPanel();

		setTitle("TheShop");
		setSize(1200, 700);
		setLocation(130, 20);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		cardPanel = new JPanel();
		cardPanel.setLayout(cl);

		cardPanel.add(homePanel, "home");
		cardPanel.add(new JScrollPane(pantsPanel), "pants");
		cardPanel.add(new JScrollPane(jacketsPanel), "jackets");
		cardPanel.add(new JScrollPane(shoesPanel), "shoes");
		cardPanel.add(contactPanel, "contacts");

		// JPanel buttonPanel = new JPanel();
		// JButton firstBtn = new JButton("First");
		// JButton nextBtn = new JButton("Next");
		// JButton previousBtn = new JButton("Previous");
		// JButton lastBtn = new JButton("Last");
		// buttonPanel.add(firstBtn);
		// buttonPanel.add(nextBtn);
		// buttonPanel.add(previousBtn);
		// buttonPanel.add(lastBtn);

		getMenu().setBackground(Color.GRAY);
		getMenu().add(getArea());

		createMenu(getMenu());
		getMenu().add(basketLbl);

		//
		// firstBtn.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// cl.first(cardPanel);
		// currentCard = 1;
		// }
		// });
		//
		// lastBtn.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// cl.last(cardPanel);
		// currentCard = 4;
		// }
		// });
		//
		// nextBtn.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// if (currentCard < 4) {
		// currentCard += 1;
		// cl.show(cardPanel, "" + (currentCard));
		// }
		// }
		// });
		//
		// previousBtn.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// if (currentCard > 1) {
		// currentCard -= 1;
		// cl.show(cardPanel, "" + (currentCard));
		// }
		// }
		// });
		// ShoppingCart cart = ShoppingCart.getInstance();
		// cart.cartItemsCounter(getMenu(), area);
		add(cardPanel, BorderLayout.CENTER);
		add(getMenu(), BorderLayout.NORTH);
		add(logoPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void createMenu(JPanel hp) {

		JMenuBar menuBar = new JMenuBar();
		hp.add(menuBar);

		JPanel searchPanel = new JPanel();
		final JTextField searchField = new JTextField(15);
		final JButton searchButton = new JButton("search");

		searchButton.setPreferredSize(new Dimension(80, 21));
		searchPanel.setBackground(Color.GRAY);
		searchPanel.add(searchField);
		searchButton.setMnemonic(KeyEvent.VK_ENTER);
		searchPanel.add(searchButton);

		hp.add(searchPanel);

		JMenuItem home = new JMenuItem("HOME");
		JMenu wear = new JMenu("WEAR");
		JMenu electronics = new JMenu("ELECTRONICS");
		JMenu contacts = new JMenu("CONTACTS");

		menuBar.add(home);
		menuBar.add(wear);
		menuBar.add(electronics);
		menuBar.add(contacts);

		JMenuItem pants = new JMenuItem("pants");
		JMenuItem jackets = new JMenuItem("jackets");
		JMenuItem shoes = new JMenuItem("shoes");

		wear.add(pants);
		wear.add(jackets);
		wear.add(shoes);

		JMenuItem computers = new JMenuItem("Computers");
		JMenuItem audio = new JMenuItem("Audio");
		JMenuItem cellPhones = new JMenuItem("Cell Phones");

		electronics.add(computers);
		electronics.add(audio);
		electronics.add(cellPhones);

		JMenuItem admin = new JMenuItem("ADMIN");
		contacts.add(admin);

		itemSetup(cl, pants, cardPanel, "pants");
		itemSetup(cl, jackets, cardPanel, "jackets");
		itemSetup(cl, shoes, cardPanel, "shoes");
		itemSetup(cl, home, cardPanel, "home");

		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchResultPanel result = new SearchResultPanel();
				for (int i = 0; i < 6; i++) {

					result.createSearchResultPanel(i, searchField, cardPanel,
							cl);
					searchField.setText("");
				}
			}

		});

		admin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShowDialog jop = new ShowDialog();

			}

		});

	}

	public static void itemSetup(final CardLayout cardL, final JMenuItem item, final JPanel pan,
			final String panelName) {
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cardL.show(pan, panelName);
			}
		});

		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PantsPanel.setPantsItem(item);
				JacketsGui.setJacketsItem(item);
				ShoesGui.setShoesItem(item);
			}
		});
	}

	public static JPanel getMenu() {
		return menu;
	}

	public void setMenu(JPanel menu) {
		this.menu = menu;
	}

	public static JTextArea getArea() {
		return area;
	}

	public static void setArea(JTextArea area) {
		Gui.area = area;
	}

}