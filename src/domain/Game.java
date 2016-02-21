package domain;

import domain.enumerations.Genre;
import domain.enumerations.Platform;

public class Game {
  
//Variables  
  private String name;
  private String description;
  private int stock;
  private String barcodeGS1;
  private double price;
  private double pointMultiplier;
  private String pictureLocation;
  private double discount;
  private Genre genre;
  private Platform platform;
 
//Constructors  
  public Game () {}
  
  public Game (String name, String description, int stock, String barcodeGS1, 
	  double price, double pointMultiplier, String pictureLocation,
	  double discount, Genre genre, Platform platform) {
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.barcodeGS1 = barcodeGS1;
    this.price = price;
    this.pointMultiplier = pointMultiplier;
    this.pictureLocation = pictureLocation;
    this.discount = discount;
    this.genre = genre;
    this.platform = platform;
  }
  
//Getters and Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getBarcodeGS1() {
    return barcodeGS1;
  }

  public void setBarcodeGS1(String barcodeGS1) {
    this.barcodeGS1 = barcodeGS1;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getPointMultiplier() {
    return pointMultiplier;
  }

  public void setPointMultiplier(double pointMultiplier) {
    this.pointMultiplier = pointMultiplier;
  }

  public String getPictureLocation() {
    return pictureLocation;
  }

  public void setPictureLocation(String pictureLocation) {
    this.pictureLocation = pictureLocation;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  } 
  
  public double getDiscountedPrice() {
	  return (double) Math.round((getPrice() - getPrice()*getDiscount()* 0.01)* 100d) / 100d;
  }
  
}