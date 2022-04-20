package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaUtilizatori;
import LantFarmacii.Model.Utilizator;
import LantFarmacii.ViewModel.VMAdministrator;

public class ButonStergereUtilizator implements Command{

    private String[] date;

    public ButonStergereUtilizator(VMAdministrator vm, String[] date){
        this.date = date;
    }

    @Override
    public void execute() {
        Utilizator u1 = new Utilizator(date[0], date[1], date[2], date[3], Integer.parseInt(date[4]));
        PersistentaUtilizatori.deleteFromDB(u1);
    }
}
