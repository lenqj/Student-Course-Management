package view.home.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class AdminHome extends UserHome{
    private JPanel userManagementPanel;
    private JMenuItem userManagementMenu;
    public DefaultTableModel tableModelUsers;

    private JComboBox comboUsers;
    private JComboBox comboID;
    private JComboBox comboRoluri;
    private JButton buttonUsers;
    private JButton buttonRoluri;

    public AdminHome(){
        super();

        createUserManagementPanel();

        contentPanel.add(userManagementPanel, "11");
    }

    public void createMenuPanel(){
        super.createMenuPanel();

        Icon iconAdmin = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconAdmin.png")));
        super.setContIcon(iconAdmin);

        ImageIcon iconManagement = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconManagement.png")));
        ImageIcon iconUser = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\iconProfil.png")));

        Image image = iconManagement.getImage();
        Image newimg = image.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        iconManagement = new ImageIcon(newimg);

        JMenu userManagement = new JMenu("Management");
        userManagement.setIcon(iconManagement);

        userManagementMenu = new JMenuItem("Useri");
        userManagementMenu.setIcon(iconUser);

        userManagement.addSeparator();
        userManagement.add(userManagementMenu);
        userManagement.addSeparator();
        menuBar.add(userManagement);
    }

    public void createUserManagementPanel(){
        userManagementPanel = new JPanel();
        userManagementPanel.setLayout(new BoxLayout(userManagementPanel, BoxLayout.Y_AXIS));
        userManagementPanel.setBackground(Color.LIGHT_GRAY.darker());

        Vector<Vector<String>> data=new Vector<>();
        Vector<String> tableHead =new Vector<>();
        tableHead.add("CNP");
        tableHead.add("Nume");
        tableHead.add("Prenume");
        tableHead.add("Adresa");
        tableHead.add("Telefon");
        tableHead.add("Email");
        tableHead.add("IBAN");
        tableHead.add("Contract");
        tableHead.add("Rol");
        tableHead.add("Actiune");


        tableModelUsers = new DefaultTableModel(data, tableHead);
        JTable table=new JTable(tableModelUsers);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelRoluri = new JPanel(new FlowLayout());
        JLabel labelID = new JLabel("ID:");
        JLabel labelRoluri = new JLabel("Rol:");
        String[] roluri = {"Student", "Profesor", "Admin"};
        comboID = new JComboBox();
        comboRoluri = new JComboBox(roluri);
        buttonRoluri = new JButton("Modifica!");
        panelRoluri.add(labelID);
        panelRoluri.add(comboID);
        panelRoluri.add(labelRoluri);
        panelRoluri.add(comboRoluri);
        panelRoluri.add(buttonRoluri);

        JPanel panelUsers = new JPanel(new FlowLayout());
        JLabel labelUsers = new JLabel("User");
        comboUsers = new JComboBox();
        buttonUsers = new JButton("Sterge!");
        panelUsers.add(labelUsers);
        panelUsers.add(comboUsers);
        panelUsers.add(buttonUsers);

        userManagementPanel.add(scrollPane);
        userManagementPanel.add(panelRoluri);
        userManagementPanel.add(panelUsers);
    }

    //Util Methods
    public void showUserManagementPanel(){
        cardLayout.show(contentPanel, "11");
    }

    public void setUsersData(Object[] usersData){
        this.tableModelUsers.addRow(usersData);
    }

    public String getSelectedCNP() {
        if(comboUsers.getSelectedItem() == null)
            return "0";
        return comboUsers.getSelectedItem().toString();
    }

    public String getSelectedID(){
        if(comboID.getSelectedItem() == null)
            return "0";
        return comboID.getSelectedItem().toString();
    }

    public String getSelectedRol(){
        if(comboRoluri.getSelectedItem() == null)
            return "0";
        return comboRoluri.getSelectedItem().toString();
    }

    public void addComboUsers(String[] Users) {
        comboUsers.removeAllItems();
        for (String s : Users) {
            comboUsers.addItem(s);
        }
    }

    public void addComboID(String[] ID){
        if(ID == null)
            return;
        comboID.removeAllItems();
        for(String s : ID){
            comboID.addItem(s);
        }
    }


    //Listeners
    public void addUserManagementMenuListener(ActionListener actionListener){
        userManagementMenu.addActionListener(actionListener);
    }
    public void addUserManagementButtonListener(ActionListener actionListener){
        buttonUsers.addActionListener(actionListener);
    }

    public void addButtonRoluriListener(ActionListener actionListener){
        buttonRoluri.addActionListener(actionListener);
    }

}
