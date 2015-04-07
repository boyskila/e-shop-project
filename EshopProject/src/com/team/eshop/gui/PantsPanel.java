package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.team.eshop.files.Files;
import com.team.eshop.products.Product;
import com.team.eshop.shoptest.TheShop;

public class PantsPanel extends JPanel {

	private static JPanel pantsCardHolder = new JPanel();
	private JPanel pantsHomePanel = new JPanel();
	static CardLayout cardlayout = new CardLayout();
	Map<Integer, String> imageMap;
	Map<Integer, String> logos;
	Panels pan = Panels.getInstance();

	public void createPanel(int id, String ids) throws ClassNotFoundException,
			IOException {

		pantsHomePanel.setLayout(new GridLayout(0, 3));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		ImageIcon img = new ImageIcon(getClass().getResource(imageMap.get(id)));
		ImageIcon logo = new ImageIcon(getClass().getResource(logos.get(id)));
		JLabel productLogo = new JLabel(logo);
		JLabel lbl = new JLabel(img);
		panel.add(lbl);
		JPanel buttonLablePanel = new JPanel();
		buttonLablePanel.setBackground(Color.WHITE);
		buttonLablePanel.setLayout(new BoxLayout(buttonLablePanel,
				BoxLayout.PAGE_AXIS));
		JButton detailsButton = new JButton("details");

		buttonLablePanel.add(detailsButton);
		buttonLablePanel.add(productLogo);
		panel.add(buttonLablePanel);
		pantsHomePanel.add(panel);

		JLabel sublbl = new JLabel(img);
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBackground(Color.WHITE);
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(850, 480));
		p1.setBackground(Color.WHITE);
		
		Table table = new Table(TheShop.getPantsList(), ids, p1, sublbl);
		pan.detailsPanel(id, table, "pantsTxt.txt", p1, ids, pantsCardHolder,
				cardlayout);
		p.add(p1);
		pantsCardHolder.add(p, id + "");
		
		buttonSetup(detailsButton, id);

	}

	public PantsPanel() throws ClassNotFoundException, IOException {
		imageMap = pan.addImages("pantsImages.txt");
		logos = pan.addImages("logosImages.txt");
		add(pantsCardHolder);
		setBackground(Color.WHITE);

		pantsCardHolder.setLayout(cardlayout);
		pantsCardHolder.add(pantsHomePanel);

		for (int i = 1; i < imageMap.size() + 1; i++) {
			String str = new StringBuilder("p").append(i).toString();
			createPanel(i, str);
		}
	}

	public void buttonSetup(JButton button, final int id) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cardlayout.show(pantsCardHolder, id + "");
				
			}
		});
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.first(pantsCardHolder);
			}
		});
	}

	

	public static void setPantsItem(JMenuItem item) {
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cardlayout.first(pantsCardHolder);

			}
		});
	}

}
