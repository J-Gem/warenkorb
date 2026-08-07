package warenkorb;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    private Shop myShop;
    private Warenkorb myWarenkorb;

    public Controller(Shop myShop) {
        this.myShop = myShop;
    }

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
    private TableView<Artikel> AKTable;

    @FXML
    private TableColumn<Artikel, Integer> ArtikelnummerColumn;
    @FXML
    private TableColumn<Artikel, String> ArtikelnameColumn;
    @FXML
    private TableColumn<Artikel, Float> steuersatzColumn;
    @FXML
    private TableColumn<Artikel, Float> preisColumn;

    @FXML
    public void updateList() {
        WKListe.getItems().clear();
        for (int i = 0; i < this.myShop.anzahlWarenkorbe(); i++) {
            WKListe.getItems().add(this.myShop.getWarenkorb(i).getName());
        }
        if (WKListe.getItems().size() > 0) {
            WKListe.getSelectionModel().selectLast();

            System.out.println("Ausgewählt: " + WKListe.getSelectionModel().getSelectedIndex());
            System.out.println("Shop Anzahl: " + myShop.anzahlWarenkorbe());
        }
    }

    @FXML
    public void initialize() {
        WKListe.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == -1) {
                return;
            }
            this.myWarenkorb = this.myShop.getWarenkorb((int) (newValue));
            updateTable();
        });

        ArtikelnummerColumn.setCellValueFactory(new PropertyValueFactory<>("artikelnummer"));
        ArtikelnameColumn.setCellValueFactory(new PropertyValueFactory<>("beschreibung"));
        preisColumn.setCellValueFactory(new PropertyValueFactory<>("nettopreis"));
        steuersatzColumn.setCellValueFactory(new PropertyValueFactory<>("steuersatz"));
    }

    @FXML
    private void WKHinzufuegen() {
        this.myShop.warenkorbHinzufuegen(WKInput.getText());
        updateList();
        WKInput.clear();
    }

    @FXML
    private void WKLoeschen() {
        this.myShop.warenkorbLoeschen(WKListe.getSelectionModel().getSelectedIndex());
        updateList();
    }

    @FXML
    public void updateTable() {
        AKTable.getItems().setAll(this.myWarenkorb.getArtikelListe());
    }

    @FXML
    private void AKHinzufuegen() {
        this.myWarenkorb.artikelHinzufuegen(Integer.parseInt(InputAKnummer.getText()), InputAKname.getText(),
                Float.parseFloat(InputAKPreis.getText()), Float.parseFloat(InputAKsteuersatz.getText()));
        updateTable();
        InputAKnummer.clear();
        InputAKname.clear();
        InputAKPreis.clear();
        InputAKsteuersatz.clear();
    }

    @FXML
    private void AKLoeschen() {
        this.myWarenkorb.artikelLoeschen(AKTable.getSelectionModel().getSelectedIndex());
        updateTable();
    }
}
