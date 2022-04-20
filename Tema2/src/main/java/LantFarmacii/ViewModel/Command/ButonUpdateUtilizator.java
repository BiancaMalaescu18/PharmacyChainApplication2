package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaUtilizatori;
import LantFarmacii.Model.Utilizator;
import LantFarmacii.ViewModel.VMAdministrator;
import LantFarmacii.ViewModel.VMAngajat;

import java.util.UUID;

public class ButonUpdateUtilizator implements Command{

    private String rol;
    private String username;
    private String parola;
    private String nume;
    private int  id_farmacie;
    private String[] date;

    public ButonUpdateUtilizator(VMAdministrator vm, String rol, String username, String parola, String nume, int id_farmacie , String[] date){
        this.rol = rol;
        this.username = username;
        this.parola = parola;
        this.nume = nume;
        this.id_farmacie = id_farmacie;
        this.date = date;
    }


    @Override
    public void execute() {
        Utilizator u1 = new Utilizator(date[0], date[1], date[2], date[3], Integer.parseInt(date[4]));
        Utilizator u2 = new Utilizator(rol, username, parola, nume, id_farmacie);
        PersistentaUtilizatori.updateDB(u1, u2);

    }
}
