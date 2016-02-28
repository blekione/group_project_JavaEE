package domain;

public class OrderItem {
  
	private int quantity;
	private Game item;
	
	public OrderItem (int quantity, Game item){
		this.quantity = quantity;
		this.item = item;
	}
		
	public Game getItem() {return this.item;}
	
	public int getQuantity() {return this.quantity;}
	
	public void setQuantity(int quantity) { this.quantity = quantity;}
 }
