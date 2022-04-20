package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaUtilizatori;
import LantFarmacii.Model.Utilizator;
import LantFarmacii.View.ViewAdministrator;
import LantFarmacii.View.ViewAngajat;
import LantFarmacii.ViewModel.VMLogin;

import java.sql.SQLException;

public class ButonLogin implements Command{

    private String username;
    private String password;
    public ButonLogin (VMLogin login, String s1, String s2) {
        this.username = s1;
        this.password = s2;
    }

    @Override
    public void execute() {
        try {
            boolean gasit = false;
            for(Utilizator u : PersistentaUtilizatori.readFromDB().getUtilizatori()) {

                if(username.equals(u.getUsername()) && password.equals(u.getParola()) && u.getRol().equals("angajat")) {
                    ViewAngajat v = new ViewAngajat();
                    gasit = true;
                    break;
                } else if (username.equals(u.getUsername()) && password.equals(u.getParola()) && u.getRol().equals("administrator")) {
                    ViewAdministrator v = new ViewAdministrator();
                    gasit = true;
                    break;
                }

            }
            if(!gasit){
                System.out.println("Username/parola invalide!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

