package com.team.eshop.files;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.team.eshop.athentification.Client;
import com.team.eshop.products.Pants;
import com.team.eshop.products.Product;

public class Files implements Serializable {

	private static Files f = new Files();

	private Files() {

	}

	public static Files getInstance() {
		return f;
	}

	public <E> void saveFile(List<E> list, String fileName) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		try {
			objOut.writeObject(list);
		} finally {
			objOut.close();
			fileOut.close();
		}

	}

	public  void saveFiles(Map<Client, List<Product>> orderList, String fileName) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		try {
			objOut.writeObject(orderList);
		} finally {
			objOut.close();
			fileOut.close();
		}

	}

	@SuppressWarnings("unchecked")
	public  Map<Client, List<Product>> deserializes(String fileName) throws IOException,
			ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		try {
			Map<Client, List<Product>> products = (Map<Client, List<Product>>) objIn.readObject();
			return products;

		} finally {
			objIn.close();
			fileIn.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public <E> List<E> deserialize(String fileName) throws IOException,
			ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		try {
			List<E> products = (List<E>) objIn.readObject();
			return products;

		} finally {
			objIn.close();
			fileIn.close();
		}
	}

	
}
