package domain;

import java.util.List;

import domain.enumerations.Title;

public class Customer {
  
//Variables
  private String email;
  private String password;
  private Title title;
  private String firstName;
  private String secondName; 
  private Address billingAddress;
  private Address deliveryAddress;
  private String loyaltyAccount;
  private int telephoneNumber;
  private String ipAddress;
  private List<Order> orders;
  
//Constructors
  public Customer () {
    
  }
  
  public Customer (String email, String password, Title title, String firstName,
	  String secondName, Address billingAddress, Address deliveryAddress,
	  String loyaltyAccount, int telephoneNumber, String ipAddress) {
    this.email = email;
    this.password = password;
    this.title = title;
    this.firstName = firstName;
    this.secondName = secondName;
    this.billingAddress = billingAddress;
    this.deliveryAddress = deliveryAddress;
    this.loyaltyAccount = loyaltyAccount;
    this.telephoneNumber = telephoneNumber;
    this.ipAddress = ipAddress;
  }
  
//Getters and Setters
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public Address getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(Address deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getLoyaltyAccount() {
    return loyaltyAccount;
  }

  public void setLoyaltyAccount(String loyaltyAccount) {
    this.loyaltyAccount = loyaltyAccount;
  }

  public int getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(int telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public Title getTitle() {
    return title;
  }

  public void setTitle(Title title) {
    this.title = title;
  }
  
  public void addOrder(Order order) {
	  this.orders.add(order);
  }
  
  public List<Order> getOrders() {
	  return this.orders;
  }
}