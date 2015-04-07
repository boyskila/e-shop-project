package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.team.eshop.athentification.Client;
import com.team.eshop.files.Files;
import com.team.eshop.products.Product;
import com.team.eshop.shoptest.TheShop;

public class OrderForm extends JPanel {

    private static OrderForm orderForm = new OrderForm();

    public static OrderForm getInstance() {
        return orderForm;
    }

    OrderForm() {

    }

    private Map<Client, List<Product>> orderList = new HashMap<Client, List<Product>>();

    public void createOrderForm(final List<Product> list, JPanel panel, JButton btn, final JTextArea area) {

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        final JLabel lblFirstName = new JLabel("First Name:");
        final JTextField txtFirstName = new JTextField();

        final JLabel lblLastName = new JLabel("Last Name:");
        final JTextField txtLastName = new JTextField();

        final JLabel lblAddress = new JLabel("Address:");
        final JTextArea txtareaAddress = new JTextArea();

        final JLabel telefonNumber = new JLabel("Telefon Number:");
        final JTextField txtTelNumber = new JTextField();

      final  JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);

        contentPanel.setLayout(new GridLayout(0, 2));

        contentPanel.add(lblFirstName);
        contentPanel.add(txtFirstName);

        contentPanel.add(lblLastName);
        contentPanel.add(txtLastName);

        contentPanel.add(telefonNumber);
        contentPanel.add(txtTelNumber);

        contentPanel.add(lblAddress);
        contentPanel.add(new JScrollPane(txtareaAddress));

        JTextArea productText = new JTextArea();
        contentPanel.add(productText);

        JButton buy = new JButton("buy");
        p.add(buy);
        contentPanel.add(p);

        panel.add(contentPanel);

        buy.addActionListener(new ActionListener() {
            Files f = Files.getInstance();

            @Override
            public void actionPerformed(ActionEvent e) {

                String fName = txtFirstName.getText();
                String sName = txtLastName.getText();
                String address = txtareaAddress.getText();
                String telNumber = txtTelNumber.getText();

                Client client = new Client(fName, sName, telNumber, address);
                orderList.put(client, list);

                for (Product product : list) {
                    if (product.getChoosenPrice() == product.getPricePerPiece()) {
                        product.setAvailability(product.getAvailability() - 1);

                        try {
                            updateProductData(list, f);
                        } catch (IOException e1) {

                            e1.printStackTrace();
                        }

                    } else {
                        product.setAvailability(product.getAvailability() - 10);
                        try {
                            f.saveFiles(orderList, "order.srz");
                            updateProductData(list, f);
                        } catch (IOException e1) {

                            e1.printStackTrace();
                        }
                    }

                    product.setSelected(false);
                    product.setBuffer(0);

                }
                try {
                    f.saveFiles(orderList, "order.srz");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                txtFirstName.setText(" ");
                txtLastName.setText(" ");
                txtareaAddress.setText(" ");
                txtTelNumber.setText(" ");
                area.setText(" ");
                area.setForeground(Color.RED);
                area.append("Your request is sent");
                contentPanel.setVisible(true);
            }

        });

    }

    public void updateProductData(List<Product> list, Files f) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 10; j++) {

                String strPants = new StringBuilder("p").append(j).toString();
                String strJackets = new StringBuilder("j").append(j).toString();
                String strShoes = new StringBuilder("s").append(j).toString();
                if (list.get(i).getId().equals(strPants)) {
                    f.saveFile(TheShop.getPantsList(), "pants.srz");
                    break;
                } else if (list.get(i).getId().equals(strJackets)) {
                    f.saveFile(TheShop.getJackets(), "jackets.srz");
                    break;
                } else if (list.get(i).getId().equals(strShoes)) {
                    f.saveFile(TheShop.getShoes(), "shoes.srz");
                    break;
                }
            }
        }
    }
}
