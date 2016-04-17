package domain;

import domain.enumerations.Platform;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import loyalty_scheme.LoyaltyScheme;
import payment.BankAccount;

public class Database {

  /**
   * A method to fetch all games currently stored on the database, regardless of
   * their platform or any other criteria.
   *
   * @return a list containing all games on the database.
   * @throws NullPointerException
   */
  public List<Game> retrieveAll() {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g",
              Game.class);
      return query.getResultList();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

//RETRIEVE ALL GAMES BY PLATFORM (NOTE: PLATFORM STRING IS CASE SENSITIVE)  
  public List<Game> retrievePlatform(String platform) {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g "
              + "WHERE g.platform = :platform", Game.class);
      query.setParameter("platform", Platform.valueOf(platform));
      return query.getResultList();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

//RETRIEVE ALL DISCOUNTED GAMES  
  public List<Game> retrieveDiscounts() {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g "
              + "WHERE g.discount > 0", Game.class);
      return query.getResultList();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

//RETRIEVE DETAILS OF A SINGLE GAME  
  public Game retrieveGame(String barcode) {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g "
              + "WHERE g.barcodeGS1 = :barcode", Game.class);
      query.setParameter("barcode", barcode);
      return query.getSingleResult();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

  public LoyaltyScheme retrieveLoyaltyAccount(String accountNo) {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    LoyaltyScheme loyaltyAccount = null;
    try {
      loyaltyAccount = em.find(LoyaltyScheme.class, accountNo);
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return loyaltyAccount;
  }

  public void updateLoyaltyPoints(String accountNo, int points) {
    LoyaltyScheme loyaltyAccount = null;
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    try {
      loyaltyAccount = em.find(LoyaltyScheme.class, accountNo);
      em.getTransaction().begin();
      loyaltyAccount.setLoyaltyPoints(points);
      em.getTransaction().commit();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
  }

  public boolean persist(Object object) {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.persist(object);
      em.getTransaction().commit();
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return false;
  }

  public List<OrderItem> returnOrders() {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      TypedQuery<OrderItem> query = em.createQuery("SELECT o FROM OrderItem o",
              OrderItem.class);
      return query.getResultList();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

  public Customer checkLogin(String email) {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      TypedQuery<Customer> query = em.createQuery(""
              + "SELECT c "
              + "FROM Customer c "
              + "WHERE c.email = :email",
              Customer.class);
      query.setParameter("email", email);
      return query.getSingleResult();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

  public boolean addPromotion(String barcode, double discount,
          double pointMult) {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    Game game = em.find(Game.class, barcode);
    em.getTransaction().begin();
    try {
      game.setDiscount(discount);
      game.setPointMultiplier(pointMult);
      em.getTransaction().commit();
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
    return false;
  }

  public void updateGameStock(Game item, int newStock) {
    Game orderedGame = null;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPU");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      orderedGame = entityManager.find(Game.class, item.getBarcodeGS1());
      entityManager.getTransaction().begin();
      orderedGame.setStock(newStock);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      entityManager.getTransaction().rollback();
    } finally {
      entityManager.close();
    }
  }

  public Game findGame(String gameBarcode) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    Game game = null;
    try {
      em.getTransaction().begin();
      game = em.find(Game.class, gameBarcode);
    } catch (Exception ex) {
      ex.printStackTrace();
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
    return game;
  }

  public void updateGame(Game game) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      Game gameOld = em.find(Game.class, game.getBarcodeGS1());
      gameOld.setName(game.getName());
      gameOld.setDescription(game.getDescription());
      gameOld.setGenre(game.getGenre());
      gameOld.setPlatform(game.getPlatform());
      gameOld.setPrice(game.getPrice());
      gameOld.setDiscount(game.getDiscount());
      gameOld.setPointMultiplier(game.getPointMultiplier());
      em.getTransaction().commit();
    } catch (Exception ex) {
      ex.printStackTrace();
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }
  
  public List<Customer> retrieveCustomers() {
    EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    List<Customer> customers = null;
    try {
      em.getTransaction().begin();
      TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c",
              Customer.class);
      customers = query.getResultList();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return customers;
  }
  
  public  BankAccount getBankAccount(String cardNumber) {
	    EntityManagerFactory emf = javax.persistence.Persistence
	            .createEntityManagerFactory("JPU");
	    EntityManager em = emf.createEntityManager();
	    BankAccount account = null;
	    try {
	      em.getTransaction().begin();
	      account = em.find(BankAccount.class, cardNumber);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	em.close();
	    }
	    return account;
	  }

  public void updateBankAccountBalance(String cardNumber, double newBalance) {
	EntityManagerFactory emf = javax.persistence.Persistence
            .createEntityManagerFactory("JPU");
    EntityManager em = emf.createEntityManager();
    BankAccount account = null;
    try {
      em.getTransaction().begin();
      account = em.find(BankAccount.class, cardNumber);
      account.setBalance(newBalance);
      em.getTransaction().commit();
    } catch (Exception ex) {
    	ex.printStackTrace();
    } finally {
    	em.close();
    }	
	
  }

}
