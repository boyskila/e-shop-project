package com.team.eshop.products;

import java.io.Serializable;

public class Jackets extends Product implements Serializable {
	public Jackets() {

	}

	public Jackets(String id, String brand, String model, String color,
			String size, double pricePerPiece, double pricePerBox,
			int availability, boolean isSelected, int buffer,double choosenPrice) {
		super(id, brand, model, color, size, pricePerPiece, pricePerBox,
				availability, isSelected, buffer, choosenPrice);

	}

	@Override
	public String toString() {
		return "Id -->" + getId() + "\nbrand --> " + getBrand()
				+ "\ncolor --> " + getColor() + "\nsize -->" + getSize()
				+ "\nprice per piece --> " + getPricePerPiece()
				+ "\nprice per box --> " + getPricePerBox()
				+ "\navailability --> " + getAvailability() + "\nChoosen Price --> " + getChoosenPrice();
	}
}
