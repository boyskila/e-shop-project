package com.team.eshop.products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shoes extends Product implements Serializable {

	public Shoes() {

	}

	public Shoes(String id, String brand, String model, String color,
			String size, double pricePerPiece, double pricePerBox,
			int availability, boolean isSelected, int buffer,
			double choosenPrice) {
		super(id, brand, model, color, size, pricePerPiece, pricePerBox,
				availability, isSelected, buffer, choosenPrice);

	}

}
