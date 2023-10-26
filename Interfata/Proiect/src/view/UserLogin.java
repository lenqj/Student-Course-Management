package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class UserLogin extends JPanel {

    private JPanel jPanelLogo;
    private JPanel jPanelLogin;
    private JPanel jPanelRegister;
    private final JPanel contentPanel;

    private JButton btnLogare;
    private JButton btnInregistrare;
    private JButton btnInregistrareUtilizator;
    private JButton btnInapoi;

    private JTextField campNume;
    private JTextField campParola;
    private JTextField campNumeRegister;
    private JTextField campPrenume;
    private JTextField campEmail;
    private JTextField campCNP;
    private JTextField campTelefon;
    private JTextField campParolaRegister;

    private final CardLayout cardLayout;

    public UserLogin() {
        //Creating Panels
        createPanelLogo();
        createPanelLogin();
        createPanelRegister();

        //Creating Content Panel
        cardLayout = new CardLayout();
        contentPanel = new JPanel();
        contentPanel.setLayout( cardLayout );
        contentPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        contentPanel.add(jPanelLogin, "1");
        contentPanel.add(jPanelRegister, "2");

        //Panel Setting
        setLayout(new BorderLayout());
        add(jPanelLogo, BorderLayout.WEST);
        add(contentPanel);
    }

    public void createPanelLogo(){
        //Creating a label for logo
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\logo.png")));
        JLabel iconLabel = new JLabel("", SwingConstants.CENTER);
        iconLabel.setIcon(icon);

        //PanelLogo Setting
        jPanelLogo = new JPanel();
        jPanelLogo.setLayout(new BorderLayout());
        jPanelLogo.setBorder(new EmptyBorder(0, 100, 0, 100));
        jPanelLogo.add(iconLabel, BorderLayout.CENTER);
    }
    public void createPanelLogin() {
        //Creating icons
        Icon iconCNP = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconCNP.png")));
        Icon iconParola = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconParola.png")));
        Icon iconLogare = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconLogare.png")));
        Icon iconInregistrare = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconInregistrare.png")));

        //Creating Labels and Text Fields
        JLabel lblLogareUtilizator = new JLabel("Logare Utilizator", SwingConstants.CENTER);
        lblLogareUtilizator.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        JPanel panelCampuriLogare = new JPanel();
        panelCampuriLogare.setLayout(new GridLayout(6, 2, 25, 15));

        JLabel lblCNP = new JLabel("CNP:");
        lblCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCNP.setBorder(new EmptyBorder(0, 0, 0, 100));

        JLabel lblParola = new JLabel("Parola:");
        lblParola.setFont(new Font("Tahoma", Font.PLAIN, 20));

        campNume = new JTextField(10);
        JPanel campNumePanel = new JPanel();
        JLabel lblNumeicon = new JLabel("", iconCNP, JLabel.LEFT);

        campNume.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campNume.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblNumeicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campNumePanel.setBackground(Color.CYAN);
        campNumePanel.setLayout(new BoxLayout(campNumePanel, BoxLayout.X_AXIS));
        campNumePanel.add(lblNumeicon);
        campNumePanel.add(campNume);

        campParola = new JPasswordField();
        JPanel campParolaPanel = new JPanel();
        JLabel lblParolaicon = new JLabel("", iconParola, JLabel.LEFT);

        campParola.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campParola.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblParolaicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campParolaPanel.setBackground(Color.CYAN);
        campParolaPanel.setLayout(new BoxLayout(campParolaPanel, BoxLayout.X_AXIS));
        campParolaPanel.add(lblParolaicon);
        campParolaPanel.add(campParola);


        panelCampuriLogare.add(new JLabel());
        panelCampuriLogare.add(new JLabel());
        panelCampuriLogare.add(new JLabel());
        panelCampuriLogare.add(new JLabel());

        panelCampuriLogare.add(lblCNP);
        panelCampuriLogare.add(campNumePanel);
        panelCampuriLogare.add(lblParola);
        panelCampuriLogare.add(campParolaPanel);

        panelCampuriLogare.add(new JLabel());
        panelCampuriLogare.add(new JLabel());
        panelCampuriLogare.add(new JLabel());
        panelCampuriLogare.add(new JLabel());

        //Creating Buttons
        JPanel panelButoane = new JPanel();
        panelButoane.setLayout(new GridLayout(1, 2, 10, 10));

        btnLogare = new JButton("Logheaza-te!");
        btnLogare.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLogare.setIcon(iconLogare);

        btnInregistrare = new JButton("Inregistreaza-te!");
        btnInregistrare.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnInregistrare.setIcon(iconInregistrare);


        panelButoane.add(btnLogare);
        panelButoane.add(btnInregistrare);

        //PanelLogin Setting
        jPanelLogin = new JPanel();
        jPanelLogin.setLayout(new BorderLayout(100,100));
        jPanelLogin.setBorder(new EmptyBorder(0, 50,0,50));
        jPanelLogin.add(lblLogareUtilizator, BorderLayout.NORTH);
        jPanelLogin.add(panelCampuriLogare, BorderLayout.CENTER);
        jPanelLogin.add(panelButoane, BorderLayout.SOUTH);
    }
    public void createPanelRegister() {
        //Creating Icons
        Icon iconNume = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconNume.png")));
        Icon iconPrenume = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconPrenume.png")));
        Icon iconEmail = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconEmail.png")));
        Icon iconTelefon = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconTelefon.png")));
        Icon iconCNP = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconCNP.png")));
        Icon iconParola = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconParola.png")));
        Icon iconInapoi = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconInapoi.png")));
        Icon iconInregistrare = new ImageIcon(Objects.requireNonNull(getClass().getResource("home\\panels\\images\\iconInregistrare.png")));

        //Creating Labels and Text Fields
        JLabel lblInregistrare = new JLabel("Inregistrare Utilizator", SwingConstants.CENTER);
        lblInregistrare.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        jPanelRegister = new JPanel();

        JPanel panelCampuri = new JPanel();
        panelCampuri.setLayout(new GridLayout(6, 2, 25, 15));

        JLabel lblNume = new JLabel("Nume:");
        lblNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNume.setBorder(new EmptyBorder(0, 0, 0, 100));

        JLabel lblPrenume = new JLabel("Prenume:");
        lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblEmail = new JLabel("Adresa email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblCNP = new JLabel("CNP:");
        lblCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblParola = new JLabel("Parola:");
        lblParola.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblTelefon = new JLabel("Telefon:");
        lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));

        campNumeRegister = new JTextField();
        JPanel campNumePanel = new JPanel();
        JLabel lblNumeicon = new JLabel("", iconNume, JLabel.LEFT);

        campNumeRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campNumeRegister.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblNumeicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campNumePanel.setBackground(Color.CYAN);
        campNumePanel.setLayout(new BoxLayout(campNumePanel, BoxLayout.X_AXIS));
        campNumePanel.add(lblNumeicon);
        campNumePanel.add(campNumeRegister);

        campPrenume = new JTextField();
        JPanel campPrenumePanel = new JPanel();
        JLabel lblPrenumeicon = new JLabel("", iconPrenume, JLabel.LEFT);

        campPrenume.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campPrenume.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblPrenumeicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campPrenumePanel.setBackground(Color.CYAN);
        campPrenumePanel.setLayout(new BoxLayout(campPrenumePanel, BoxLayout.X_AXIS));
        campPrenumePanel.add(lblPrenumeicon);
        campPrenumePanel.add(campPrenume);


        campEmail = new JTextField();
        JPanel campEmailPanel = new JPanel();
        JLabel lblEmailicon = new JLabel("", iconEmail, JLabel.LEFT);

        campEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campEmail.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblEmailicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campEmailPanel.setBackground(Color.CYAN);
        campEmailPanel.setLayout(new BoxLayout(campEmailPanel, BoxLayout.X_AXIS));
        campEmailPanel.add(lblEmailicon);
        campEmailPanel.add(campEmail);

        campCNP = new JTextField();
        JPanel campCNPPanel = new JPanel();
        JLabel lblCNPicon = new JLabel("", iconCNP, JLabel.LEFT);

        campCNP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campCNP.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblCNPicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campCNPPanel.setBackground(Color.CYAN);
        campCNPPanel.setLayout(new BoxLayout(campCNPPanel, BoxLayout.X_AXIS));
        campCNPPanel.add(lblCNPicon);
        campCNPPanel.add(campCNP);

        campTelefon = new JTextField();
        JPanel campTelefonPanel = new JPanel();
        JLabel lblTelefonicon = new JLabel("", iconTelefon, JLabel.LEFT);

        campTelefon.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campTelefon.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblTelefonicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campTelefonPanel.setBackground(Color.CYAN);
        campTelefonPanel.setLayout(new BoxLayout(campTelefonPanel, BoxLayout.X_AXIS));
        campTelefonPanel.add(lblTelefonicon);
        campTelefonPanel.add(campTelefon);

        campParolaRegister = new JPasswordField();
        JPanel campParolaPanel = new JPanel();
        JLabel lblParolaicon = new JLabel("", iconParola, JLabel.LEFT);

        campParolaRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campParolaRegister.setBorder(new MatteBorder(2, 0, 2, 2, Color.CYAN));

        lblParolaicon.setBorder(new EmptyBorder(10, 10, 10, 10));

        campParolaPanel.setBackground(Color.CYAN);
        campParolaPanel.setLayout(new BoxLayout(campParolaPanel, BoxLayout.X_AXIS));
        campParolaPanel.add(lblParolaicon);
        campParolaPanel.add(campParolaRegister);


        panelCampuri.add(lblNume);
        panelCampuri.add(campNumePanel);

        panelCampuri.add(lblPrenume);
        panelCampuri.add(campPrenumePanel);

        panelCampuri.add(lblEmail);
        panelCampuri.add(campEmailPanel);

        panelCampuri.add(lblTelefon);
        panelCampuri.add(campTelefonPanel);

        panelCampuri.add(lblCNP);
        panelCampuri.add(campCNPPanel);

        panelCampuri.add(lblParola);
        panelCampuri.add(campParolaPanel);

        //Creating Buttons
        JPanel panelButoane = new JPanel();
        panelButoane.setLayout(new GridLayout(1, 2, 10, 10));

        btnInregistrareUtilizator = new JButton("Inregistreaza-te!");
        btnInregistrareUtilizator.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnInregistrareUtilizator.setIcon(iconInregistrare);

        btnInapoi = new JButton("Inapoi");
        btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnInapoi.setIcon(iconInapoi);

        panelButoane.add(btnInapoi);
        panelButoane.add(btnInregistrareUtilizator);

        //PanelRegister Setting
        jPanelRegister = new JPanel();
        jPanelRegister.setLayout(new BorderLayout(100,100));
        jPanelRegister.add(lblInregistrare, BorderLayout.NORTH);
        jPanelRegister.add(panelCampuri, BorderLayout.CENTER);
        jPanelRegister.add(panelButoane, BorderLayout.SOUTH);

    }

    //Util function
    public void showPanelInregistrare(){
        cardLayout.show(contentPanel, "2");
    }

    public void showPanelLogare(){
        cardLayout.show(contentPanel, "1");
    }

    public String getCNP(){
        return campNume.getText();
    }

    public String getParola(){
        return campParola.getText();
    }

    public String getCampNume(){
        return campNumeRegister.getText();
    }

    public String getCampPrenume(){
        return campPrenume.getText();
    }

    public String getCampEmail(){
        return campEmail.getText();
    }

    public String getCampCNP(){
        return campCNP.getText();
    }

    public String getCampTelefon(){
        return campTelefon.getText();
    }

    public String getCampParolaRegister(){
        return campParolaRegister.getText();
    }

    public void clearFields(){
        campNume.setText("");
        campParola.setText("");
        campParolaRegister.setText("");
        campNumeRegister.setText("");
        campPrenume.setText("");
        campCNP.setText("");
        campEmail.setText("");
        campTelefon.setText("");
    }

    //Listeners

    public void addBtnLogareListener(ActionListener log){
        btnLogare.addActionListener(log);
    }

    public void addBtnInregistrareListener(ActionListener inr){
        btnInregistrare.addActionListener(inr);
    }

    public void addBtnInapoiListener(ActionListener inp){
        btnInapoi.addActionListener(inp);
    }

    public void addBtnInregistrareUtilizatorListener(ActionListener inru){
        btnInregistrareUtilizator.addActionListener(inru);
    }


}
