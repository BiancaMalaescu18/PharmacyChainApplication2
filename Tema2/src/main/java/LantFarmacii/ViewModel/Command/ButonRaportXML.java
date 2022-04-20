package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Raport.Rapoarte;
import LantFarmacii.ViewModel.VMAngajat;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

public class ButonRaportXML implements Command{

    public ButonRaportXML(VMAngajat vm){}

    @Override
    public void execute() {
        try {
            PersistentaProduse produse = PersistentaProduse.readFromDB();
            try {
                Rapoarte.raportXML(produse);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
