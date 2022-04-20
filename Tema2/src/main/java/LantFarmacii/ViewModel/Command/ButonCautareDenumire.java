package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.View.ViewAngajat;
import LantFarmacii.ViewModel.VMAngajat;

import java.sql.SQLException;

public class ButonCautareDenumire implements Command{

    private String search;

    public ButonCautareDenumire(VMAngajat vm, String search){
        this.search = search;
    }

    @Override
    public void execute() {
        PersistentaProduse produse = null;
        try {
            produse = PersistentaProduse.readFromDB();
            produse.getProduse().removeIf(p -> !p.getNume().equals(search));
            ViewAngajat view = new ViewAngajat();
            view.showTableDemo(produse);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
