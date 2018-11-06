package cs4125;

import UI.UserInterfaces.RentView;
import Business.BusinessManagement.Authentication;
import UI.Controllers.LoginController;
import UI.Controllers.RentController;
import UI.UserInterfaces.LoginView;

public class CS4125 {

    public static void main(String[] args) {
        login();
    }

    private static void login() {
        // Temp login: username = benjamin, password = benjaminpw
        //LoginView view = new LoginView();
        //Authentication model = new Authentication();
        //LoginController controller = new LoginController(view, model);
        
        new RentController(new RentView());
    }
}
