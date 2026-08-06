package warenkorb;

import java.util.List;
import java.util.ArrayList;

public class Shop {
    private List<Warenkorb> warenkorbListe = new ArrayList<>();

    public Shop() {
    }

    public boolean warenkorbHinzufuegen(String name) {
        Warenkorb warenkorb = new Warenkorb(name);
        warenkorbListe.add(warenkorb);
        return true;
    }

    public boolean warenkorbLoeschen(int index) {
        warenkorbListe.remove(index);
        return true;
    }

    public Warenkorb getWarenkorb(int index) {
        return warenkorbListe.get(index);
    }
}
