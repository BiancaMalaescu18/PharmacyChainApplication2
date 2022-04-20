package LantFarmacii.ViewModel;

import LantFarmacii.Model.Persistenta.PersistentaUtilizatori;
import LantFarmacii.ViewModel.Command.*;

public class VMAdministrator {

    private String rol;
    private String username;
    private String parola;
    private String nume;
    private int  id_farmacie;
    private String[] date;

    public Command afisareUtilizatori;
    public Command updateUtilizator;
    public Command stergereUtilizator;
    public Command adaugareUtilizator;

    public VMAdministrator(){
        afisareUtilizatori = new ButonAfisareUtilizatori(this);
    }

    public Object[][] tableData2(PersistentaUtilizatori util) {
        int sz = util.getUtilizatori().size();
        Object[][] data = new Object[sz][];

        for (int i = 0; i < sz; i++) {
            data[i] = new Object[]{util.getUtilizatori().get(i).getRol(), util.getUtilizatori().get(i).getUsername(), util.getUtilizatori().get(i).getParola(), util.getUtilizatori().get(i).getNume(), util.getUtilizatori().get(i).getId_farmacie()};
        }

        return data;
    }

    public void instAdd(){
        adaugareUtilizator = new ButonAdaugareUtilizator(this, rol, username, parola, nume, id_farmacie );

    }

    public void instUpd(){
        updateUtilizator = new ButonUpdateUtilizator(this, rol,  username,  parola,  nume,  id_farmacie ,  date);
    }

    public void instDel(){
        stergereUtilizator = new ButonStergereUtilizator(this, date);
    }

    public void setId_farmacie(int id_farmacie) {
        this.id_farmacie = id_farmacie;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setDate(String[] date) {
        this.date = date;
    }
}
