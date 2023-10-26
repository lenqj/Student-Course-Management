package view.home.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
public class UserHome extends JPanel {
    protected JPanel contentPanel;
    protected JPanel menuPanel;
    protected JPanel profilPanel;
    protected JPanel editareProfilPanel;

    protected JPanel rightPanel;

    protected final CardLayout cardLayout;
    protected JMenu cont ;
    protected JMenuBar menuBar;
    protected JMenuItem profilulMeu;
    protected JMenuItem editareProfil;
    protected JMenuItem delogare;

    protected JLabel nume;
    protected JLabel prenume;
    protected JLabel adresaEmail;
    protected JLabel telefon;
    protected JLabel adresa;
    protected JLabel CNP;

    protected JLabel numeEditare;
    protected JLabel prenumeEditare;
    protected JLabel adresaEmailEditare;
    protected JLabel telefonEditare;
    protected JLabel adresaEditare;
    protected JLabel CNPEditare;

    protected JTextField textFieldAdresa;
    protected JTextField textFieldTelefon;
    protected JTextField textFieldEmail;
    protected JTextField textFieldIBAN;
    protected JTextField textFieldContract;

    protected JButton editCont;
    protected JButton stergeCont;

    protected TitledBorder titledBorder;
    protected TitledBorder titledBorderEditare;

    public UserHome() {
        setLayout(new BorderLayout());
        setSize(1000, 650);

        // Card Layout
        cardLayout = new CardLayout();
        createContentPanel();

        createMenuPanel();
        createProfilPanel();
        createEditareProfilPanel();

        add(menuPanel, BorderLayout.NORTH);
        contentPanel.add(profilPanel, "1");
        contentPanel.add(editareProfilPanel, "2");
        cardLayout.show(contentPanel, "1");

        add(contentPanel);
    }

    public void createMenuPanel() {
        // Setare panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());

        // Creare bara meniu
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        menuPanel.add(menuBar);

        // Meniu cont
        Icon iconCont = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/iconProfil.png")));
        Icon iconProfil = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/iconProfil.png")));
        Icon iconEditare = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/iconEditare.png")));
        Icon iconDelogare = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/iconDelogare.png")));

        cont = new JMenu("Cont");
        setContIcon(iconCont);
        profilulMeu = new JMenuItem("Profilul Meu");
        profilulMeu.setIcon(iconProfil);

        editareProfil = new JMenuItem("Editare profil");
        editareProfil.setIcon(iconEditare);

        delogare = new JMenuItem("Delogare");
        delogare.setIcon(iconDelogare);

        cont.addSeparator();
        cont.add(profilulMeu);
        cont.addSeparator();
        cont.add(editareProfil);
        cont.addSeparator();
        cont.add(delogare);
        cont.addSeparator();
        menuBar.add(cont);
    }

