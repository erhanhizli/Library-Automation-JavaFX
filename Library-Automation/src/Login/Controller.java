package Login;

import Conn.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    private DBConnection dc;

    public Label isConnected = new Label();
    public TextField txtUsername = new TextField();
    public PasswordField txtPassword = new PasswordField();
    public TextField txtAdminName = new TextField();
    public TextField txtAdminPassword = new TextField();

    private Stage stage = new Stage();

    public void GoUserLoginPage(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("User Login");
        stage.setScene(scnUserLogin);
        stage.show();
    }
    public void GoAdminLoginPage (ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("Admin_Login.fxml"));
        Scene scnAdminLogin = new Scene(p1);

        stage.setTitle("Admin Login");
        stage.setScene(scnAdminLogin);
        stage.show();
    }
    public void GoBack (ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scnLogin = new Scene(p1);

        stage.setTitle("Library Automation");
        stage.setScene(scnLogin);
        stage.show();
    }
    public void GoForgot(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("Forgot_Password.fxml"));
        Scene scnForgot = new Scene(p1);

        stage.setTitle("Password Recovery");
        stage.setScene(scnForgot);
        stage.show();
    }
    public void GoSignin(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("User_Signin.fxml"));
        Scene scnSignin = new Scene(p1);

        stage.setTitle("User Registration");
        stage.setScene(scnSignin);
        stage.show();
    }

    public void Login(ActionEvent event) throws IOException{

        if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Empty Fields!");
            alert.setContentText("Please enter all necessary information!");

            alert.showAndWait();
        }

        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user_info WHERE username = '" + txtUsername.getText() + "' AND user_password = '" + txtPassword.getText() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next())
            {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent p1 = FXMLLoader.load(getClass().getResource("../UserScreen/UserMainScreen.fxml"));
                Scene scnSignin = new Scene(p1);

                stage.setTitle("User Screen");
                stage.setScene(scnSignin);
                stage.show();

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("Login Failed!");
                alert.setContentText("Please check your username or password!");

                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void LoginAdmin(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + txtAdminName.getText() + "' AND password = '" + txtAdminPassword.getText() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                isConnected.setText("Connected");

            } else {
                isConnected.setText("Not Connected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
