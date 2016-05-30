package nl.hhs.MySQLJavaHalo;

/**
 * @author Hugo van Elswijk (15090558)
 * @author Matthew van der Toorn Vrijthoff (15042758)
 * @author Gianfranco Lopez (15102211)
 * @author Rene Uhliar (14036738)
 * @author Len van Kampen (14063711)
 * @version 1.0
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/logon.fxml"));
        primaryStage.setTitle("De Haagse Hogeschool - HALO");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
