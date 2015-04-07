package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.team.eshop.athentification.User;
import com.team.eshop.shoptest.TheShop;

public class ShowDialog extends JFrame {

    public ShowDialog() {

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        setSize(230, 150);
        setLocation(600, 300);

        JPanel label = new JPanel(new GridLayout(0, 1));
        label.add(new JLabel("Username", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label);

        final JButton btn = new JButton("OK");

        final JPanel controls = new JPanel(new GridLayout(0, 1));

        final JTextField username = new JTextField(10);
        final JPasswordField password = new JPasswordField(10);

        controls.add(username);
        controls.add(password);

        panel.add(controls);
        panel.add(btn);

        frame.add(panel);

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean flag = true;
                for (User admins : TheShop.getAdmins()) {

                    if (username.getText().equals(admins.getUsername())
                            && password.getText().equals(admins.getPassword())) {
                        AdminWindow adminPanel = new AdminWindow();

                        username.setText("");
                        password.setText("");
                        flag = false;
                        break;

                    }
                }

                if (flag) {
                    JOptionPane.showMessageDialog(null, "Incorect Username or Password");

                }
                setVisible(false);
            }
        });

        add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}