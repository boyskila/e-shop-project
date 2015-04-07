package com.team.eshop.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

public class AddressForm extends JFrame {
	private JLabel lblFirstName = new JLabel("First Name:");
	private JTextField txtFirstName = new JTextField();

	private JLabel lblLastName = new JLabel("Last Name:");
	private JTextField txtLastName = new JTextField();

	private JLabel lblCountry = new JLabel("Country:");
	private JComboBox<String> country;

	private JLabel lblGender = new JLabel("Gender:");
	private JRadioButton rdMale = new JRadioButton("Male");
	private JRadioButton rdFemale = new JRadioButton("Female");

	private JLabel lblAge = new JLabel("Age:");
	private JSpinner spnAge = new JSpinner();

	private JLabel lblAddress = new JLabel("Address:");
	private JTextArea txtareaAddress = new JTextArea();
	
	private JButton btnSubmit = new JButton("Submit");

	private JLabel lblResult = new JLabel("Result:");
	private JTextArea txtareaResult = new JTextArea();

	public AddressForm() {
		country = new JComboBox<String>(new String[] { "Bulgaria", "USA",
				"China", "Rusia", "Germany" });
		spnAge.setModel(new SpinnerNumberModel(20, 1, 90, 1));
		
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdMale);
		genderGroup.add(rdFemale);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setSize(600,200);
		
		contentPanel.setLayout(new GridLayout(0, 2));
		
		contentPanel.add(lblFirstName);
		contentPanel.add(txtFirstName);
		
		contentPanel.add(lblLastName);
		contentPanel.add(txtLastName);
		
		contentPanel.add(lblCountry);
		contentPanel.add(country);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		radioPanel.add(rdMale);
		radioPanel.add(rdFemale);
		
		contentPanel.add(lblGender);
		contentPanel.add(radioPanel);
		
		contentPanel.add(lblAge);
		contentPanel.add(spnAge);
		
		contentPanel.add(lblAddress);
		contentPanel.add(new JScrollPane(txtareaAddress));
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		this.getContentPane().add(contentPanel);

		JPanel topResultPanel = new JPanel();
		topResultPanel.setLayout(new BoxLayout(topResultPanel, BoxLayout.Y_AXIS));
		topResultPanel.add(btnSubmit);
		topResultPanel.add(lblResult);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(500,200);
		resultPanel.setLayout(new BorderLayout());
		resultPanel.add(topResultPanel, BorderLayout.NORTH);
		resultPanel.add(new JScrollPane(txtareaResult), BorderLayout.CENTER);
		
		
		this.getContentPane().add(contentPanel);
		this.getContentPane().add(resultPanel);
		
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSubmitClick(e);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private void btnSubmitClick(ActionEvent e) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("first Name:"  + txtFirstName.getText() + "\n");
		stringBuilder.append("last Name:"  + txtLastName.getText() + "\n");
		stringBuilder.append("Age:"  + spnAge.getValue() + "\n");
		stringBuilder.append("Country:"  + country.getSelectedItem() + "\n");
		stringBuilder.append("Gender:"  + (rdFemale.isSelected() ? "female" :"Male") + "\n");
		stringBuilder.append("Address"  + txtareaAddress.getText() + "\n");
		txtareaResult.setText(stringBuilder.toString());
		
	}

	public static void main(String[] args) {
		AddressForm form = new AddressForm();
		form.setSize(500, 500);
		form.setVisible(true);
	}
}
