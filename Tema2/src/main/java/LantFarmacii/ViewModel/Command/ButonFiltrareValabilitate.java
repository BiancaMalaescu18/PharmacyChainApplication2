package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.View.ViewAngajat;
import LantFarmacii.ViewModel.VMAngajat;

import java.sql.SQLException;
import java.time.LocalDate;

public class ButonFiltrareValabilitate implements Command{

    private String search;

    public ButonFiltrareValabilitate(VMAngajat vm, String search){
        this.search = search;
    }

    @Override
    public void execute() {
        PersistentaProduse produse = null;
        try {
            produse = PersistentaProduse.readFromDB();

            produse.getProduse().removeIf(p -> !p.getValabilitate().toString().equals(search));
            ViewAngajat view = new ViewAngajat();
            view.showTableDemo(produse);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
