package warenkorb;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    private Shop MyShop;
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage Stage) {
        MyShop = new Shop();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/warenkorb/main.fxml"));
            loader.setController(new Controller(MyShop));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage.setScene(scene);
            Stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
