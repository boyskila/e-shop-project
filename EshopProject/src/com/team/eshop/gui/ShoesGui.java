package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.scene.control.ScrollPane;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.team.eshop.products.Product;
import com.team.eshop.shoptest.TheShop;

public class ShoesGui extends JPanel {

	private static JPanel shoesCardHolder = new JPanel();
	private JPanel shoesHomePanel = new JPanel();
	static CardLayout cl = new CardLayout();
	Map<Integer, String> imgMap;

	Panels pan = Panels.getInstance();

	public void createPanel(final int id, String ids) throws ClassNotFoundException,
			IOException {

		shoesHomePanel.setLayout(new GridLayout(0, 3));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		ImageIcon img = new ImageIcon(getClass().getResource(imgMap.get(id)));
		JLabel lbl = new JLabel(img);
		panel.add(lbl);
		JButton detailsButton = new JButton("details");
		panel.add(detailsButton);
		shoesHomePanel.add(panel);

		JLabel sublbl = new JLabel(img);
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.setLayout(new GridBagLayout());
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(850, 480));
		p.setBackground(Color.WHITE);

		Table table = new Table(TheShop.getShoes(), ids, p, sublbl);
		pan.detailsPanel(id, table, "shoesTxt.txt", p, ids, shoesCardHolder, cl);
		p1.add(p);
		shoesCardHolder.add(p1, id + "");

		detailsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cl.show(shoesCardHolder, id + "");

			}
		});

	}

	public ShoesGui() throws ClassNotFoundException, IOException {

		imgMap = pan.addImages("shoesImages.txt");
		shoesHomePanel.setBackground(Color.WHITE);
		add(shoesCardHolder);
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		shoesCardHolder.setLayout(cl);
		shoesCardHolder.add(shoesHomePanel);

		for (int i = 1; i < imgMap.size() + 1; i++) {

			String shoesId = new StringBuilder("s").append(i).toString();
			createPanel(i, shoesId);

		}

	}

	public static void setShoesItem(JMenuItem item) {
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cl.first(shoesCardHolder);

			}
		});
	}
}
