package domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
  
//Variables
  private String houseNumber;
  private String street;
  private String city;
  private String county;
  private String postcode;
  
//Constructors
  public Address () {
    
  }
  
  public Address (String houseNumber, String street, String city,
	  String county, String postcode) {
    this.houseNumber = houseNumber;
    this.street = street;
    this.city = city;
    this.county = county;
    this.postcode = postcode;
  }
  
//Getters and Setters
  public String getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }  
  
}
