/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier_interface;

import domain.Game;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static javafx.application.Application.launch;
import javafx.scene.control.TablePosition;
import javax.mail.FetchProfile.Item;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class GUI extends Application {

    Button btn;
    
    @Override
    public void start(Stage primaryStage) throws SQLException {
        ObservableList<Game> data = FXCollections.observableArrayList();
        TableView<Game> table = new TableView<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Game> listGames = entityManager.createQuery("SELECT p FROM Game p").getResultList();
        List<Game> getStockNumber = entityManager.createQuery("SELECT p.stock FROM Game p").getResultList();
        Integer[] array = getStockNumber.toArray (new Integer [getStockNumber.size()]);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        for (Game game : listGames) {
            data.add(game);
        }
        table.setItems(data);

       // Object[] array = getStockNumber.toArray();

        System.out.println(table.getItems());
        //Create Objects
        HBox hBox = new HBox();
        GridPane root = new GridPane();

        //Column 1
        TableColumn column1 = new TableColumn("Game");
        column1.setMinWidth(400);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Column 2
        TableColumn column2 = new TableColumn<>("Stock");
        column2.setMinWidth(200);
        column2.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //Append columns to table
        table.getColumns().addAll(column1, column2);

        btn = new Button("Supply Stock");

        hBox.getChildren().add(table);

        table.setItems(data);

        //Retrieve data from  the table
        GridPane.setConstraints(hBox, 0, 0);
        GridPane.setConstraints(btn, 0, 1);
        root.getChildren().addAll(hBox, btn);
        root.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(root, 600, 300);
        scene.getStylesheets().add(GUI.class.getResource("supplier-interface-main.css").toExternalForm());

        primaryStage.setTitle("Supplier Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {

        launch(args);
    }

}
