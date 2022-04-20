package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.View.ViewAngajat;
import LantFarmacii.ViewModel.VMAngajat;

import java.sql.SQLException;

public class ButonAfisare implements Command{

    public ButonAfisare(VMAngajat vm){}

    private static PersistentaProduse produse = new PersistentaProduse();

    @Override
    public void execute(){
        try{
            produse = PersistentaProduse.readFromDB();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        ViewAngajat view = new ViewAngajat();
        view.showTableDemo(produse);

    }
}
