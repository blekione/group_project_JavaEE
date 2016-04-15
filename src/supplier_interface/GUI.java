/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier_interface;

import domain.Game;
import java.sql.SQLException;
import javafx.application.Application;
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
import static javafx.application.Application.launch;

/**
 *
 * @author Admin
 */
public class GUI extends Application {
    
    //instantiation
    StockManager stockManager = new StockManager();
    Button btn;

    @Override
    public void start(Stage primaryStage) throws SQLException {
        ObservableList<Game> data = FXCollections.observableArrayList();
        TableView<Game> table = new TableView<>();
        HBox hBox = new HBox();
   
        GridPane root = new GridPane();
        
        stockManager.getGames(data, table);
        
        
        /* Graphical User Interface */
        
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

        btn = new Button("Refresh");
        btn.setOnAction(e -> stockManager.getGame(data, table));
        
        hBox.getChildren().add(table);

        GridPane.setConstraints(hBox, 0, 0);
        GridPane.setConstraints(btn, 0, 1);
        root.getChildren().addAll(hBox, btn);
        root.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(root, 600, 300);
        scene.getStylesheets().add(GUI.class.getResource("supplier-interface-main.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Supply Stock");
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
