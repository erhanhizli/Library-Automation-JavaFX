package Admin;

import Conn.DBConnection;
import UserScreen.BookDetails;
import UserScreen.ShowAllBooks;
import UserScreen.UserDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_Book_Details_Controller {
    @FXML
    private TableColumn<BookDetails,String> book_id;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> book_title;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> author_id;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> category_id;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> book_language;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> book_quantity;
    @FXML
    private TableView<BookDetails> BookDetailsTable;

    Stage stage = new Stage();

    private DBConnection dc;
    public ObservableList<BookDetails> data;


    public void GoToAdminScreen(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Screen.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("Book Details");
        stage.setScene(scnUserLogin);
        stage.show();
    }
    public void ShowBookDetails(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from book_details ");

            while(resultSet.next())
            {
                //get string from db
                data.add(new UserScreen.BookDetails(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        book_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        book_title.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        author_id.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        category_id .setCellValueFactory(new PropertyValueFactory<>("category_id"));
        book_language.setCellValueFactory(new PropertyValueFactory<>("book_language"));
        book_quantity.setCellValueFactory(new PropertyValueFactory<>("book_quantity"));


        BookDetailsTable.setItems(null);
        BookDetailsTable.setItems(data);

    }

}