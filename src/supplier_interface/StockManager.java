package supplier_interface;

import domain.Game;
import domain.Observer;
import domain.Order;
import domain.OrderItem;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Bartosz Mirowski
 */
public class StockManager implements Observer{
    
    public List<Game> getGames(ObservableList<Game> data, TableView table) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            
            List<Game> listGames = entityManager.createQuery("SELECT p FROM Game p").getResultList();
            for (Game game : listGames) {
                data.add(game);
                table.setItems(data);
               
                if(game.getStock() <=22){
                    
                   
                    
                   game.setStock(10);
                   
                }
                

            }
           
           
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            
            entityManager.close();
            entityManagerFactory.close();
        }
        return null;
    }

    @Override
    public void update(Order order) {
        
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getItem().getStock() > 5) {
                orderGame(orderItem.getItem(), 20);
            }
        }
        
    }
    
        
    private void orderGame(Game item, int newStock) {
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
        } finally {
            entityManager.close();
        }        
    }
    
}

    
