package UserScreen;

import Conn.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

public class MyProfile_Controller {
    @FXML
    private TableColumn<UserScreen.UserDetails,String> UserID;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> Name;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> Lastname;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> Sex;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> Password;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> Phone;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> E_Mail;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> RecoveryAnswer;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> Username;
    @FXML
    private TableView<UserScreen.UserDetails> TableProfile;


    @FXML
    public Button btnUpdateScreen;
    @FXML
    public Button btnbacktomain;
    private DBConnection dc;
    public ObservableList<UserScreen.UserDetails>data;
    public Label Lblusernamemyprofile;
    public Label lbluserupdate;
    Stage stage = new Stage();
    Stage stage2 =new Stage();


    public void ShowMyInformation(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.connect();


        try {
            data = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from user_info where username='"+Lblusernamemyprofile.getText()+"'");

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
        UserID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        Lastname.setCellValueFactory(new PropertyValueFactory<>("user_lastname"));
        Sex.setCellValueFactory(new PropertyValueFactory<>("user_sex"));
        Password.setCellValueFactory(new PropertyValueFactory<>("user_password"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("user_phone"));
        E_Mail.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        RecoveryAnswer.setCellValueFactory(new PropertyValueFactory<>("user_recovery"));
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));



        TableProfile.setItems(null);
        TableProfile.setItems(data);
    }
    public void setLblusernamemyprofile(String text){

        Lblusernamemyprofile.setText(text);
    }

    public void setlbluserupdateback(String text)
    {
        Lblusernamemyprofile.setText(text);
    }
    public void backtomain (ActionEvent event)throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader2=new FXMLLoader(getClass().getResource("UserMainScreen.fxml"));
        Parent root2 = loader2.load();
        User_Screen_Controller thirdController = loader2.getController();
        thirdController.setlblusernamebacktomain(Lblusernamemyprofile.getText());

        stage2.setTitle("User Screen");
        Scene scene2 = new Scene(root2);
        stage2.setScene(scene2);
        stage2.show();
    }
    public void  GoToUpdateProfile(ActionEvent event)throws IOException
    {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("UpdateProfile.fxml"));

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = loader.load();
        Update_User_Controller secondController = loader.getController();
        secondController.setLblusernameupdatescreen(Lblusernamemyprofile.getText());

        stage.setTitle("Update Screen");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}