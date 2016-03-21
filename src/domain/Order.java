package domain;

import java.util.List;

public class Order {

	private int orderId;
	// @OneToMany(cascade=ALL, mappedBy="itemOwner")
	private List<OrderItem> orderItems;
	private Customer orderOwner;

	public Order() {}

	public double getTotalPrice() {
		return 0.0;
	}
	
	public int getTotalPoints() {
		
		int points = 0;
		for (OrderItem item: orderItems) {
		points += item.getLoyaltyPoints();
		}
		return points;
	}

	public void moveItemsFromCart(List<OrderItem> items) {
		this.orderItems = items;		
	}
	
	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

}
