package Admin;

import Conn.DBConnection;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_User_Details_Controller {
    @FXML
    private TableColumn<UserDetails,String> user_id;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_name;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_lastname;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_sex;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_password;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_phone;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_email;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_recovery;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> username;
    @FXML
    private TableView<UserDetails> UserDetailsTable;


    Stage stage = new Stage();
    private DBConnection dc;
    public ObservableList<UserDetails> data;

    public void GoToAdminScreen(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Screen.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("User Details");
        stage.setScene(scnUserLogin);
        stage.show();
    }

    public void ShowUserDetails(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from user_info ");

            while(resultSet.next())
            {
                //get string from db
                data.add(new UserScreen.UserDetails(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        user_lastname.setCellValueFactory(new PropertyValueFactory<>("user_lastname"));
        user_sex .setCellValueFactory(new PropertyValueFactory<>("user_sex"));
        user_password.setCellValueFactory(new PropertyValueFactory<>("user_password"));
        user_phone.setCellValueFactory(new PropertyValueFactory<>("user_phone"));
        user_email.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        user_recovery.setCellValueFactory(new PropertyValueFactory<>("user_recovery"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));



        UserDetailsTable.setItems(null);
        UserDetailsTable.setItems(data);

    }

}