    public void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);
    }

    public void createProfilPanel() {
        profilPanel = new JPanel();
        profilPanel.setLayout(new BorderLayout());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        rightPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
        rightPanel.setBackground(Color.LIGHT_GRAY);

        stergeCont = new JButton("Sterge!");

        rightPanel.add(stergeCont);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new MatteBorder(50, 50, 50, 50, Color.LIGHT_GRAY.darker()));

        // Avatar
        JPanel avatarPanel = new JPanel();
        avatarPanel.setBackground(Color.LIGHT_GRAY.darker());
        avatarPanel.setLayout(new GridLayout());
        avatarPanel.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
        Icon avatar = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/avatar.png")));
        JLabel avatarLabel = new JLabel(avatar);
        avatarLabel.setBorder(new MatteBorder(15, 15, 15, 15, Color.LIGHT_GRAY.darker()));
        titledBorderEditare = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK), "");
        titledBorderEditare.setTitleJustification(TitledBorder.CENTER);
        titledBorderEditare.setTitlePosition(TitledBorder.BOTTOM);
        titledBorderEditare.setTitleFont(new Font("Arial", Font.BOLD, 10));
        avatarLabel.setBorder(titledBorderEditare);
        avatarPanel.add(avatarLabel);

        //Date
        JPanel dataPanel = new JPanel();
        dataPanel.setBackground(Color.LIGHT_GRAY.darker());
        dataPanel.setBorder(new EmptyBorder(100, 0, 150, 0));
        dataPanel.setLayout(new GridLayout(6, 3));
        numeEditare = new JLabel("", SwingConstants.CENTER);
        numeEditare.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        prenumeEditare = new JLabel("", SwingConstants.CENTER);
        prenumeEditare.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        adresaEmailEditare = new JLabel("", SwingConstants.CENTER);
        adresaEmailEditare.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        telefonEditare = new JLabel("", SwingConstants.CENTER);
        telefonEditare.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        adresaEditare = new JLabel("", SwingConstants.CENTER);
        adresaEditare.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        CNPEditare = new JLabel("", SwingConstants.CENTER);
        CNPEditare.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        dataPanel.add(numeEditare);
        dataPanel.add(prenumeEditare);
        dataPanel.add(adresaEmailEditare);
        dataPanel.add(telefonEditare);
        dataPanel.add(adresaEditare);
        dataPanel.add(CNPEditare);

        leftPanel.add(avatarPanel);
        leftPanel.add(dataPanel);

        profilPanel.add(leftPanel, BorderLayout.WEST);
        profilPanel.add(rightPanel);
    }

    public void createEditareProfilPanel() {
        editareProfilPanel = new JPanel();
        editareProfilPanel.setLayout(new BoxLayout(editareProfilPanel, BoxLayout.X_AXIS));

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
        rightPanel.setBackground(Color.LIGHT_GRAY);

        textFieldAdresa = new JTextField("");
        textFieldAdresa.setColumns(20);
        textFieldTelefon = new JTextField("");
        textFieldTelefon.setColumns(20);
        textFieldEmail = new JTextField("");
        textFieldEmail.setColumns(20);
        textFieldIBAN = new JTextField("");
        textFieldIBAN.setColumns(20);
        textFieldContract = new JTextField("");
        textFieldContract.setColumns(20);

        JLabel labelAdresa = new JLabel("Adresa");
        JLabel labelTelefon = new JLabel("Telefon");
        JLabel labelEmail = new JLabel("Email");
        JLabel labelIBAN = new JLabel("IBAN");
        JLabel labelContract = new JLabel("Contract");

        rightPanel.add(labelAdresa);
        rightPanel.add(textFieldAdresa);

        rightPanel.add(labelTelefon);
        rightPanel.add(textFieldTelefon);

        rightPanel.add(labelEmail);
        rightPanel.add(textFieldEmail);

        rightPanel.add(labelIBAN);
        rightPanel.add(textFieldIBAN);

        rightPanel.add(labelContract);
        rightPanel.add(textFieldContract);

        editCont = new JButton("Editeaza!");
        JPanel btnEdit = new JPanel();
        btnEdit.setBackground(Color.LIGHT_GRAY);
        btnEdit.add(editCont);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new MatteBorder(50, 50, 50, 50, Color.LIGHT_GRAY.darker()));

        // Avatar
        JPanel avatarPanel = new JPanel();
        avatarPanel.setBackground(Color.LIGHT_GRAY.darker());
        avatarPanel.setLayout(new GridLayout());
        avatarPanel.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
        Icon avatar = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/avatar.png")));
        JLabel avatarLabel = new JLabel(avatar);
        avatarLabel.setBorder(new MatteBorder(15, 15, 15, 15, Color.LIGHT_GRAY.darker()));
        titledBorder = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK), "");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.BOTTOM);
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 10));
        avatarLabel.setBorder(titledBorder);
        avatarPanel.add(avatarLabel);

        //Date
        JPanel dataPanel = new JPanel();
        dataPanel.setBackground(Color.LIGHT_GRAY.darker());
        dataPanel.setBorder(new EmptyBorder(100, 0, 150, 0));
        dataPanel.setLayout(new GridLayout(6, 3));
        nume = new JLabel("", SwingConstants.CENTER);
        nume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        prenume = new JLabel("", SwingConstants.CENTER);
        prenume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        adresaEmail = new JLabel("", SwingConstants.CENTER);
        adresaEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        telefon = new JLabel("", SwingConstants.CENTER);
        telefon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        adresa = new JLabel("", SwingConstants.CENTER);
        adresa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        CNP = new JLabel("", SwingConstants.CENTER);
        CNP.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        dataPanel.add(nume);
        dataPanel.add(prenume);
        dataPanel.add(adresaEmail);
        dataPanel.add(telefon);
        dataPanel.add(adresa);
        dataPanel.add(CNP);

        leftPanel.add(avatarPanel);
        leftPanel.add(dataPanel);

        editareProfilPanel.add(leftPanel);
        editareProfilPanel.add(rightPanel);
        editareProfilPanel.add(btnEdit);

    }

    //Util Functions

    public void setContIcon(Icon icon){
        cont.setIcon(icon);
    }
    public void setTitledBorder(String titleBorderLastName, String titleBordetFirstName) {
        titledBorder.setTitle(" " + titleBorderLastName + " " + titleBordetFirstName);
        titledBorderEditare.setTitle(" " + titleBorderLastName + " " + titleBordetFirstName);
    }

    public void setNume(String nume){
        this.numeEditare.setText("Nume: " + nume);
        this.nume.setText("Nume: " + nume);
    }

    public void setPrenume(String prenume){
        this.prenumeEditare.setText("Prenume: " + prenume);
        this.prenume.setText("Prenume: " + prenume);
    }

    public void setAdresaEmail(String adresaEmail){
        this.adresaEmailEditare.setText("Adresa de email: " + adresaEmail);
        this.adresaEmail.setText("Adresa de email: " + adresaEmail);
    }

    public void setTelefon(String telefon){
        this.telefonEditare.setText("Telefon: " + telefon);
        this.telefon.setText("Telefon: " + telefon);
    }

    public void setAdresa(String adresa){
        this.adresaEditare.setText("Adresa: " + adresa);
        this.adresa.setText("Adresa: " + adresa);
    }

    public void setCNP(String CNP){
        this.CNPEditare.setText("CNP: " + CNP);
        this.CNP.setText("CNP: " + CNP);
    }

    public void showProfilPanel() {
        cardLayout.show(contentPanel, "1");
    }

    public void showEditareProfilPanel() {
        cardLayout.show(contentPanel, "2");
    }

    public String getAdresa(){
        return textFieldAdresa.getText();
    }

    public String getTelefon(){
        return textFieldTelefon.getText();
    }

    public String getEmail(){
        return textFieldEmail.getText();
    }

    public String getIBAN(){
        return textFieldIBAN.getText();
    }

    public String getContract(){
        return textFieldContract.getText();
    }

    //Listeners
    public void addProfilulMeuListener(ActionListener pm) {
        profilulMeu.addActionListener(pm);
    }

    public void addEditareProfilListener(ActionListener ep) {
        editareProfil.addActionListener(ep);
    }

    public void addDelogareListener(ActionListener de) {
        delogare.addActionListener(de);
    }

    public void addEditareProfilButtonListener(ActionListener actionListener){
        editCont.addActionListener(actionListener);
    }

    public void addStergeContListener(ActionListener actionListener){
        stergeCont.addActionListener(actionListener);
    }
}
