package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

import javafx.scene.control.ScrollPane;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.team.eshop.files.Files;
import com.team.eshop.shoptest.TheShop;

public class JacketsGui extends JPanel {

	private static JPanel jacketsCardHolder = new JPanel();
	private JPanel jacketsHomePanel = new JPanel();
	static CardLayout cl = new CardLayout();

	Map<Integer, String> imageMap;
	Panels pan = Panels.getInstance();

	public void createPanel(final int id, String ids) throws ClassNotFoundException,
			IOException {

		jacketsHomePanel.setLayout(new GridLayout(0, 3));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		ImageIcon img = new ImageIcon(getClass().getResource(imageMap.get(id)));
		JLabel lbl = new JLabel(img);
		panel.add(lbl);
		JButton detailsButton = new JButton("details");
		panel.add(detailsButton);
		jacketsHomePanel.add(panel);

		JLabel sublbl = new JLabel(img);
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBackground(Color.WHITE);
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(850, 480));
		p1.setBackground(Color.WHITE);
		Table table = new Table(TheShop.getJackets(), ids, p1, sublbl);
		pan.detailsPanel(id, table, "jacketsText.txt", p1, ids,jacketsCardHolder,cl);
		p.add(p1);
		jacketsCardHolder.add(p, id + "");

		detailsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cl.show(jacketsCardHolder, id + "");

			}
		});

	}

	public JacketsGui() throws IOException, ClassNotFoundException {

		// Panels jacketsPanel = Panels.getInstance();
		imageMap = pan.addImages("jacketsImages.txt");
		add(jacketsCardHolder);
		setBackground(Color.WHITE);

		jacketsCardHolder.setLayout(cl);
		jacketsCardHolder.add(jacketsHomePanel);

		for (int i = 1; i < imageMap.size() + 1; i++) {
			String shoesId = new StringBuilder("j").append(i).toString();

			createPanel(i, shoesId);

		}
	}

	public static void setJacketsItem(JMenuItem item) {
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cl.first(jacketsCardHolder);

			}
		});
	}

}
