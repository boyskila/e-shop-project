package com.team.eshop.files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.lang.model.element.Element;

import com.team.eshop.products.*;
import com.team.eshop.shoptest.TheShop;

public class ShoppingCart {
	private int counter = 0;

	private Product[] shoppingCart = new Product[10];
	Scanner scen = new Scanner(System.in);
	private String idOfProduct = null;
	private int numberOfAddedProducts = 0;
	private int boxCounter = 0;
	private int piececCounter = 0;

	public ShoppingCart() {

	}

	public void limitOfTheCart(String input, int counter)
			throws ClassNotFoundException, IOException {

		if (idOfProduct.equals("exit")) {
			if (getPiececCounter() != 0 || getBoxCounter() != 0) {
				addedItems(getPiececCounter(), getBoxCounter());
			}
			System.out.println("This is the End");
			System.exit(0);
		} else if (counter >= 5) {
			System.out
					.println("\n=========================\nThe cart is full\n=========================\n");
			addedItems(getPiececCounter(), getBoxCounter());
			TheShop.main(null);
		}
	}

	public <E> void addToCart(List<E> list, String file) throws IOException,
			ClassNotFoundException {
		Files f = Files.getInstance();

		for (int i = 0; i < shoppingCart.length; i++) {
			System.out.println("\nTo the main menu press EXIT");
			System.out
					.println("\nTo add the product to the shopping cart write his ID and press Enter\n\n========"
							+ "===========================================\n");

			idOfProduct = scen.next();
			Iterator<E> iter = list.iterator();
			while (iter.hasNext()) {
				Product product = (Product) iter.next();
				if (product.getId().equals(idOfProduct)) {

					shoppingCart[i] = product;

					System.out.printf(
							"\n=========================\n%s is added to the cart \n=="
									+ "=======================\n",
							shoppingCart[i].getBrand());

					chooseUnit(list, shoppingCart[i].getId());

					System.out
							.println("\n=========================\nThe quantity of the product  now is -->  "
									+ product.getAvailability());
					counter++;
					System.out
							.printf("\n---------------------\nYou have %d items added to the cart\n-----------------\n",
									1 + i);

				}
				f.saveFile(list, file);
				limitOfTheCart(idOfProduct, counter);
			}
		}
	}

	public <E> void chooseUnit(List<E> list, String id) {
		boolean check = true;
		Iterator<E> i = list.iterator();
		while (i.hasNext()) {

			Product product = (Product) i.next();
			if (product.getId().equals(id)) {

				do {
					System.out
							.println("Type 1 to buy a single piece\nType 2 to buy a box(10 piecec in the box)");

					Scanner sc = new Scanner(System.in);
					int in = sc.nextInt();
					switch (in) {
					case 1:
						System.out
								.println("Enter how many piece u want to buy");
						int piececCount = sc.nextInt();
						product.setAvailability(product.getAvailability()
								- piececCount);
						piececCounter += piececCount;

						if (getPiececCounter() > 1) {
							System.out.printf(
									"You add to the cart %d piecec from %s ",
									getPiececCounter(), product.getBrand());
						} else {
							System.out
									.printf("You add to the cart a single piece from %s ",
											product.getBrand());
						}
						break;
					case 2:
						System.out
								.println("Enter how many boxes u want to buy");
						int boxCount = sc.nextInt();
						product.setAvailability(product.getAvailability()
								- (boxCount * 10));
						boxCounter += boxCount;

						if (getBoxCounter() > 1) {
							System.out.printf(
									"You add to the cart %d boxes from %s ",
									getBoxCounter(), product.getBrand());
						} else {
							System.out.printf(
									"You add to the cart one box from %s ",
									product.getBrand());
						}
						break;
					default:
						System.out.println("Incorrect format");
						check = false;
						break;

					}
				} while (true);
			}
		}
	}

	public void addedItems(int piecec, int boxes) {
		System.out
				.printf("\n====================\nThis items was added to the "
						+ "shopping cart\n========================\nTotal of "
						+ "the added products --> %d piecec and %d boxes\n===================================\n",
						piecec, boxes);
		for (int i = 0; i < shoppingCart.length; i++) {
			if (shoppingCart[i] != null) {
				System.out.println(shoppingCart[i]);
				System.out.println("\n========================\n");
			}

		}
	}

	public Product[] getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(Product[] shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public int getNumberOfAddedProducts() {
		return numberOfAddedProducts;
	}

	public int getBoxCounter() {
		return boxCounter;
	}

	public int getPiececCounter() {
		return piececCounter;
	}

	public void setPiececCounter(int piececCounter) {
		this.piececCounter = piececCounter;
	}

}
