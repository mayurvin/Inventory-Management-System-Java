package com.talem;

public class Item {
	
	private String name;
	private int quantity;

	public Item(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return this.name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void removeAmount(int amount) {
		this.quantity -= amount;
	}

	public void addAmount(int amount) {
		this.quantity += amount;
	}
}
