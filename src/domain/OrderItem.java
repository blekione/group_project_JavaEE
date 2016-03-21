package domain;

public class OrderItem {
  
	private int itemId;
	//@ManyToOne relationship
	private Order itemOwner = null;
	private int quantity;
	//@OneToOne
	private Game item;
	
	public OrderItem() {}
	
	public OrderItem (int quantity, Game item){
		this.quantity = quantity;
		this.item = item;
	}
		
	public Game getItem() {return this.item;}
	
	public int getQuantity() {return this.quantity;}
	
	public void setQuantity(int quantity) { this.quantity = quantity;}
	
	public void setOwerOrder(Order order) {
		this.itemOwner = order;
	}
	
	/**
	 * Calculates amount of loyalty point earn for game purchase.
	 * Each £ is worth 1 point * multiplier:
	 * multipier:
	 * = 1 - when game is not on promotion
	 * = 2 - when game on double point promotion 
	 *  
	 * @return int
	 */
	public int getLoyaltyPoints() {
		return (int) (this.item.getPrice() * this.item.getPointMultiplier());
	}
 }
