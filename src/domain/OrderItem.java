package domain;

public class OrderItem {
  
	private int positionNumber;
	private int quantity;
	private Game item;
	
	public OrderItem (int positionNumber, int quantity, Game item){
		this.positionNumber = positionNumber;
		this.quantity = quantity;
		this.item = item;
	}
	
	public Integer getId() { return this.positionNumber; }
}
