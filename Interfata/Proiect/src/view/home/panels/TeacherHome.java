package view.home.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class TeacherHome extends UserHome{
    private JPanel coursesPanel;
    private JPanel cataloguePanel;
    private JPanel scheduleCoursePanel;
    private JPanel mesajeGrupPanel;

    private JPanel grupurileMelePanel;

    private JCheckBox checkBoxLaborator;
    private JCheckBox checkBoxSeminar;

    private JMenuItem coursesMenu;
    private JMenuItem catalogueMenu;
    private JMenuItem scheduleCourseMenu;

    private JMenuItem groupsMenu;

    private JMenuItem chatMenu;
    private JButton scheduleCourseButton;
    private JButton ponderiButton;
    private JButton createCourseButton;
    private JButton noteaza;
    private JButton buttonAddUserCourses;
    private JButton buttonTrimiteMesajGrup;
    private JButton buttonStergeCurs;

    private JTextField textFieldOreMin;
    private JTextField textFieldOreMax;
    private JTextField textFieldDepartament;


    private JComboBox comboCoursesID2;
    private JComboBox comboWeekDays;
    private JComboBox comboStartHour;
    private JComboBox comboFinalHour;
    private JComboBox comboActivities;
    private JComboBox comboCoursesID3;
    private JComboBox comboPonderiCurs;
    private JComboBox comboPonderiSeminar;
    private JComboBox comboPonderiLaborator;
    private JComboBox comboTipActivitate;
    private JComboBox comboNota;
    private JComboBox comboCN;
    private JComboBox comboIN;
    private JComboBox comboAddUsersCourses;
    private JComboBox comboAddCourses;
    private JComboBox comboIDGrupMesaje;
    private JComboBox comboCursuriStergere;
    private JTextField textFieldCourseID;
    private JTextField textFieldCourseDescription;
    private JTextField textFieldMesajGrup;

    public DefaultTableModel tableModelCursuri;
    public DefaultTableModel tableModelCatalog;
    public DefaultTableModel tableModelMesaje;

    public DefaultTableModel tableModelGrupuri;

    public TeacherHome(){
        super();

        createCoursesPanel();
        createCataloguePanel();
        createScheduleCoursePanel();
        createMesajeGrupPanel();
        createGrupurileMelePanel();

        contentPanel.add(coursesPanel, "8");
        contentPanel.add(cataloguePanel, "9");
        contentPanel.add(scheduleCoursePanel, "10");
        contentPanel.add(mesajeGrupPanel, "mesajeGrupTeacher");
        contentPanel.add(grupurileMelePanel, "grupurileMeleTeacher");
    }

    public void createMenuPanel(){
        super.createMenuPanel();

        Icon iconCourses = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconCursuri.png")));
        Icon iconActivities = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconActivities.png")));
        Icon iconCatalogue = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconCatalogue.png")));
        Icon iconSchedule = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconSchedule.png")));
        Icon iconGrup = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconGrup.png")));
        Icon iconChat = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconChat.png")));
        Icon iconTeacher = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconTeacher.png")));

        super.setContIcon(iconTeacher);
        //Menu Activities
        JMenu activities = new JMenu("Activitati");
        activities.setIcon(iconActivities);

        catalogueMenu = new JMenuItem("Catalog");
        catalogueMenu.setIcon(iconCatalogue);

        coursesMenu = new JMenuItem("Cursuri");
        coursesMenu.setIcon(iconCourses);

        scheduleCourseMenu = new JMenuItem("Programare Activitati");
        scheduleCourseMenu.setIcon(iconSchedule);

        activities.addSeparator();
        activities.add(coursesMenu);
        activities.addSeparator();
        activities.add(catalogueMenu);
        activities.addSeparator();
        activities.add(scheduleCourseMenu);
        activities.addSeparator();

        JMenu groups = new JMenu("Grupuri");
        groups.setIcon(iconGrup);
        groupsMenu = new JMenuItem("Grupurile Mele");
        groupsMenu.setIcon(iconGrup);
        chatMenu = new JMenuItem("Chat");
        chatMenu.setIcon(iconChat);

        groups.addSeparator();
        groups.add(groupsMenu);
        groups.addSeparator();
        groups.add(chatMenu);
        groups.addSeparator();


        menuBar.add(activities);
        menuBar.add(groups);
    }

    //Creating Panels

    public void createEditareProfilPanel(){
        super.createEditareProfilPanel();
        JLabel oreMin = new JLabel("Ore Minime");
        JLabel oreMax = new JLabel("Ore Maxime");
        JLabel departament = new JLabel("Departament");

        textFieldOreMin = new JTextField();
        textFieldOreMin.setColumns(20);
        textFieldOreMax = new JTextField();
        textFieldOreMax.setColumns(20);
        textFieldDepartament = new JTextField();
        textFieldDepartament.setColumns(20);
        rightPanel.add(oreMin);
        rightPanel.add(textFieldOreMin);
        rightPanel.add(oreMax);
        rightPanel.add(textFieldOreMax);
        rightPanel.add(departament);
        rightPanel.add(textFieldDepartament);
    }

    public void createCoursesPanel(){
        coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
        coursesPanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();

        tableHead.add("Curs");
        tableHead.add("Profesor");
        tableHead.add("Student");
        tableHead.add("Descriere Curs");
        tableHead.add("Numar Studenti");
        tableHead.add("Numar Studenti Maxim");
        tableHead.add("Procent Nota");
        tableHead.add("Nota Finala");
        tableHead.add("Tip Activitate");

        tableModelCursuri = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelCursuri);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        JLabel labelCNPUsers = new JLabel("User");
        JLabel labelIDCourses = new JLabel("Curs");
        comboAddUsersCourses = new JComboBox();
        comboAddCourses = new JComboBox();
        buttonAddUserCourses = new JButton("Adauga");

        topPanel.add(labelIDCourses);
        topPanel.add(comboAddCourses);

        topPanel.add(labelCNPUsers);
        topPanel.add(comboAddUsersCourses);

        topPanel.add(buttonAddUserCourses);

        JPanel bottomPanel = new JPanel();
        JLabel idC = new JLabel("ID Curs");
        comboCursuriStergere = new JComboBox();
        buttonStergeCurs = new JButton("Sterge");
        bottomPanel.add(idC);
        bottomPanel.add(comboCursuriStergere);
        bottomPanel.add(buttonStergeCurs);

        coursesPanel.add(scrollPane);
        coursesPanel.add(topPanel);
        coursesPanel.add(bottomPanel);
    }

    public void createCataloguePanel(){
        cataloguePanel = new JPanel();
        cataloguePanel.setLayout(new BorderLayout());
        cataloguePanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();
        tableHead.add("Curs");
        tableHead.add("Nume");
        tableHead.add("Prenume");
        tableHead.add("Nota Curs");
        tableHead.add("Nota Finala");
        tableModelCatalog = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelCatalog);
        JScrollPane scrollPane = new JScrollPane(table);
        cataloguePanel.add(scrollPane, BorderLayout.NORTH);

        //Notare Student
        String[] note = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] activitate = {"Curs", "Seminar", "Laborator"};
        comboCN = new JComboBox();
        comboTipActivitate = new JComboBox(activitate);
        comboIN = new JComboBox();
        comboNota = new JComboBox(note);

        JLabel idCurs = new JLabel("Curs: ");
        JLabel tipActivitate = new JLabel("Tip Activitate: ");
        JLabel idStudent = new JLabel("Student: ");
        JLabel nota = new JLabel("Nota: ");

        noteaza = new JButton("Noteaza!");

        JPanel panelNotare = new JPanel(new FlowLayout());
        panelNotare.add(idCurs);
        panelNotare.add(comboCN);
        panelNotare.add(tipActivitate);
        panelNotare.add(comboTipActivitate);
        panelNotare.add(idStudent);
        panelNotare.add(comboIN);
        panelNotare.add(nota);
        panelNotare.add(comboNota);
        panelNotare.add(noteaza);

        cataloguePanel.add(panelNotare);
    }

    public void createScheduleCoursePanel(){
        scheduleCoursePanel = new JPanel();
        scheduleCoursePanel.setLayout(new BorderLayout());
        scheduleCoursePanel.setBackground(Color.LIGHT_GRAY.darker());
        /*---------------------------------------------------------------------------------------------*/
        //Panel 1 - creare cursuri/laborator/seminar
        /*---------------------------------------------------------------------------------------------*/
        JPanel contentPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JLabel courseID1 = new JLabel("ID Curs");
        textFieldCourseID = new JTextField();
        textFieldCourseID.setColumns(3);

        checkBoxLaborator = new JCheckBox();
        checkBoxSeminar = new JCheckBox();

        JLabel checkLaborator = new JLabel("Laborator: ");
        JLabel checkSeminar = new JLabel("Seminar: ");
        JLabel descriptionCourse = new JLabel("Descriere Curs");
        textFieldCourseDescription = new JTextField();
        textFieldCourseDescription.setColumns(10);

        createCourseButton = new JButton("Creeaza!");

        contentPanel1.add(courseID1);
        contentPanel1.add(textFieldCourseID);
        contentPanel1.add(descriptionCourse);
        contentPanel1.add(textFieldCourseDescription);
        contentPanel1.add(checkSeminar);
        contentPanel1.add(checkBoxSeminar);
        contentPanel1.add(checkLaborator);
        contentPanel1.add(checkBoxLaborator);
        contentPanel1.add(createCourseButton);
        /*---------------------------------------------------------------------------------------------*/
        //Panel 1 - final
        /*---------------------------------------------------------------------------------------------*/


        /*---------------------------------------------------------------------------------------------*/
        //Panel 2 - Programare Activitati
        /*---------------------------------------------------------------------------------------------*/
        JPanel contentPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JLabel labelActivties = new JLabel("Activitate");
        String[] activities = {"Curs", "Seminar", "Laborator"};
        comboActivities = new JComboBox(activities);

        JLabel courseID2 = new JLabel("ID Curs");
        comboCoursesID2 = new JComboBox();

        JLabel day = new JLabel("Ziua");
        String[] weekDays = {"Luni", "Marti", "Miercuri", "Joi", "Vineri"};
        comboWeekDays = new JComboBox(weekDays);

        String[] startHour = {"8:00", "10:00", "12:00", "14:00", "16:00", "18:00"};
        JLabel starthr = new JLabel("Ora Start");
        comboStartHour = new JComboBox(startHour);

        JLabel finishr = new JLabel("Ora Final");
        String[] finishHour = {"9:50", "11:50", "13:50", "15:50", "17:50", "19:50"};
        comboFinalHour = new JComboBox(finishHour);

        scheduleCourseButton = new JButton("Trimite!");

        contentPanel2.add(labelActivties);
        contentPanel2.add(comboActivities);
        contentPanel2.add(courseID2);
        contentPanel2.add(comboCoursesID2);
        contentPanel2.add(day);
        contentPanel2.add(comboWeekDays);
        contentPanel2.add(starthr);
        contentPanel2.add(comboStartHour);
        contentPanel2.add(finishr);
        contentPanel2.add(comboFinalHour);
        contentPanel2.add(scheduleCourseButton);
        /*---------------------------------------------------------------------------------------------*/
        //Panel 2 - final
        /*---------------------------------------------------------------------------------------------*/

        /*---------------------------------------------------------------------------------------------*/
        //Panel 3 - Setare Ponderi Activitati
        /*---------------------------------------------------------------------------------------------*/

        JPanel contentPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JLabel courseID3 = new JLabel("ID Curs");
        comboCoursesID3 = new JComboBox();
        JLabel pondereCurs = new JLabel("Pondere Curs");
        String[] ponderi = {"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
        comboPonderiCurs = new JComboBox(ponderi);
        JLabel pondereSeminar = new JLabel("Pondere Seminar");
        comboPonderiSeminar = new JComboBox();
        JLabel pondereLaborator = new JLabel("Pondere Laborator");
        comboPonderiLaborator = new JComboBox();
        ponderiButton = new JButton("Seteaza!");

        contentPanel3.add(courseID3);
        contentPanel3.add(comboCoursesID3);
        contentPanel3.add(pondereCurs);
        contentPanel3.add(comboPonderiCurs);
        contentPanel3.add(pondereSeminar);
        contentPanel3.add(comboPonderiSeminar);
        contentPanel3.add(pondereLaborator);
        contentPanel3.add(comboPonderiLaborator);
        contentPanel3.add(ponderiButton);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(contentPanel1);
        contentPanel.add(contentPanel2);
        contentPanel.add(contentPanel3);

        scheduleCoursePanel.add(contentPanel);
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

    //Util Methods
    public void showCoursesPanel(){
        cardLayout.show(contentPanel, "8");
    }

    public void showCataloguePanel(){
        cardLayout.show(contentPanel, "9");
    }
    public void showScheduleCoursePanel(){
        cardLayout.show(contentPanel, "10");
    }
    public void showMesajePanel() {
        cardLayout.show(contentPanel, "mesajeGrupTeacher");
    }

    public void showGrupurileMelePanel() {
        cardLayout.show(contentPanel, "grupurileMeleTeacher");
    }

    public void setCoursesData(String[] coursesData){
        this.tableModelCursuri.addRow(coursesData);
    }

    public void clearTextFieldMesajGrup() {
        textFieldMesajGrup.setText("");
    }
    public void setCatalogueData(String[] catalogueData){
        this.tableModelCatalog.addRow(catalogueData);
    }
    public void setMesajeData(Object[] mesaje){
        this.tableModelMesaje.addRow(mesaje);
    }
    public void setGroupsData(Object[] grupuri){
        this.tableModelGrupuri.addRow(grupuri);
    }
    public String getComboTipActivitate(){
        return comboTipActivitate.getSelectedItem().toString();
    }

    public String getComboNota(){
        return comboNota.getSelectedItem().toString();
    }

    public String getComboCN(){
        if(comboCN.getSelectedItem() == null)
            return "0";
        return comboCN.getSelectedItem().toString();
    }

    public String getComboIn(){
        if(comboIN.getSelectedItem() == null)
            return "0";
        return comboIN.getSelectedItem().toString();
    }

    public String getComboAddUsersCourses(){
        if(comboAddUsersCourses.getSelectedItem() == null)
            return "0";
        return comboAddUsersCourses.getSelectedItem().toString();
    }

    public String getComboAddCourses(){
        if(comboAddCourses.getSelectedItem() == null)
            return "0";
        return comboAddCourses.getSelectedItem().toString();
    }

    public void setComboAddUsersCourses(String[] useri){
        comboAddUsersCourses.removeAllItems();
        for(String s : useri)
            comboAddUsersCourses.addItem(s);
    }

    public void setComboAddCourses(String[] cursuri){
        comboAddCourses.removeAllItems();
        for(String s : cursuri)
            comboAddCourses.addItem(s);
    }


    public void setComboCN(String[] cursuri){
        comboCN.removeAllItems();
        for(String s : cursuri)
            comboCN.addItem(s);
    }

    public String getTextFieldOreMin(){
        return textFieldOreMin.getText();
    }

    public String getTextFieldOreMax(){
        return textFieldOreMax.getText();
    }

    public String getTextFieldDepartament(){
        return textFieldDepartament.getText();
    }

    public void setComboIN(String[] studenti){
        comboIN.removeAllItems();
        for(String s : studenti)
            comboIN.addItem(s);
    }

    public void setComboCoursesID2(String[] coursesIDData){
        comboCoursesID2.removeAllItems();
        for(String s : coursesIDData){
            comboCoursesID2.addItem(s);
        }
    }

    public void setComboCoursesID3(String[] coursesIDData){
        comboCoursesID3.removeAllItems();
        for(String s : coursesIDData){
            comboCoursesID3.addItem(s);
        }
    }

    public void setComboCursuriStergere(String[] coursesIDData){
        comboCursuriStergere.removeAllItems();
        for(String s : coursesIDData){
            comboCursuriStergere.addItem(s);
        }
    }

    public void setComboPonderiSeminar(String[] ponderi){
        comboPonderiSeminar.removeAllItems();
        for(String s : ponderi){
            comboPonderiSeminar.addItem(s);
        }
    }

    public void setComboPonderiLaborator(String[] ponderi){
        comboPonderiLaborator.removeAllItems();
        for(String s : ponderi){
            comboPonderiLaborator.addItem(s);
        }
    }


    public String getSelectedComboCoursesID2(){
        if(comboCoursesID2.getSelectedItem() == null)
            return "0";
        return comboCoursesID2.getSelectedItem().toString();
    }

    public String getSelectedComboCoursesID3(){
        if(comboCoursesID3.getSelectedItem() == null)
            return "0";
        return comboCoursesID3.getSelectedItem().toString();
    }

    public int getSelectedComboWeekDays(){
        if(comboWeekDays.getSelectedItem() == null)
            return 0;
        switch (comboWeekDays.getSelectedItem().toString()) {
            case "Luni" -> {
                return 1;
            }
            case "Marti" -> {
                return 2;
            }
            case "Miercuri" -> {
                return 3;
            }
            case "Joi" -> {
                return 4;
            }
            case "Vineri" -> {
                return 5;
            }
            default -> {
                return 0;
            }
        }
    }

    public int getSelectedComboActivities(){
        if(comboActivities.getSelectedItem() == null)
            return -1;
        switch (comboActivities.getSelectedItem().toString()) {
            case "Curs" -> {
                return 0;
            }
            case "Seminar" -> {
                return 1;
            }
            default -> {
                return 2;
            }
        }
    }

    public boolean getCheckBoxLaborator(){
        return checkBoxLaborator.isSelected();
    }

    public boolean getCheckBoxSeminar(){
        return checkBoxSeminar.isSelected();
    }

    public String getPondereCurs(){
        if(comboPonderiCurs.getSelectedItem() == null)
            return "0";
        return comboPonderiCurs.getSelectedItem().toString();
    }

    public String getPondereSeminar(){
        if(comboPonderiSeminar.getSelectedItem() == null)
            return "0";
        return comboPonderiSeminar.getSelectedItem().toString();
    }

    public String getPondereLaborator(){
        if(comboPonderiLaborator.getSelectedItem() == null)
            return "0";
        return comboPonderiLaborator.getSelectedItem().toString();
    }

    public String getSelectedComboStartHour(){
        if(comboStartHour.getSelectedItem() == null)
            return "0";
        return comboStartHour.getSelectedItem().toString();
    }

    public String getSelectedComboFinalHour(){
        if(comboFinalHour.getSelectedItem() == null)
            return "0";
        return comboFinalHour.getSelectedItem().toString();
    }

    public void setComboIDGrupMesaje(String[] grupuri){
        comboIDGrupMesaje.removeAllItems();
        for(String s : grupuri){
            comboIDGrupMesaje.addItem(s);
        }
    }

    public int getComboIDGrupMesaje(){
        if(comboIDGrupMesaje.getSelectedItem() == null)
            return 0;
        return Integer.parseInt(comboIDGrupMesaje.getSelectedItem().toString());
    }

    public String getComboCursuriSterge(){
        if(comboCursuriStergere.getSelectedItem() == null)
            return "0";
        return comboCursuriStergere.getSelectedItem().toString();
    }

    public String getTextFieldMesajGrup(){
        if(textFieldMesajGrup.getText() == null)
            return "0";
        return textFieldMesajGrup.getText();
    }


    public String getTextfieldIDCurs(){
        return textFieldCourseID.getText();
    }

    public String getCourseDescription(){
        return textFieldCourseDescription.getText();
    }

    //Listeners
    public void addCoursesMenuListener(ActionListener actionListener){
        coursesMenu.addActionListener(actionListener);
    }

    public void addCatalogueMenuListener(ActionListener actionListener){
        catalogueMenu.addActionListener(actionListener);
    }

    public void addScheduleCourseComboListener(ActionListener actionListener){
        comboActivities.addActionListener(actionListener);
    }

    public void addScheduleCourseMenuListener(ActionListener actionListener){
        scheduleCourseMenu.addActionListener(actionListener);
    }

    public void addScheduleCourseButtonListener(ActionListener actionListener){
        scheduleCourseButton.addActionListener(actionListener);
    }

    public void addPonderiButtonListener(ActionListener actionListener){
        ponderiButton.addActionListener(actionListener);
    }

    public void addCreateCourseButtonListener(ActionListener actionListener){
        createCourseButton.addActionListener(actionListener);
    }

    public void addComboCNListener(ActionListener actionListener){
        comboCN.addActionListener(actionListener);
    }

    public void addNoteazaListener(ActionListener actionListener){
        noteaza.addActionListener(actionListener);
    }

    public void addComboAddCourses(ActionListener actionListener){
        comboAddCourses.addActionListener(actionListener);
    }
    public void addComboAddUsersCourses(ActionListener actionListener){
        comboAddUsersCourses.addActionListener(actionListener);
    }
    public void addButtonAddUserCourses(ActionListener actionListener) {
        buttonAddUserCourses.addActionListener(actionListener);
    }

    public void addComboPonderiCurs(ActionListener actionListener){
        comboPonderiCurs.addActionListener(actionListener);
    }

    public void addComboPonderiSeminar(ActionListener actionListener){
        comboPonderiSeminar.addActionListener(actionListener);
    }

    public void addChatListener(ActionListener nt) {
        chatMenu.addActionListener(nt);
    }
    public void addButtonTrimiteMesajGrup(ActionListener nt) {
        buttonTrimiteMesajGrup.addActionListener(nt);
    }
    public void addComboIDGrupMesaje(ActionListener nt) {
        comboIDGrupMesaje.addActionListener(nt);
    }

    public void addGrupurileMele(ActionListener nt) {
        groupsMenu.addActionListener(nt);
    }

    public void addButtonStergeCursListener(ActionListener actionListener){
        buttonStergeCurs.addActionListener(actionListener);
    }

}
