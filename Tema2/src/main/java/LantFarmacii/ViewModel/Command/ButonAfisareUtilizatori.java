package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Model.Persistenta.PersistentaUtilizatori;
import LantFarmacii.View.ViewAdministrator;
import LantFarmacii.View.ViewAngajat;
import LantFarmacii.ViewModel.VMAdministrator;

import java.sql.SQLException;

public class ButonAfisareUtilizatori implements Command{

    public ButonAfisareUtilizatori (VMAdministrator vm){}

    private static PersistentaUtilizatori utilizatori = new PersistentaUtilizatori();

    @Override
    public void execute() {

        try{
            utilizatori = PersistentaUtilizatori.readFromDB();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        ViewAdministrator view = new ViewAdministrator();
        view.showTableDemo(utilizatori);

    }
}
