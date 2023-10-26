package view.home.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StudentHome extends UserHome {
    private JPanel creeazaGrupPanel;
    private JPanel grupurileMelePanel;
    private JPanel cursurileMelePanel;
    private JPanel orarPanel;
    private JPanel notePanel;
    private JPanel mesajeGrupPanel;

    private JTextField descriereGrup;
    private JTextField ID_Grup;

    private JTextField textFieldMesajGrup;

    private JMenuItem cursurileMele;
    private JMenuItem orar;
    private JMenuItem note;
    private JMenuItem grupurileMele;
    private JMenuItem creareGrup;
    private JMenuItem chat;

    private JButton btnCreareGrup;
    private JButton btnInvitaInGrup;
    private JButton buttonCursuri;
    private JButton buttonTrimiteMesajGrup;
    private JButton buttonSugestiiInvita;
    private JButton buttonInvitaProfesor;
    private JButton buttonStergeGrup;

    private JComboBox comboIDGrup;
    private JComboBox comboUser;
    private JComboBox comboIDGrupMesaje;
    private JComboBox comboCursuri;
    private JComboBox comboSugestiiCNP;
    private JComboBox comboSugestiiID;
    private JComboBox comboProfesorCNP;
    private JComboBox comboInvPID;
    private JComboBox comboStergeGrup;

    private JTextField textFieldNrOre;

    public DefaultTableModel tableModelGrupuri;
    public DefaultTableModel tableModelCursuri;
    public DefaultTableModel tableModelOrar;
    public DefaultTableModel tableModelNote;
    public DefaultTableModel tableModelMesaje;

    public StudentHome() {
        super();

        createCreeazaGrupPanel();
        createGrupurileMelePanel();
        createCursurileMelePanel();
        createOrarPanel();
        createNotePanel();
        createMesajeGrupPanel();

        contentPanel.add(creeazaGrupPanel, "3");
        contentPanel.add(grupurileMelePanel, "4");
        contentPanel.add(cursurileMelePanel, "5");
        contentPanel.add(orarPanel, "6");
        contentPanel.add(notePanel, "7");
        contentPanel.add(mesajeGrupPanel, "mesajeGrupStudent");
    }

    public void createMenuPanel() {
        super.createMenuPanel();

        Icon iconStudent = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconStudent.png")));
        super.setContIcon(iconStudent);
        // Meniu cont
        Icon iconCursuri = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconCursuri.png")));
        Icon iconOrar = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconOrar.png")));
        Icon iconGrup = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconGrup.png")));
        Icon iconCreeazaGrup = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconInregistrare.png")));
        Icon iconNote = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconNote.png")));
        Icon iconChat = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconChat.png")));


        // Meniu Cursuri
        JMenu cursuri = new JMenu("Cursuri");
        cursuri.setIcon(iconCursuri);

        cursurileMele = new JMenuItem("Cursurile Mele");
        cursurileMele.setIcon(iconCursuri);

        orar = new JMenuItem("Orar");
        orar.setIcon(iconOrar);

        note = new JMenuItem("Note");
        note.setIcon(iconNote);

        cursuri.addSeparator();
        cursuri.add(cursurileMele);
        cursuri.addSeparator();
        cursuri.add(orar);
        cursuri.addSeparator();
        cursuri.add(note);
        cursuri.addSeparator();
        menuBar.add(cursuri);

        // Meniu Grupuri
        JMenu grupuri = new JMenu("Grupuri");
        grupuri.setIcon(iconGrup);
        grupurileMele = new JMenuItem("Grupurile Mele");
        grupurileMele.setIcon(iconGrup);

        creareGrup = new JMenuItem("Creeaza Grup");
        creareGrup.setIcon(iconCreeazaGrup);

        chat = new JMenuItem("Chat");
        chat.setIcon(iconChat);

        grupuri.addSeparator();
        grupuri.add(grupurileMele);
        grupuri.addSeparator();
        grupuri.add(creareGrup);
        grupuri.addSeparator();
        grupuri.add(chat);
        grupuri.addSeparator();
        menuBar.add(grupuri);
    }

    public void createEditareProfilPanel(){
        super.createEditareProfilPanel();
        JLabel ore = new JLabel("Numar ore");
        textFieldNrOre = new JTextField();
        textFieldNrOre.setColumns(20);
        rightPanel.add(ore);
        rightPanel.add(textFieldNrOre);
    }

    public void createCreeazaGrupPanel() {
        creeazaGrupPanel = new JPanel();
        creeazaGrupPanel.setLayout(new BoxLayout(creeazaGrupPanel, BoxLayout.Y_AXIS));
        creeazaGrupPanel.setBackground(Color.LIGHT_GRAY.darker());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        JLabel Descriere = new JLabel("Descriere:");
        JLabel ID = new JLabel("ID Grup:");

        descriereGrup = new JTextField();
        descriereGrup.setColumns(20);

        ID_Grup = new JTextField();
        ID_Grup.setColumns(20);

        btnCreareGrup = new JButton("Creeaza!");

        topPanel.add(Descriere);
        topPanel.add(descriereGrup);
        topPanel.add(ID);
        topPanel.add(ID_Grup);
        topPanel.add(btnCreareGrup);

        JPanel panelInvitaProfesor = new JPanel();
        comboInvPID = new JComboBox();
        comboProfesorCNP = new JComboBox();
        buttonInvitaProfesor = new JButton("Invita!");
        JLabel invPId = new JLabel("ID GRUP:");
        JLabel pCNP= new JLabel("Profesor:");

        panelInvitaProfesor.add(invPId);
        panelInvitaProfesor.add(comboInvPID);
        panelInvitaProfesor.add(pCNP);
        panelInvitaProfesor.add(comboProfesorCNP);
        panelInvitaProfesor.add(buttonInvitaProfesor);

        JPanel stergeGrup = new JPanel();
        comboStergeGrup = new JComboBox();
        buttonStergeGrup = new JButton("Sterge grup");
        stergeGrup.add(comboStergeGrup);
        stergeGrup.add(buttonStergeGrup);

        JPanel botPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JLabel labelID_Grup = new JLabel("ID Grup:");
        JLabel labelID_Student = new JLabel("Student:");
        btnInvitaInGrup = new JButton("Invita!");
        comboIDGrup = new JComboBox();
        comboUser = new JComboBox();

        botPanel.add(labelID_Grup);
        botPanel.add(comboIDGrup);
        botPanel.add(labelID_Student);
        botPanel.add(comboUser);
        botPanel.add(btnInvitaInGrup);

        JPanel panelSg = new JPanel();
        panelSg.setLayout(new FlowLayout());
        JLabel sugestie = new JLabel("SUGESTII");
        sugestie.setFont(new Font("Arial", Font.BOLD, 20));
        panelSg.add(sugestie);

        JPanel panelSterge = new JPanel();
        panelSterge.setLayout(new FlowLayout());
        JLabel sterge = new JLabel("STERGE GRUP");
        sterge.setFont(new Font("Arial", Font.BOLD, 20));
        panelSterge.add(sterge);

        JPanel panelIg = new JPanel();
        panelSg.setLayout(new FlowLayout());
        JLabel ig = new JLabel("INVITA STUDENT");
        ig.setFont(new Font("Arial", Font.BOLD, 20));
        panelIg.add(ig);

        JPanel panelCg = new JPanel();
        panelSg.setLayout(new FlowLayout());
        JLabel cg = new JLabel("CREEAZA GRUP");
        cg.setFont(new Font("Arial", Font.BOLD, 20));
        panelCg.add(cg);

        JPanel panelIp = new JPanel();
        panelSg.setLayout(new FlowLayout());
        JLabel ip = new JLabel("INVITA PROFESOR");
        ip.setFont(new Font("Arial", Font.BOLD, 20));
        panelIp.add(ip);


        JPanel sugestiiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        comboSugestiiCNP = new JComboBox();
        comboSugestiiID = new JComboBox();
        buttonSugestiiInvita = new JButton("Invita!");

        sugestiiPanel.add(labelID_Grup);
        sugestiiPanel.add(comboSugestiiID);
        sugestiiPanel.add(labelID_Student);
        sugestiiPanel.add(comboSugestiiCNP);
        sugestiiPanel.add(buttonSugestiiInvita);

        creeazaGrupPanel.add(panelCg);
        creeazaGrupPanel.add(topPanel);
        creeazaGrupPanel.add(panelSterge);
        creeazaGrupPanel.add(stergeGrup);
        creeazaGrupPanel.add(panelIp);
        creeazaGrupPanel.add(panelInvitaProfesor);
        creeazaGrupPanel.add(panelIg);
        creeazaGrupPanel.add(botPanel);
        creeazaGrupPanel.add(panelSg);
        creeazaGrupPanel.add(sugestiiPanel);
    }

    public void createGrupurileMelePanel() {
        grupurileMelePanel = new JPanel();
        grupurileMelePanel.setLayout(new BoxLayout(grupurileMelePanel, BoxLayout.Y_AXIS));
        grupurileMelePanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();
        tableHead.add("ID Grup");
        tableHead.add("CNP Admin");
        tableHead.add("CNP Profesor");
        tableHead.add("CNP Student");
        tableHead.add("Invitatie");
        tableHead.add("Descriere Grup");
        tableModelGrupuri = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelGrupuri);
        JScrollPane scrollPane = new JScrollPane(table);

        grupurileMelePanel.add(scrollPane);
    }

    public void createCursurileMelePanel() {
        cursurileMelePanel = new JPanel();
        cursurileMelePanel.setLayout(new BoxLayout(cursurileMelePanel, BoxLayout.Y_AXIS));
        cursurileMelePanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();
        tableHead.add("Curs");
        tableHead.add("Profesor");
        tableHead.add("Descriere Curs");
        tableHead.add("Numar Studenti");
        tableHead.add("Numar Studenti Maxim");
        tableHead.add("Procent Nota");
        tableModelCursuri = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelCursuri);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelCursuri = new JPanel(new FlowLayout());
        JLabel labelCursuri = new JLabel("Cursuri");
        comboCursuri = new JComboBox();
        buttonCursuri = new JButton("Inscrie-te!");

        panelCursuri.add(labelCursuri);
        panelCursuri.add(comboCursuri);
        panelCursuri.add(buttonCursuri);


        cursurileMelePanel.add(scrollPane);
        cursurileMelePanel.add(panelCursuri);
    }

    public void createOrarPanel() {
        orarPanel = new JPanel();
        orarPanel.setLayout(new BorderLayout());
        orarPanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();
        tableHead.add("Curs");
        tableHead.add("Tip Activitate");
        tableHead.add("Ora Start");
        tableHead.add("Ora Final");
        tableHead.add("Ziua");
        tableModelOrar = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelOrar);

        JScrollPane scrollPane = new JScrollPane(table);
        orarPanel.add(scrollPane);
    }

    public void createNotePanel() {
        notePanel = new JPanel();
        notePanel.setLayout(new BorderLayout());
        notePanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();
        tableHead.add("Curs");
        tableHead.add("Nota");
        tableHead.add("Tip Activitate");
        tableModelNote = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelNote);

        JScrollPane scrollPane = new JScrollPane(table);
        notePanel.add(scrollPane);
    }

    public void createMesajeGrupPanel() {
        mesajeGrupPanel = new JPanel();
        mesajeGrupPanel.setLayout(new BoxLayout(mesajeGrupPanel, BoxLayout.Y_AXIS));
        mesajeGrupPanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();
        tableHead.add("Grup");
        tableHead.add("CNP");
        tableHead.add("Mesaj");
        tableHead.add("Data");
        tableModelMesaje = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelMesaje);
        JScrollPane scrollPane = new JScrollPane(table);


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        JLabel labelID_Grup = new JLabel("Grup");
        JLabel labelMesaj = new JLabel("Mesaj");

        comboIDGrupMesaje = new JComboBox();

        textFieldMesajGrup = new JTextField();
        textFieldMesajGrup.setColumns(20);

        buttonTrimiteMesajGrup = new JButton("Trimite");

        topPanel.add(labelID_Grup);
        topPanel.add(comboIDGrupMesaje);
        topPanel.add(labelMesaj);
        topPanel.add(textFieldMesajGrup);
        topPanel.add(buttonTrimiteMesajGrup);

        mesajeGrupPanel.add(scrollPane);
        mesajeGrupPanel.add(topPanel);
    }


    //Util Methods

    public String getSelectedIDGrup() {
        if(comboIDGrup.getSelectedItem() == null)
            return "0";
        return comboIDGrup.getSelectedItem().toString();
    }

    public int getSelectedIDGrupMesaj() {
        if(comboIDGrupMesaje.getSelectedItem() == null)
            return 0;
        return Integer.parseInt(comboIDGrupMesaje.getSelectedItem().toString());
    }

    public String getSelectedCNP() {
        if(comboUser.getSelectedItem() == null)
            return "0";
        return comboUser.getSelectedItem().toString();
    }

    public String getIDGrup() {
        return ID_Grup.getText();
    }

    public String getTextFieldMesajGrup() {
        return textFieldMesajGrup.getText();
    }

    public void clearTextFieldMesajGrup() {
        textFieldMesajGrup.setText("");
    }

    public void setGrupurileMeleData(String[] grupurileMeleData) {
        this.tableModelGrupuri.addRow(grupurileMeleData);
    }

    public void setCursurileMeleData(String[] cursurileMeleData) {
        this.tableModelCursuri.addRow(cursurileMeleData);
    }

    public void setOrarData(String[] orarData) {
        this.tableModelOrar.addRow(orarData);
    }

    public void setNoteData(String[] noteData) {
        this.tableModelNote.addRow(noteData);
    }
    public void setMesajeData(String[] mesajeData) {
        this.tableModelMesaje.addRow(mesajeData);
    }

    public String getDescriereGrup() {
        return descriereGrup.getText();
    }

    public void showCreareGrupPanel() {
        cardLayout.show(contentPanel, "3");
    }

    public void showGrupurileMelePanel() {
        cardLayout.show(contentPanel, "4");
    }

    public void showCursurileMelePanel() {
        cardLayout.show(contentPanel, "5");
    }

    public void showOrarPanel() {
        cardLayout.show(contentPanel, "6");
    }

    public void showNotePanel() {
        cardLayout.show(contentPanel, "7");
    }

    public void showMesajePanel() {
        cardLayout.show(contentPanel, "mesajeGrupStudent");
    }

    public void addComboIDGrup(String[] IDGrup) {
        comboIDGrup.removeAllItems();
        for (String s : IDGrup) {
            comboIDGrup.addItem(s);
        }
    }

    public void addComboInvPID(String[] IDGrup) {
        comboInvPID.removeAllItems();
        for (String s : IDGrup) {
            comboInvPID.addItem(s);
        }
    }

    public void addComboSugestiiID(String[] ID){
        comboSugestiiID.removeAllItems();
        for (String s : ID) {
            comboSugestiiID.addItem(s);
        }
    }

    public String getComboSugestiiID(){
        if(comboSugestiiID.getSelectedItem() == null)
            return "0";
        return comboSugestiiID.getSelectedItem().toString();
    }

    public String getComboInvPID(){
        if(comboInvPID.getSelectedItem() == null)
            return "0";
        return comboInvPID.getSelectedItem().toString();
    }

    public String getComboProfesorCNP(){
        if(comboProfesorCNP.getSelectedItem() == null)
            return "0";
        return comboProfesorCNP.getSelectedItem().toString();
    }

    public void addComboUser(String[] groupUsers){
        comboUser.removeAllItems();
        for(String s : groupUsers){
            comboUser.addItem(s);
        }
    }

    public void addComboProfesorCNP(String[] groupUsers){
        comboProfesorCNP.removeAllItems();
        for(String s : groupUsers){
            comboProfesorCNP.addItem(s);
        }
    }

    public void addComboSugestiiCNP(String[] CNP){
        comboSugestiiCNP.removeAllItems();
        ArrayList<String> listCNP = new ArrayList<>();
        for(String s : CNP){
            listCNP.add(s);
        }
        Collections.shuffle(listCNP);
        Iterator<String> iterator = listCNP.iterator();
        int i = 0;
        while(i < 3 && iterator.hasNext()){
            comboSugestiiCNP.addItem(iterator.next());
            i++;
        }
    }

    public String getComboSugestiiCNP(){
        if(comboSugestiiCNP.getSelectedItem() == null)
            return "0";
        return comboSugestiiCNP.getSelectedItem().toString();
    }

    public String getComboStergeGrup(){
        if(comboStergeGrup.getSelectedItem() == null)
            return "0";
        return comboStergeGrup.getSelectedItem().toString();
    }

    public void addComboIDGrupMesaje(String[] IDGrup) {
        comboIDGrupMesaje.removeAllItems();
        for (String s : IDGrup) {
            comboIDGrupMesaje.addItem(s);
        }
    }

    public void addComboStergeGrup(String[] IDGrup) {
        comboStergeGrup.removeAllItems();
        for (String s : IDGrup) {
            comboStergeGrup.addItem(s);
        }
    }

    public String getSelectedCursuri() {
        if(comboCursuri.getSelectedItem() == null)
            return "0";
        return comboCursuri.getSelectedItem().toString();
    }

    public void addComboCursuri(String[] Cursuri) {
        comboCursuri.removeAllItems();
        for (String s : Cursuri) {
            comboCursuri.addItem(s);
        }
    }

    public String getTextFieldNrOre(){
        return  textFieldNrOre.getText();
    }

    //Listeners
    public void addCursurileMeleListener(ActionListener cm) {
        cursurileMele.addActionListener(cm);
    }

    public void addOrarListener(ActionListener or) {
        orar.addActionListener(or);
    }

    public void addNoteListener(ActionListener nt) {
        note.addActionListener(nt);
    }

    public void addGrupurileMeleListener(ActionListener gm) {
        grupurileMele.addActionListener(gm);
    }

    public void addCreareGrupListener(ActionListener cg) {
        creareGrup.addActionListener(cg);
    }

    public void addBtnCreareGrupListener(ActionListener bcg) {
        btnCreareGrup.addActionListener(bcg);
    }

    public void addBtnInvitaInGrupListener(ActionListener ig) {
        btnInvitaInGrup.addActionListener(ig);
    }

    public void addComboIDGrupListener(ActionListener actionListener){
        comboIDGrup.addActionListener(actionListener);
    }

    public void addChatListener(ActionListener nt) {
        chat.addActionListener(nt);
    }
    public void addComboIDGrupMesajeListener(ActionListener actionListener){
        comboIDGrupMesaje.addActionListener(actionListener);
    }

    public void addCursuriButtonListener(ActionListener actionListener){
        buttonCursuri.addActionListener(actionListener);
    }

    public void addButtonTrimiteMesajGrupListener(ActionListener actionListener){
        buttonTrimiteMesajGrup.addActionListener(actionListener);
    }

    public void addButtonSugestiiInvita(ActionListener actionListener){
        buttonSugestiiInvita.addActionListener(actionListener);
    }

    public void addButtonInvitaProfesorListener(ActionListener actionListener){
        buttonInvitaProfesor.addActionListener(actionListener);
    }

    public void addButtonStergeGrupListener(ActionListener actionListener){
        buttonStergeGrup.addActionListener(actionListener);
    }
}
