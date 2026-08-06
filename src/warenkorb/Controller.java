package warenkorb;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private ListView<String> WKListe;

    @FXML
    private TextField WKInput;

    @FXML
    private Button btnWKHinzufuegen;

    @FXML
    private Button btnWKLoeschen;


    @FXML
    private TextField InputAKnummer;

    @FXML
    private TextField InputAKname;

    @FXML
    private TextField InputAKsteuersatz;

    @FXML
    private TextField InputAKPreis;

    @FXML
    public void initialize(){
        WKListe.getItems().add("Warenkorb 1");
        WKListe.getItems().add("Warenkorb 2");
        WKListe.getItems().add("Warenkorb 3");

        WKListe.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Neu ausgewählt: " + newValue);
        });
    }


}
