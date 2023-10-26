package view.home.panels;

import javax.swing.*;
import java.util.Objects;

public class SuperAdminHome extends AdminHome{
    public SuperAdminHome(){
        super();
        Icon iconSAdmin = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconSuperAdmin.png")));
        super.setContIcon(iconSAdmin);
    }
}
