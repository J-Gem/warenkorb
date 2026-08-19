package warenkorb;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

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
    private Label labelNettopreis;

    @FXML
    private Label labelBruttopreis;

    @FXML
    private Label labelArtikelanzahl;

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
        if (WKInput.getText() != "" && this.checkWarenkorb(WKInput.getText())) {
            this.myShop.warenkorbHinzufuegen(WKInput.getText());
            updateList();
            WKInput.clear();
        }
    }

    @FXML
    private void WKLoeschen() {
        if (this.myShop.anzahlWarenkorbe() > 0) {
            this.myShop.warenkorbLoeschen(WKListe.getSelectionModel().getSelectedIndex());
            updateList();
            if (this.myShop.anzahlWarenkorbe() == 0)
                AKTable.getItems().clear();
        }
    }

    @FXML
    public void updateTable() {
        AKTable.getItems().setAll(this.myWarenkorb.getArtikelListe());
        this.setNettopreis();
        this.setBruttopreis();
        this.setAnzahl();
    }

    @FXML
    private void AKHinzufuegen() {
        if (this.checkArtikelVoll() && checkArtikel((Integer.parseInt(InputAKnummer.getText())))) {
            this.myWarenkorb.artikelHinzufuegen(Integer.parseInt(InputAKnummer.getText()), InputAKname.getText(),
                    Float.parseFloat(InputAKPreis.getText()), Float.parseFloat(InputAKsteuersatz.getText()));
            updateTable();
            InputAKnummer.clear();
            InputAKname.clear();
            InputAKPreis.clear();
            InputAKsteuersatz.clear();

        }
    }

    @FXML
    private void AKLoeschen() {
        if (this.myShop.anzahlWarenkorbe() != 0 && this.myWarenkorb.gesamtAnzahlBerechnen() != 0
                && AKTable.getSelectionModel().getSelectedIndex() > -1) {
            this.myWarenkorb.artikelLoeschen(AKTable.getSelectionModel().getSelectedIndex());
            updateTable();
        }
    }

    public boolean checkWarenkorb(String name) {
        for (int i = 0; i < this.myShop.anzahlWarenkorbe(); i++) {
            if (this.myShop.getWarenkorb(i).getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkArtikelVoll() {
        if (InputAKnummer.getText() != "" && InputAKname.getText() != "" && InputAKPreis.getText() != ""
                && InputAKsteuersatz.getText() != "" && this.myShop.anzahlWarenkorbe() != 0) {
            if (InputAKnummer.getText().matches("\\d+") && InputAKPreis.getText().matches("\\d+(\\.\\d+)?")
                    && InputAKsteuersatz.getText().matches("\\d+(\\.\\d+)?")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkArtikel(int nummer) {
        for (int i = 0; i < this.myWarenkorb.gesamtAnzahlBerechnen(); i++) {
            if (this.myWarenkorb.getArtikel(i).getArtikelnummer() == nummer) {
                return false;
            }
        }
        return true;
    }

    public void setNettopreis() {
        labelNettopreis.setText(String.format("%.2f", this.myWarenkorb.gesamtNettopreisBerechnen()));
    }

    public void setBruttopreis() {
        labelBruttopreis.setText(String.format("%.2f", this.myWarenkorb.gesamtpreisBerechnen()));
    }

    public void setAnzahl() {
        labelArtikelanzahl.setText(String.valueOf(this.myWarenkorb.gesamtAnzahlBerechnen()));
    }
}
