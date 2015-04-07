package com.team.eshop.products;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@SuppressWarnings("serial")
public class Pants extends Product implements Serializable {
	Pants() {

	}

	public Pants(String id, String brand, String model, String color,
			String size, double pricePerPiece, double pricePerBox,
			int availability, boolean isSelected, int buffer,
			double choosenPrice) {

		super(id, brand, model, color, size, pricePerPiece, pricePerBox,
				availability, isSelected, buffer, choosenPrice);

	}

	@Override
	public String toString() {
		return "Id -->" + getId() + "\nbrand --> " + getBrand()
				+ "\nmodel --> " + getModel() + "\ncolor --> " + getColor()
				+ "\nsize -->" + getSize() + "\nprice per piece --> "
				+ getPricePerPiece() + "\nprice per box --> "
				+ getPricePerBox() + "\navailability --> " + getAvailability()
				+ "\nChoosen Price --> " + getChoosenPrice();
	}

}
