package view;

import view.home.panels.*;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public UserLogin userLogin;
    public UserHome userHome;
    public StudentHome studentHome;
    public TeacherHome teacherHome;
    public AdminHome adminHome;
    public SuperAdminHome superAdminHome;
    private final JPanel contentPanel;

    private final CardLayout cardLayout;

    public View() {
        setSize(1000, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");

        userLogin = new UserLogin();
        userHome = new UserHome();
        studentHome = new StudentHome();
        teacherHome = new TeacherHome();
        adminHome = new AdminHome();
        superAdminHome = new SuperAdminHome();

        cardLayout = new CardLayout();
        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        contentPanel.add(userLogin, "0");
        contentPanel.add(userHome, "1");
        contentPanel.add(studentHome, "2");
        contentPanel.add(teacherHome, "3");
        contentPanel.add(adminHome, "4");
        contentPanel.add(superAdminHome, "5");

        cardLayout.show(contentPanel, "0");

        add(contentPanel);
    }

    public void showPanel(String idRol) {
        cardLayout.show(contentPanel, idRol);
        if(idRol.equals("1")){
            setTitle("User Home");
        }else if(idRol.equals("2")){
            setTitle("Student Home");
        }else if(idRol.equals("3")){
            setTitle("Profesor Home");
        }else if(idRol.equals("4")){
            setTitle("Admin Home");
        }else
            setTitle("Super Adimn Home");

    }
}

