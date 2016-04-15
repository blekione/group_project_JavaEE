package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

  @Id
  private int orderId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemOwner")
  private List<OrderItem> orderItems;

  private Customer orderOwner;

  public Order() {
  }
  
  public Order(List<OrderItem> orderItems, Customer orderOwner) {
	  this.orderItems = orderItems;
	  this.orderOwner = orderOwner;
  }
  
  
  public Order(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public double getTotalPrice() {
    return 0.0;
  }

  public int getTotalPoints() {

    int points = 0;
    for (OrderItem item : orderItems) {
    	System.out.println("points from game: " + item.getLoyaltyPoints());
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

  public Customer getOrderOwner() {
        return orderOwner;
    }
}
