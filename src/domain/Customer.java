package domain;

import java.util.ArrayList;
import java.util.List;

import domain.enumerations.Title;
import java.io.Serializable;

public class Customer implements Serializable {
  
//Variables
  private String email;
  private String password;
  private Title title;
  private String firstName;
  private String secondName; 
  private Address address;
  private String loyaltyAccount;
  private String telephoneNumber;
  private List<Order> orders;
  
//Constructors
  public Customer () {
    
  }
  
  public Customer (String email, String password, Title title, String firstName,
	  String secondName, Address address, String loyaltyAccount, String telephoneNumber) {
    this.email = email;
    this.password = password;
    this.title = title;
    this.firstName = firstName;
    this.secondName = secondName;
    this.address = address;
    this.loyaltyAccount = loyaltyAccount;
    this.telephoneNumber = telephoneNumber;
    this.orders = new ArrayList<>();
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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address billingAddress) {
    this.address = billingAddress;
  }

  public String getLoyaltyAccount() {
    return loyaltyAccount;
  }

  public void setLoyaltyAccount(String loyaltyAccount) {
    this.loyaltyAccount = loyaltyAccount;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
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