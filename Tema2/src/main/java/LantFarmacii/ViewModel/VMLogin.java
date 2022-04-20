package LantFarmacii.ViewModel;

import LantFarmacii.ViewModel.Command.ButonLogin;
import LantFarmacii.ViewModel.Command.Command;

public class VMLogin {

        private String username = "";
        private String password = "";
        public Command login;

        public void inst() {
            login = new ButonLogin(this, username, password);
        }

        public void setUserValue(String userValue) {
            this.username = userValue;
        }

        public void setPassValue(String passValue) {
            this.password = passValue;
        }
}
