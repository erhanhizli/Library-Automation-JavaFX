package Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_Login_Controller {
    Stage stage = new Stage();

    public TextField txtAdminName;
    public TextField txtAdminPassword;

    public void GoBack(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        Scene scnAdminLogin = new Scene(p1);

        stage.setTitle("Admin Login");
        stage.setScene(scnAdminLogin);
        stage.show();
    }

    public void LoginAdmin(ActionEvent event) throws IOException
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM admin_detail WHERE admin_name = '" + txtAdminName.getText() + "' AND admin_password = '" + txtAdminPassword.getText() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Screen.fxml"));
                Scene scnAdminLogin = new Scene(p1);

                stage.setTitle("Admin Screen");
                stage.setScene(scnAdminLogin);
                stage.show();



            } else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}