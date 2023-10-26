package runner;

import controller.Controller;
import model.Model;
import view.View;

public class ApplicationRunner {
    public static void main(String[] args){
        View view = new View();
        Model model = new Model();
        new Controller(view, model);
        view.setVisible(true);
    }
}
