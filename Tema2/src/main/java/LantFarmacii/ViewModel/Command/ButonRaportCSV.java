package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Raport.Rapoarte;
import LantFarmacii.ViewModel.VMAngajat;

import java.io.IOException;
import java.sql.SQLException;

public class ButonRaportCSV implements Command{

    public ButonRaportCSV(VMAngajat vm){}

    @Override
    public void execute() {
        try {
            PersistentaProduse produse = PersistentaProduse.readFromDB();
            try {
                Rapoarte.raportCSV(produse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
