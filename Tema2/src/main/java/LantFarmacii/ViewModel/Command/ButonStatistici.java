package LantFarmacii.ViewModel.Command;

import LantFarmacii.Raport.RaportDisponibilitate;
import LantFarmacii.Raport.RaportProducator;
import LantFarmacii.Raport.RaportValabilitate;
import LantFarmacii.ViewModel.VMAngajat;

import java.sql.SQLException;

public class ButonStatistici implements Command{

    public ButonStatistici(VMAngajat vm){}

    @Override
    public void execute() {
        try {
            RaportDisponibilitate.creare();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            RaportValabilitate.creare();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            RaportProducator.creare();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
