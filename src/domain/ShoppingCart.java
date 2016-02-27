package domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private List<OrderItem> cartItems;
	private double total;

	public ShoppingCart() {
		this.cartItems = new ArrayList<>();
		total = 0.0;
	}
	
	public void addNewItem(OrderItem orderItem) {
		this.cartItems.add(orderItem);
	}
	
	public void removeItem(int positionNumber) {
		this.cartItems.remove(positionNumber);
	}
	
	public void clearCart() {
		this.cartItems = new ArrayList<>();
	}
 
}
