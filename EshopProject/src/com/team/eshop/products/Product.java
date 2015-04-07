package com.team.eshop.products;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import sun.management.counter.Units;

import com.team.eshop.files.Files;
import com.team.eshop.files.ShoppingCart;

public class Product implements Serializable {
	private String id;
	private String brand;
	private String model;
	private String color;
	private String size;
	private double pricePerPiece;
	private double pricePerBox;
	private int availability;
	private boolean isSelected;
	private int buffer;
	private double choosenPrice;

	private List<Pants> pantsList = new ArrayList<Pants>();
	private List<Shoes> shoesList = new ArrayList<Shoes>();
	private List<Jackets> jacketsList = new ArrayList<Jackets>();

	public Product() {

	}

	public Product(String id, String brand, String model, String color,
			String size, double pricePerPiece, double pricePerBox,
			int availability, boolean isSelected, int buffer,
			double choosenPrice) {
		this.id = id;
		this.brand = brand;
		this.setModel(model);
		this.color = color;
		this.size = size;
		this.pricePerPiece = pricePerPiece;
		this.pricePerBox = pricePerBox;
		this.availability = availability;
		this.setSelected(isSelected);
		this.setBuffer(buffer);
		this.setChoosenPrice(choosenPrice);
	}

	public <E> void checkProducts(List<E> listOfProducts, String file)
			throws ClassNotFoundException, IOException {

		// listOfProducts = Files.deserialize(file);
		Iterator<E> i = listOfProducts.iterator();

		while (i.hasNext()) {
			Product pro = (Product) i.next();
			System.out.println(pro.toString()
					+ "\n#############################\n");

		}
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Id -->" + id + "\nbrand --> " + brand + "\ncolor --> " + color
				+ "\nsize -->" + size + "\nprice -->" + pricePerPiece
				+ "\npricePerBox --> " + pricePerBox + "\navailability --> "
				+ availability + "\nChoosen Price --> " + choosenPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPricePerPiece() {
		return pricePerPiece;
	}

	public void setPricePerPiece(double pricePerPiece) {
		this.pricePerPiece = pricePerPiece;
	}

	public double getPricePerBox() {
		return pricePerBox;
	}

	public void setPricePerBox(double pricePerBox) {
		this.pricePerBox = pricePerBox;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public int getBuffer() {
		return buffer;
	}

	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}

	public double getChoosenPrice() {
		return choosenPrice;
	}

	public void setChoosenPrice(double choosenPrice) {
		this.choosenPrice = choosenPrice;
	}

}
