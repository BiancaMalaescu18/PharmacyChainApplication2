package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Model.ProdusCuProducator;
import LantFarmacii.ViewModel.VMAngajat;

import java.time.LocalDate;

public class ButonStergereProdus implements Command{
    private String[] date;

    public ButonStergereProdus(VMAngajat vm, String[] s){
        this.date = s;
    }

    @Override
    public void execute() {
        ProdusCuProducator p1 = new ProdusCuProducator(date[0], Boolean.parseBoolean(date[1]), Integer.parseInt(date[2]), date[3], Double.parseDouble(date[4]), Integer.parseInt(date[5]), LocalDate.parse(date[6]));
        PersistentaProduse.deleteFromDB(p1);
    }
}
