package domain;

import domain.enumerations.Platform;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class Database {

//RETRIEVE ALL GAMES  
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
  public Game retrieveGame (String barcode) {
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
}
