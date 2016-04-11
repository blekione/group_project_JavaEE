package domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERITEMS")
public class OrderItem implements Serializable {

  @Id
  private int itemId;

  @ManyToOne
  private Order itemOwner = null;

  private int quantity;

  @OneToOne (cascade=CascadeType.PERSIST)
  private Game item;

  public OrderItem() {
  }

  public OrderItem(int quantity, Game item) {
    this.quantity = quantity;
    this.item = item;
  }

  public Game getItem() {
    return this.item;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setOrderOwner(Order order) {
    this.itemOwner = order;
  }

  /**
   * Calculates amount of loyalty point earn for game purchase. Each £ is worth
   * 1 point * multiplier: multipier: = 1 - when game is not on promotion = 2 -
   * when game on double point promotion
   *
   * @return int
   */
  public int getLoyaltyPoints() {
    return (int) (this.item.getPrice() * this.item.getPointMultiplier());
  }
}
