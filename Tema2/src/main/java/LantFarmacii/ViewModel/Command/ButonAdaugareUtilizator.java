package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaUtilizatori;
import LantFarmacii.Model.Utilizator;
import LantFarmacii.ViewModel.VMAdministrator;

public class ButonAdaugareUtilizator implements Command{

    private String rol;
    private String username;
    private String parola;
    private String nume;
    private int  id_farmacie;

    public ButonAdaugareUtilizator(VMAdministrator vm, String rol, String username, String parola, String nume, int id_farmacie ){
        this.rol = rol;
        this.username = username;
        this.parola = parola;
        this.nume = nume;
        this.id_farmacie = id_farmacie;
    }

    @Override
    public void execute() {
        Utilizator u1 =new Utilizator(rol, username, parola, nume, id_farmacie);
        PersistentaUtilizatori.addIntoDB(u1);

    }
}
