package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
	
	private Map<String, OrderItem> cartItems;
	private double total;

	public ShoppingCart() {
		this.cartItems = new HashMap<>();
		total = 0.0;
	}
	
	public void addNewItem(OrderItem orderItem) {
		this.cartItems.put(orderItem.getItem().getBarcodeGS1() , orderItem);
	}
	
	public void removeItem(String itemBarcode) {
		this.cartItems.remove(itemBarcode);
	}
	
	public void clearCart() {
		this.cartItems = new HashMap<>();
	}
	
	public OrderItem findItem(String barcode) {
		 OrderItem orderItem = null;
		if (cartItems.get(barcode) != null) {
			orderItem = cartItems.get(barcode);
		}
		return orderItem;
	}
 
	public int getSize() { return cartItems.size();}
	
}
