package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Controller {
    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        //User Login
        view.userLogin.addBtnInregistrareListener(new BtnInregistareListener());
        view.userLogin.addBtnInapoiListener(new BtnInapoiListener());
        view.userLogin.addBtnLogareListener(new BtnLogareListener());
        view.userLogin.addBtnInregistrareUtilizatorListener(new BtnInregistrareUtilizatorListener());

        //User Home
        view.userHome.addProfilulMeuListener(new ProfilulMeuListener());
        view.userHome.addEditareProfilListener(new EditareProfilulMeuListener());
        view.userHome.addDelogareListener(new DelogareListener());
        view.userHome.addEditareProfilButtonListener(new EditareProfilButtonListener());
        view.userHome.addStergeContListener(new StergeContListener());

        //Student Home
        view.studentHome.addProfilulMeuListener(new StudentProfilulMeuListener());
        view.studentHome.addEditareProfilListener(new StudentEditareProfilulMeuListener());
        view.studentHome.addDelogareListener(new DelogareListener());
        view.studentHome.addEditareProfilButtonListener(new StudentEditareProfilButtonListener());
        view.studentHome.addCreareGrupListener(new CreareGrupListener());
        view.studentHome.addBtnCreareGrupListener(new BtnCreareGrupListener());
        view.studentHome.addGrupurileMeleListener(new GrupurileMeleListener());
        view.studentHome.addCursurileMeleListener(new CursurileMeleListener());
        view.studentHome.addOrarListener(new OrarListener());
        view.studentHome.addNoteListener(new NoteListener());
        view.studentHome.addBtnInvitaInGrupListener(new BtnInvitaInGrupListener());
        view.studentHome.addComboIDGrupListener(new ComboIDGrupListener());
        view.studentHome.addChatListener(new MesajeListener());
        view.studentHome.addComboIDGrupMesajeListener(new ComboIDGrupMesajListener());
        view.studentHome.addStergeContListener(new StudentStergeContListener());
        view.studentHome.addCursuriButtonListener(new BtnCursuriListener());
        view.studentHome.addButtonTrimiteMesajGrupListener(new ButtonTrimiteMesajGrupListener());
        view.studentHome.addButtonSugestiiInvita(new ButtonSugestiiInvitaListener());
        view.studentHome.addButtonInvitaProfesorListener(new ButtonInvitaProfesorListener());
        view.studentHome.addButtonStergeGrupListener(new ButtonStergeGrupListener());

        //Teacher Home
        view.teacherHome.addProfilulMeuListener(new TeacherProfilulMeuListener());
        view.teacherHome.addEditareProfilListener(new TeacherEditareProfilulMeuListener());
        view.teacherHome.addDelogareListener(new DelogareListener());
        view.teacherHome.addEditareProfilButtonListener(new TeacherEditareProfilButtonListener());
        view.teacherHome.addCoursesMenuListener(new CoursesPanelListener());
        view.teacherHome.addCatalogueMenuListener(new CataloguePanelListener());
        view.teacherHome.addScheduleCourseMenuListener(new ScheduleCourseMenuListener());
        view.teacherHome.addScheduleCourseComboListener(new ScheduleCourseComboListener());
        view.teacherHome.addScheduleCourseButtonListener(new ScheduleCourseButtonListener());
        view.teacherHome.addStergeContListener(new TeacherStergeContListener());
        view.teacherHome.addPonderiButtonListener(new PonderiButtonListener());
        view.teacherHome.addNoteazaListener(new NoteazaListener());
        view.teacherHome.addComboCNListener(new ComboCNListener());
        view.teacherHome.addCreateCourseButtonListener(new CreateCourseButtonListener());
        view.teacherHome.addComboAddCourses(new ComboAddCoursesListener());
        view.teacherHome.addComboAddUsersCourses(new ComboAddUsersCoursesListener());
        view.teacherHome.addButtonAddUserCourses(new ButtonAddUserCoursesListener());
        view.teacherHome.addComboPonderiCurs(new ComboPonderiCursListener());
        view.teacherHome.addComboPonderiSeminar(new ComboPonderiSeminarListener());
        view.teacherHome.addChatListener(new ChatTeacherListener());
        view.teacherHome.addComboIDGrupMesaje(new ComboIDGrupMesajeListener());
        view.teacherHome.addButtonTrimiteMesajGrup(new ButtonTrimiteMesajGrupTeacherListener());
        view.teacherHome.addGrupurileMele(new GrupurileMeleTeacherListener());
        view.teacherHome.addButtonStergeCursListener(new ButtonCursuriStergereListener());

        //Admin Home
        view.adminHome.addProfilulMeuListener(new AdminProfilulMeuListener());
        view.adminHome.addEditareProfilListener(new AdminEditareProfilulMeuListener());
        view.adminHome.addDelogareListener(new DelogareListener());
        view.adminHome.addEditareProfilButtonListener(new AdminEditareProfilButtonListener());
        view.adminHome.addUserManagementMenuListener(new UserManagementMenuListener());
        view.adminHome.addStergeContListener(new AdminStergeContListener());
        view.adminHome.addUserManagementButtonListener(new BtnUsersListener());
        view.adminHome.addButtonRoluriListener(new ButtonRoluriListener());

        //Super Admin Home
        view.superAdminHome.addProfilulMeuListener(new SuperAdminProfilulMeuListener());
        view.superAdminHome.addEditareProfilListener(new SuperAdminEditareProfilulMeuListener());
        view.superAdminHome.addDelogareListener(new DelogareListener());
        view.superAdminHome.addEditareProfilButtonListener(new SuperAdminEditareProfilButtonListener());
        view.superAdminHome.addUserManagementMenuListener(new SUserManagementMenuListener());
        view.superAdminHome.addUserManagementButtonListener(new SBtnUsersListener());
        view.superAdminHome.addButtonRoluriListener(new SButtonRoluriListener());
    }

    //                                        --- Creating inner classes for events ---

    //          USER LOGIN
    //Panel Inregistrare
    class BtnInregistareListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.userLogin.showPanelInregistrare();
        }
    }

    //Buton inapoi spre Login
    class BtnInapoiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.userLogin.showPanelLogare();
        }
    }

    //Login
    class BtnLogareListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.getConnection();
            String CNP = view.userLogin.getCNP();
            String parola = view.userLogin.getParola();
            if (model.sqlQueries.loginQuery(model.getConnectionInstance(), CNP, parola)) {
                model.setCNP(CNP);
                String idRol = model.sqlQueries.getUserRole(model.getConnectionInstance(), model.getCNP());
                switch (idRol) {
                    case "1" -> setProfileData();
                    case "2" -> setStudentProfileData();
                    case "3" -> setTeacherProfileData();
                    case "4" -> setAdminProfileData();
                    default -> setSuperAdminProfileData();
                }

                view.showPanel(idRol);
                view.userLogin.clearFields();
            } else {
                model.closeConnection();
            }
        }
    }

    //Inregistare
    class BtnInregistrareUtilizatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.getConnection();
            String nume = view.userLogin.getCampNume();
            String prenume = view.userLogin.getCampPrenume();
            String email = view.userLogin.getCampEmail();
            String CNP = view.userLogin.getCampCNP();
            String telefon = view.userLogin.getCampTelefon();
            String password = view.userLogin.getCampParolaRegister();


            if (!nume.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Numele trebuie sa contina doar litere!");
                return;
            }

            if (!prenume.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Prenumele trebuie sa contina doar litere!");
                return;
            }

            if (!Pattern.compile("^(.+)@(.+)$").matcher(email).matches()) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Adresa de email nu poate fi validata!");
                return;
            }

            if (!CNP.matches("[0-9]+") || CNP.length() != 13) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "CNP-ul nu poate fi validat!");
                return;
            }

            if (!telefon.matches("[0-9]+") || telefon.length() != 10) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Numarul de telefon nu poate fi validat!");
                return;
            }

            if (!Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$").matcher(password).matches()) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Parola trebuie sa contina: aA1& si lungime intre 8 si 20!");
                return;
            }

            if (model.sqlQueries.registerQuery(model.getConnectionInstance(), nume, prenume, email, CNP, telefon, password)) {
                model.setCNP(CNP);
                setProfileData();
                view.showPanel("1");
                view.userLogin.clearFields();
            } else
                model.closeConnection();
        }
    }

    //          USER HOME
    //Logout

    class ProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setProfileData();
            view.userHome.showProfilPanel();
        }
    }

    class EditareProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setEditareProfil();
            view.userHome.showEditareProfilPanel();
        }
    }

    class DelogareListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showPanel("0");
            model.closeConnection();
        }
    }

    class StergeContListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.sqlQueries.deleteUserAccount(model.getConnectionInstance(), model.getCNP());
            view.showPanel("0");
            model.closeConnection();
        }
    }

    class EditareProfilButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String adresa = view.userHome.getAdresa();
            String telefon = view.userHome.getTelefon();
            String email = view.userHome.getEmail();
            String IBAN = view.userHome.getIBAN();
            String contract = view.userHome.getContract();
            model.sqlQueries.editeazaCont(model.getConnectionInstance(), model.getCNP(), adresa, telefon, email, IBAN, contract);
        }
    }

    //          STUDENT HOME
    //Profil
    class StudentProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setStudentProfileData();
            view.studentHome.showProfilPanel();
        }
    }

    class StudentEditareProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setStudentEditareProfil();
            view.studentHome.showEditareProfilPanel();
        }
    }

    class StudentEditareProfilButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String adresa = view.studentHome.getAdresa();
            String telefon = view.studentHome.getTelefon();
            String email = view.studentHome.getEmail();
            String IBAN = view.studentHome.getIBAN();
            String contract = view.studentHome.getContract();
            String nrOre = view.studentHome.getTextFieldNrOre();
            model.sqlQueries.editeazaContStudent(model.getConnectionInstance(), model.getCNP(), adresa, telefon, email, IBAN, contract, nrOre);
        }
    }

    class StudentStergeContListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.sqlQueries.deleteStudentAccount(model.getConnectionInstance(), model.getCNP());
            view.showPanel("0");
            model.closeConnection();
        }
    }

    //Grupuri
    class CreareGrupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setCreareGrupPanel();
            setSugestii();
            setProfesoriCursuri();
            setStergeGrup();
            view.studentHome.showCreareGrupPanel();
        }
    }

    class ButtonStergeGrupListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String selectedG = view.studentHome.getComboStergeGrup();
            model.sqlQueries.stergeGrup(model.getConnectionInstance(), selectedG);
            setStergeGrup();
            view.studentHome.showCreareGrupPanel();
        }
    }

    class BtnCreareGrupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String CNP = model.getCNP();
            String descriere = view.studentHome.getDescriereGrup();
            String IDGrup = view.studentHome.getIDGrup();
            String error = model.sqlQueries.creareGrup(model.getConnectionInstance(), CNP, descriere, IDGrup);
            try{
                if(error.equals("Exista deja un grup cu acest id!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Exista deja un grup cu ID-ul " + IDGrup + "!");
                }
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(new JButton("OK!"), "Grupul " + IDGrup + " a fost creat cu succes!");
            }
            setCreareGrupPanel();
            setSugestii();
            setProfesoriCursuri();
            setStergeGrup();
            String[] IDGrup1 = model.sqlQueries.getGroupsID(model.getConnectionInstance(), model.getCNP());
            view.studentHome.addComboIDGrup(IDGrup1);
            view.studentHome.showCreareGrupPanel();
        }
    }

    class BtnInvitaInGrupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.studentHome.getSelectedCNP();
            String IDgrup = view.studentHome.getSelectedIDGrup();
            String error = model.sqlQueries.invitaInGrup(model.getConnectionInstance(), model.getCNP(), email, IDgrup);
            try{
                if(error.equals("Studentul a fost deja invitat!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Studentul a fost deja invitat!");
                }
                if(error.equals("Nu esti admin ca sa inviti persoane! / Nu exista un grup cu acest id!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu esti admin ca sa inviti persoane! / Nu exista un grup cu acest id!");
                }
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(new JButton("OK!"), "Studentul " + email + " a fost invitat in grupul " + IDgrup +"!");
            }
            String selectedItem = view.studentHome.getSelectedIDGrup();
            String[] usersID = model.sqlQueries.getUsersID(model.getConnectionInstance(), model.getCNP(), selectedItem);
            view.studentHome.addComboUser(usersID);
            view.studentHome.showCreareGrupPanel();
        }
    }

    class GrupurileMeleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setGrupurileMele();
            view.studentHome.showGrupurileMelePanel();
        }
    }

    class ComboIDGrupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selectedItem = view.studentHome.getSelectedIDGrup();
            String[] usersID = model.sqlQueries.getUsersID(model.getConnectionInstance(), model.getCNP(), selectedItem);
            view.studentHome.addComboUser(usersID);
            view.studentHome.showCreareGrupPanel();
        }
    }

    class ComboIDGrupMesajListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIDGrup = view.studentHome.getSelectedIDGrupMesaj();
            setMesajeGrup(selectedIDGrup);
            view.studentHome.showMesajePanel();
        }
    }

    class MesajeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIDGrup = view.studentHome.getSelectedIDGrupMesaj();
            view.studentHome.clearTextFieldMesajGrup();
            String[] groups = model.sqlQueries.getGroupsMemberID(model.getConnectionInstance(), model.getCNP());
            view.studentHome.addComboIDGrupMesaje(groups);
            setMesajeGrup(selectedIDGrup);
            view.studentHome.showMesajePanel();
        }
    }
    class ButtonTrimiteMesajGrupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIDGrupMesaj = view.studentHome.getSelectedIDGrupMesaj();
            String mesajGrup = view.studentHome.getTextFieldMesajGrup();
            String error = model.sqlQueries.trimiteMesajGrup(model.getConnectionInstance(), model.getCNP(), selectedIDGrupMesaj, mesajGrup);
            try{
                if(error.equals("Nu faceti parte din acest grup!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu faceti parte din acest grup!");
                }
                if(error.equals("Grupul nu exista!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Grupul nu exista!");
                }
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(new JButton("OK!"), "Mesaj trimis cu succes!");
            }
            view.studentHome.clearTextFieldMesajGrup();
            String[] groups = model.sqlQueries.getGroupsMemberID(model.getConnectionInstance(), model.getCNP());
            view.studentHome.addComboIDGrupMesaje(groups);
            setMesajeGrup(selectedIDGrupMesaj);
            view.studentHome.showMesajePanel();
        }
    }

    class ButtonSugestiiInvitaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.studentHome.getComboSugestiiCNP();
            String IDgrup = view.studentHome.getComboSugestiiID();
            String error = model.sqlQueries.invitaInGrup(model.getConnectionInstance(), model.getCNP(), email, IDgrup);
            try{
                if(error.equals("Studentul a fost deja invitat!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Studentul a fost deja invitat!");
                }
                if(error.equals("Nu esti admin ca sa inviti persoane! / Nu exista un grup cu acest id!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu esti admin ca sa inviti persoane! / Nu exista un grup cu acest id!");
                }
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(new JButton("OK!"), "Studentul " + email + " a fost invitat in grupul " + IDgrup +"!");
            }
            String selectedItem = view.studentHome.getComboSugestiiID();
            String[] usersID = model.sqlQueries.getUsersID(model.getConnectionInstance(), model.getCNP(), selectedItem);
            view.studentHome.addComboSugestiiCNP(usersID);
            view.studentHome.showCreareGrupPanel();
        }
    }

    class ButtonInvitaProfesorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.studentHome.getComboProfesorCNP();
            String IDgrup = view.studentHome.getComboInvPID();
            String error = model.sqlQueries.adaugaProfesorGrup(model.getConnectionInstance(), email, Integer.parseInt(IDgrup));
            try{
                if(error.equals("Nu exista un grup cu acest id!")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu exista un grup cu acest id!");
                }
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(new JButton("OK!"), "Profesorul " + email + " a fost invitat in grupul " + IDgrup +"!");
            }
            String[] usersID = model.sqlQueries.getTeacherCNP(model.getConnectionInstance());
            view.studentHome.addComboProfesorCNP(usersID);
            view.studentHome.showCreareGrupPanel();
        }
    }


    //Cursuri
    class CursurileMeleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] cursuri = model.sqlQueries.getCursuri(model.getConnectionInstance(), model.getCNP());
            view.studentHome.addComboCursuri(cursuri);
            setCursurileMele();
            view.studentHome.showCursurileMelePanel();
        }

    }

    class OrarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setOrar();
            view.studentHome.showOrarPanel();
        }
    }

    class NoteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setNote();
            view.studentHome.showNotePanel();
        }
    }

    class BtnCursuriListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] cursuri = model.sqlQueries.getCursuri(model.getConnectionInstance(), model.getCNP());
            view.studentHome.addComboCursuri(cursuri);

            String Curs = view.studentHome.getSelectedCursuri();
            String error = model.sqlQueries.adaugaStudentCurs(model.getConnectionInstance(), model.getCNP(), Curs);
            try{
                if(error.equals("Studentul exista deja.")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Studentul exista deja.");
                }else if(error.equals("Nu mai are ore disponibile.")) {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu mai are ore disponibile.");
                }else if(error.equals("Nu se mai pot adauga studenti la acest curs deoarece a atins numarul maxim")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu se mai pot adauga studenti la acest curs deoarece a atins numarul maxim");
                }
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(new JButton("OK!"), "Te-ai alaturat cursului cu succes!");
            }

            setCursurileMele();
            view.studentHome.showCursurileMelePanel();
        }
    }

    //          TEACHER HOME

    //Profil
    class TeacherProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setTeacherProfileData();
            view.teacherHome.showProfilPanel();
        }
    }

    class TeacherEditareProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setTeacherEditareProfil();
            view.teacherHome.showEditareProfilPanel();
        }
    }

    class TeacherEditareProfilButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String adresa = view.teacherHome.getAdresa();
            String telefon = view.teacherHome.getTelefon();
            String email = view.teacherHome.getEmail();
            String IBAN = view.teacherHome.getIBAN();
            String contract = view.teacherHome.getContract();
            String oreMin = view.teacherHome.getTextFieldOreMin();
            String oreMax = view.teacherHome.getTextFieldOreMax();
            String departament = view.teacherHome.getTextFieldDepartament();

            model.sqlQueries.editeazaContProfesor(model.getConnectionInstance(), model.getCNP(), adresa, telefon, email, IBAN, contract, oreMin, oreMax, departament);
        }
    }

    class TeacherStergeContListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.sqlQueries.deleteTeacherAccount(model.getConnectionInstance(), model.getCNP());
            view.showPanel("0");
            model.closeConnection();
        }
    }

    //Activities
    class CoursesPanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] cursuri = model.sqlQueries.getCursuriProfesor(model.getConnectionInstance(), model.getCNP());
            view.teacherHome.setComboAddCourses(cursuri);
            setCourses();
            setStergeCursuri();
            view.teacherHome.showCoursesPanel();
        }
    }

    class NoteazaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String student = view.teacherHome.getComboIn();
            String curs = view.teacherHome.getComboCN();
            String tipactivitate = view.teacherHome.getComboTipActivitate();
            String nota = view.teacherHome.getComboNota();
            model.sqlQueries.notareStudent(model.getConnectionInstance(), model.getCNP(), student, curs, tipactivitate, nota);
            model.sqlQueries.seteazaNotaFinala(model.getConnectionInstance(), student, Integer.parseInt(curs));
            setCatalogue();
        }
    }

    class ComboCNListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String curs = view.teacherHome.getComboCN();
            String[] studenti = model.sqlQueries.getStudentiNotare(model.getConnectionInstance(), model.getCNP(), curs);
            view.teacherHome.setComboIN(studenti);
            setCatalogue();
        }
    }

    class CreateCourseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ID = view.teacherHome.getTextfieldIDCurs();
            String desc = view.teacherHome.getCourseDescription();
            model.sqlQueries.creareCurs(model.getConnectionInstance(), model.getCNP(), ID, desc);
            if(view.teacherHome.getCheckBoxSeminar()){
                model.sqlQueries.creareSeminar(model.getConnectionInstance(), model.getCNP(), ID);
            }
            if(view.teacherHome.getCheckBoxLaborator()){
                model.sqlQueries.creareLaborator(model.getConnectionInstance(), model.getCNP(), ID);
            }
            setCourses();
        }
    }

    class CataloguePanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setCatalogue();
            view.teacherHome.showCataloguePanel();
        }
    }

    class ScheduleCourseComboListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] coursesID = null;
            if (view.teacherHome.getSelectedComboActivities() == 0) {
                coursesID = model.sqlQueries.getCoursesID(model.getConnectionInstance(), model.getCNP());
            }
            if (view.teacherHome.getSelectedComboActivities() == 1) {
                coursesID = model.sqlQueries.getSeminarsID(model.getConnectionInstance(), model.getCNP());
            }
            if (view.teacherHome.getSelectedComboActivities() == 2) {
                coursesID = model.sqlQueries.getLaboratoriesID(model.getConnectionInstance(), model.getCNP());
            }
            view.teacherHome.setComboCoursesID2(coursesID);
            view.teacherHome.showScheduleCoursePanel();
        }
    }

    class ScheduleCourseMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] coursesID = model.sqlQueries.getCoursesID(model.getConnectionInstance(), model.getCNP());
            view.teacherHome.setComboCoursesID3(coursesID);
            view.teacherHome.showScheduleCoursePanel();
        }
    }


    class ScheduleCourseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int ID_CursSelectat = Integer.parseInt(view.teacherHome.getSelectedComboCoursesID2());
            int Zi_Selectata = view.teacherHome.getSelectedComboWeekDays();
            String OraStart = view.teacherHome.getSelectedComboStartHour();
            String OraFinal = view.teacherHome.getSelectedComboFinalHour();
            String[] coursesID = null;
            if (view.teacherHome.getSelectedComboActivities() == 0) {
                String error = model.sqlQueries.programeazaCurs(model.getConnectionInstance(), ID_CursSelectat, Zi_Selectata, OraStart, OraFinal);
                try{
                    if(error.equals("Programul se suprapune cu alta activitate din cadrul acestei materii!")){
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Programul se suprapune cu alta activitate din cadrul acestei materii!");
                    }else if(error.equals("Acest curs este deja programat!")) {
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Acest curs este deja programat!");
                    }else if(error.equals("Cursul cu acest ID nu s-a gasit!")){
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Cursul cu acest ID nu s-a gasit!");
                    }
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Curs programat cu succes!");
                }
                coursesID = model.sqlQueries.getCoursesID(model.getConnectionInstance(), model.getCNP());
            }
            if (view.teacherHome.getSelectedComboActivities() == 1) {
                String error = model.sqlQueries.programeazaSeminar(model.getConnectionInstance(), ID_CursSelectat, Zi_Selectata, OraStart, OraFinal);
                try{
                    if(error.equals("Programul se suprapune cu alta activitate din cadrul acestei materii!")){
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Programul se suprapune cu alta activitate din cadrul acestei materii!");
                    }else if(error.equals("Acest seminar este deja programat!")) {
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Acest seminar este deja programat!");
                    }else if(error.equals("Cursul cu acest ID nu s-a gasit!")){
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Cursul cu acest ID nu s-a gasit!");
                    }
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Seminar programat cu succes!");
                }

                coursesID = model.sqlQueries.getSeminarsID(model.getConnectionInstance(), model.getCNP());
            }
            if (view.teacherHome.getSelectedComboActivities() == 2) {
                String error = model.sqlQueries.programeazaLaborator(model.getConnectionInstance(), ID_CursSelectat, Zi_Selectata, OraStart, OraFinal);
                try{
                    if(error.equals("Programul se suprapune cu alta activitate din cadrul acestei materii!")){
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Programul se suprapune cu alta activitate din cadrul acestei materii!\"");
                    }else if(error.equals("Acest laborator este deja programat!")) {
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Acest laborator este deja programat!\"");
                    }else if(error.equals("Cursul cu acest ID nu s-a gasit!")){
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Cursul cu acest ID nu s-a gasit!");
                    }
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Laborator programat cu succes!");
                }
                coursesID = model.sqlQueries.getLaboratoriesID(model.getConnectionInstance(), model.getCNP());
            }
            view.teacherHome.setComboCoursesID2(coursesID);
            view.teacherHome.showScheduleCoursePanel();
        }
    }

    class ComboPonderiCursListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String pondereCurs = view.teacherHome.getPondereCurs();
            int ponderiSize = 100 - Integer.parseInt(pondereCurs);
            String ponderi[] = new String[ponderiSize/10];
            for(int i = 10; i <= ponderiSize; i+=10){
                ponderi[i/10 - 1] = String.valueOf(i);
            }
            view.teacherHome.setComboPonderiSeminar(ponderi);
            view.teacherHome.showScheduleCoursePanel();
        }
    }

    class ComboPonderiSeminarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String pondereCurs = view.teacherHome.getPondereCurs();
            String pondereSeminar = view.teacherHome.getPondereSeminar();
            int ponderiSize = 100 - Integer.parseInt(pondereCurs) - Integer.parseInt(pondereSeminar);
            String ponderi[] = new String[ponderiSize/10];
            for(int i = 10; i <= ponderiSize; i+=10){
                ponderi[i/10 - 1] = String.valueOf(i);
            }
            view.teacherHome.setComboPonderiLaborator(ponderi);
            view.teacherHome.showScheduleCoursePanel();
        }
    }

    class PonderiButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ID_Curs = view.teacherHome.getSelectedComboCoursesID3();
            String pondereCurs = view.teacherHome.getPondereCurs();
            String pondereSeminar = view.teacherHome.getPondereSeminar();
            String pondereLaborator = view.teacherHome.getPondereLaborator();
            model.sqlQueries.setPonderi(model.getConnectionInstance(), model.getCNP(), ID_Curs, pondereCurs, pondereSeminar, pondereLaborator);
            JOptionPane.showMessageDialog(new JButton("OK!"), "Ponderile au fost setate!");
        }
    }

    class ComboAddCoursesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String IDCurs = view.teacherHome.getComboAddCourses();
            String[] useri = model.sqlQueries.getNoUseriCursuri(model.getConnectionInstance(), model.getCNP(), IDCurs);
            view.teacherHome.setComboAddUsersCourses(useri);
            setCourses();
            view.teacherHome.showCoursesPanel();
        }
    }

    class ComboAddUsersCoursesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setCourses();
            view.teacherHome.showCoursesPanel();
        }
    }
    class ButtonAddUserCoursesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String IDCurs = view.teacherHome.getComboAddCourses();;
            String CNPStudent = view.teacherHome.getComboAddUsersCourses();
            String error = model.sqlQueries.adaugaStudentCurs(model.getConnectionInstance(), CNPStudent, IDCurs);
            try{
                if(error.equals("Studentul exista deja.")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Studentul exista deja.");
                }else if(error.equals("Nu mai are ore disponibile.")) {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu mai are ore disponibile.");
                }else if(error.equals("Nu se mai pot adauga studenti la acest curs deoarece a atins numarul maxim")){
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Nu se mai pot adauga studenti la acest curs deoarece a atins numarul maxim");
                }
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(new JButton("OK!"), "Student adaugat cu succes!");
            }
            String[] useri = model.sqlQueries.getNoUseriCursuri(model.getConnectionInstance(), model.getCNP(), IDCurs);
            view.teacherHome.setComboAddUsersCourses(useri);
            setCourses();
            view.teacherHome.showCoursesPanel();
        }
    }

    class ButtonCursuriStergereListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String id = view.teacherHome.getComboCursuriSterge();
            model.sqlQueries.stergeCurs(model.getConnectionInstance(), id);
            String[] cursuri = model.sqlQueries.getCursuriProfesor(model.getConnectionInstance(), model.getCNP());
            view.teacherHome.setComboAddCourses(cursuri);
            setCourses();
            setStergeCursuri();
            view.teacherHome.showCoursesPanel();
        }
    }

    class ChatTeacherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIDGrup = view.teacherHome.getComboIDGrupMesaje();
            String[] grupuri = model.sqlQueries.getTeacherGroups(model.getConnectionInstance(), model.getCNP());
            view.teacherHome.setComboIDGrupMesaje(grupuri);
            setMesajeGrupTeacher(selectedIDGrup);
            view.teacherHome.showMesajePanel();
        }
    }

    class ComboIDGrupMesajeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIDGrup = view.teacherHome.getComboIDGrupMesaje();
            setMesajeGrupTeacher(selectedIDGrup);
            view.teacherHome.showMesajePanel();
        }
    }

    class ButtonTrimiteMesajGrupTeacherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIDGrup = view.teacherHome.getComboIDGrupMesaje();
            String mesaj = view.teacherHome.getTextFieldMesajGrup();
            model.sqlQueries.trimiteMesajGrup(model.getConnectionInstance(), model.getCNP(), selectedIDGrup, mesaj);
            view.teacherHome.clearTextFieldMesajGrup();
            setMesajeGrupTeacher(selectedIDGrup);
            view.teacherHome.showMesajePanel();
        }
    }

    class GrupurileMeleTeacherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setGroupsTeacher();
            view.teacherHome.showGrupurileMelePanel();
        }
    }


    //          ADMIN HOME
    //Profil
    class AdminProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setAdminProfileData();
            view.adminHome.showProfilPanel();
        }
    }

    class AdminEditareProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setAdminEditareProfil();
            view.adminHome.showEditareProfilPanel();
        }
    }

    class AdminEditareProfilButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String adresa = view.adminHome.getAdresa();
            String telefon = view.adminHome.getTelefon();
            String email = view.adminHome.getEmail();
            String IBAN = view.adminHome.getIBAN();
            String contract = view.adminHome.getContract();
            model.sqlQueries.editeazaCont(model.getConnectionInstance(), model.getCNP(), adresa, telefon, email, IBAN, contract);
        }
    }

    class UserManagementMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setUserManagementPanel();
            setRoluriPanel();
            String idRol = model.sqlQueries.getUserRole(model.getConnectionInstance(), model.getCNP());
            String[] users = model.sqlQueries.getUsers(model.getConnectionInstance(), model.getCNP(), Integer.parseInt(idRol));
            view.adminHome.addComboUsers(users);
            view.adminHome.showUserManagementPanel();
        }
    }

    class BtnUsersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String CNP = view.adminHome.getSelectedCNP();
            model.sqlQueries.deleteUserAccount(model.getConnectionInstance(), CNP);
            setUserManagementPanel();
            String idRol = model.sqlQueries.getUserRole(model.getConnectionInstance(), model.getCNP());
            String[] users = model.sqlQueries.getUsers(model.getConnectionInstance(), model.getCNP(), Integer.parseInt(idRol));
            view.adminHome.addComboUsers(users);
            view.adminHome.showUserManagementPanel();
        }
    }

    class ButtonRoluriListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String ID = view.adminHome.getSelectedID();
            String rol = view.adminHome.getSelectedRol();
            if(rol.equals("Student")){
                model.sqlQueries.insertStudent(model.getConnectionInstance(), ID);
            }else if(rol.equals("Profesor")){
                model.sqlQueries.insertProfesor(model.getConnectionInstance(), ID);
            }else{
                model.sqlQueries.insertAdmin(model.getConnectionInstance(), ID);
            }
            setRoluriPanel();
        }
    }

    class AdminStergeContListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.sqlQueries.deleteAdminAccount(model.getConnectionInstance(), model.getCNP());
            view.showPanel("0");
            model.closeConnection();
        }
    }

    //SUPER ADMIN HOME
    //Profil
    class SuperAdminProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setSuperAdminProfileData();
            view.superAdminHome.showProfilPanel();
        }
    }

    class SuperAdminEditareProfilulMeuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setSuperAdminEditareProfil();
            view.superAdminHome.showEditareProfilPanel();
        }
    }

    class SuperAdminEditareProfilButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String adresa = view.superAdminHome.getAdresa();
            String telefon = view.superAdminHome.getTelefon();
            String email = view.superAdminHome.getEmail();
            String IBAN = view.superAdminHome.getIBAN();
            String contract = view.superAdminHome.getContract();
            model.sqlQueries.editeazaCont(model.getConnectionInstance(), model.getCNP(), adresa, telefon, email, IBAN, contract);
        }
    }

    class SUserManagementMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setSPUserManagementPanel();
            setSRoluriPanel();
            String idRol = model.sqlQueries.getUserRole(model.getConnectionInstance(), model.getCNP());
            String[] users = model.sqlQueries.getUsers(model.getConnectionInstance(), model.getCNP(), Integer.parseInt(idRol));
            view.superAdminHome.addComboUsers(users);
            view.superAdminHome.showUserManagementPanel();
        }
    }

    class SBtnUsersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String CNP = view.superAdminHome.getSelectedCNP();
            model.sqlQueries.deleteUserAccount(model.getConnectionInstance(), CNP);
            setSPUserManagementPanel();
            String idRol = model.sqlQueries.getUserRole(model.getConnectionInstance(), model.getCNP());
            String[] users = model.sqlQueries.getUsers(model.getConnectionInstance(), model.getCNP(), Integer.parseInt(idRol));
            view.superAdminHome.addComboUsers(users);
            view.superAdminHome.showUserManagementPanel();
        }
    }

    class SButtonRoluriListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String ID = view.superAdminHome.getSelectedID();
            String rol = view.superAdminHome.getSelectedRol();
            if(rol.equals("Student")){
                model.sqlQueries.insertStudent(model.getConnectionInstance(), ID);
            }else if(rol.equals("Profesor")){
                model.sqlQueries.insertProfesor(model.getConnectionInstance(), ID);
            }else{
                model.sqlQueries.insertAdmin(model.getConnectionInstance(), ID);
            }
            setSRoluriPanel();
        }
    }


    //                                        ---Util functions---
    //USER HOME
    //Profil

    public void setProfileData() {
        ResultSet resultSet = model.sqlQueries.getProfileData(model.getConnectionInstance(), model.getCNP());
        try {
            //Titled Border
            String lastName = model.sqlQueries.setTitleBorderLastName(model.getConnectionInstance(), model.getCNP());
            String firstName = model.sqlQueries.setTitleBorderFirstName(model.getConnectionInstance(), model.getCNP());
            view.userHome.setTitledBorder(lastName, firstName);

            //Profile Data
            while (resultSet.next()) {
                String nume = resultSet.getString("Nume");
                String prenume = resultSet.getString("Prenume");

                String adresaEmail = resultSet.getString("Email");
                if (adresaEmail == null) {
                    adresaEmail = " ";
                }

                String telefon = resultSet.getString("Telefon");
                if (telefon == null) {
                    telefon = " ";
                }

                String adresa = resultSet.getString("Adresa");
                if (adresa == null) {
                    adresa = " ";
                }

                String CNP = resultSet.getString("CNP");
                view.userHome.setNume(nume);
                view.userHome.setPrenume(prenume);
                view.userHome.setAdresaEmail(adresaEmail);
                view.userHome.setTelefon(telefon);
                view.userHome.setAdresa(adresa);
                view.userHome.setCNP(CNP);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void setEditareProfil() {
        setProfileData();
    }

    //          STUDENT HOME
    //Profil

    public void setStudentProfileData() {
        ResultSet resultSet = model.sqlQueries.getProfileData(model.getConnectionInstance(), model.getCNP());
        try {
            //Titled Border
            String lastName = model.sqlQueries.setTitleBorderLastName(model.getConnectionInstance(), model.getCNP());
            String firstName = model.sqlQueries.setTitleBorderFirstName(model.getConnectionInstance(), model.getCNP());
            view.studentHome.setTitledBorder(lastName, firstName);

            //Profile Data
            while (resultSet.next()) {
                String nume = resultSet.getString("Nume");
                String prenume = resultSet.getString("Prenume");

                String adresaEmail = resultSet.getString("Email");
                if (adresaEmail == null) {
                    adresaEmail = " ";
                }

                String telefon = resultSet.getString("Telefon");
                if (telefon == null) {
                    telefon = " ";
                }

                String adresa = resultSet.getString("Adresa");
                if (adresa == null) {
                    adresa = " ";
                }

                String CNP = resultSet.getString("CNP");
                view.studentHome.setNume(nume);
                view.studentHome.setPrenume(prenume);
                view.studentHome.setAdresaEmail(adresaEmail);
                view.studentHome.setTelefon(telefon);
                view.studentHome.setAdresa(adresa);
                view.studentHome.setCNP(CNP);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void setStudentEditareProfil() {

    }

    //Grupuri
    public void setCreareGrupPanel() {
        String[] IDGrup = model.sqlQueries.getGroupsID(model.getConnectionInstance(), model.getCNP());
        String selectedItem = view.studentHome.getSelectedIDGrup();
        String[] usersID = model.sqlQueries.getUsersID(model.getConnectionInstance(), model.getCNP(), selectedItem);
        view.studentHome.addComboIDGrup(IDGrup);
        view.studentHome.addComboUser(usersID);
        view.studentHome.showCreareGrupPanel();
    }

    public void setGrupurileMele() {
        String[][] grupurileMele = model.sqlQueries.getGrupurileMele(model.getConnectionInstance(), model.getCNP());
        view.studentHome.tableModelGrupuri.setNumRows(0);
        for (int i = 0; i < grupurileMele.length; i++) {
            view.studentHome.setGrupurileMeleData(grupurileMele[i]);
        }
    }

    public void setSugestii() {
        String[] IDGrup = model.sqlQueries.getGroupsID(model.getConnectionInstance(), model.getCNP());
        view.studentHome.addComboSugestiiID(IDGrup);
        String selectedItem = view.studentHome.getComboSugestiiID();
        String[] usersID = model.sqlQueries.getUsersID(model.getConnectionInstance(), model.getCNP(), selectedItem);
        view.studentHome.addComboSugestiiCNP(usersID);
        view.studentHome.showCreareGrupPanel();
    }

    public void setProfesoriCursuri(){
        String[] IDGrup = model.sqlQueries.getGroupsID(model.getConnectionInstance(), model.getCNP());
        view.studentHome.addComboInvPID(IDGrup);
        String[] usersID = model.sqlQueries.getTeacherCNP(model.getConnectionInstance());
        view.studentHome.addComboProfesorCNP(usersID);
        view.studentHome.showCreareGrupPanel();
    }

    //Cursuri
    public void setCursurileMele() {
        String[][] cursurileMele = model.sqlQueries.getCursurileMele(model.getConnectionInstance(), model.getCNP());
        view.studentHome.tableModelCursuri.setNumRows(0);
        for (int i = 0; i < cursurileMele.length; i++) {
            view.studentHome.setCursurileMeleData(cursurileMele[i]);
        }
    }


    public void setOrar() {
        String[][] orar = model.sqlQueries.getOrar(model.getConnectionInstance(), model.getCNP());
        view.studentHome.tableModelOrar.setNumRows(0);
        for (int i = 0; i < orar.length; i++) {
            view.studentHome.setOrarData(orar[i]);
        }
    }

    public void setNote() {
        String[][] noteCurs = model.sqlQueries.getNoteCurs(model.getConnectionInstance(), model.getCNP());
        String[][] noteLaborator = model.sqlQueries.getNoteLaboratoare(model.getConnectionInstance(), model.getCNP());
        String[][] noteSeminar = model.sqlQueries.getNoteSeminarii(model.getConnectionInstance(), model.getCNP());

        view.studentHome.tableModelNote.setNumRows(0);
        for (int i = 0; i < noteCurs.length; i++) {
            view.studentHome.setNoteData(noteCurs[i]);
        }
        for (int i = 0; i < noteLaborator.length; i++) {
            view.studentHome.setNoteData(noteLaborator[i]);
        }
        for (int i = 0; i < noteSeminar.length; i++) {
            view.studentHome.setNoteData(noteSeminar[i]);
        }
    }

    public void setMesajeGrup(int selectedIDGrup) {
        String[][] mesaje = model.sqlQueries.getMesajeGrup(model.getConnectionInstance(), selectedIDGrup);
        view.studentHome.tableModelMesaje.setNumRows(0);
        for (int i = 0; i < mesaje.length; i++) {
            view.studentHome.setMesajeData(mesaje[i]);
        }
    }

    public void setStergeGrup(){
        String[] idG = model.sqlQueries.getGroupsID(model.getConnectionInstance(), model.getCNP());
        view.studentHome.addComboStergeGrup(idG);
    }

    //          TEACHER HOME
    //Profil

    public void setTeacherProfileData() {
        ResultSet resultSet = model.sqlQueries.getProfileData(model.getConnectionInstance(), model.getCNP());
        try {
            //Titled Border
            String lastName = model.sqlQueries.setTitleBorderLastName(model.getConnectionInstance(), model.getCNP());
            String firstName = model.sqlQueries.setTitleBorderFirstName(model.getConnectionInstance(), model.getCNP());
            view.teacherHome.setTitledBorder(lastName, firstName);

            //Profile Data
            while (resultSet.next()) {
                String nume = resultSet.getString("Nume");
                String prenume = resultSet.getString("Prenume");

                String adresaEmail = resultSet.getString("Email");
                if (adresaEmail == null) {
                    adresaEmail = " ";
                }

                String telefon = resultSet.getString("Telefon");
                if (telefon == null) {
                    telefon = " ";
                }

                String adresa = resultSet.getString("Adresa");
                if (adresa == null) {
                    adresa = " ";
                }

                String CNP = resultSet.getString("CNP");
                view.teacherHome.setNume(nume);
                view.teacherHome.setPrenume(prenume);
                view.teacherHome.setAdresaEmail(adresaEmail);
                view.teacherHome.setTelefon(telefon);
                view.teacherHome.setAdresa(adresa);
                view.teacherHome.setCNP(CNP);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void setTeacherEditareProfil() {

    }

    public void setCourses() {
        String[][] cursurileMele = model.sqlQueries.getCourses(model.getConnectionInstance(), model.getCNP());
        view.teacherHome.tableModelCursuri.setNumRows(0);
        for (int i = 0; i < cursurileMele.length; i++) {
            view.teacherHome.setCoursesData(cursurileMele[i]);
        }
    }

    public void setCatalogue() {
        String[][] catalog = model.sqlQueries.getCatalogue(model.getConnectionInstance(), model.getCNP());
        view.teacherHome.tableModelCatalog.setNumRows(0);
        for (int i = 0; i < catalog.length; i++) {
            view.teacherHome.setCatalogueData(catalog[i]);
        }

        String[] cursuri = model.sqlQueries.getCoursesNotare(model.getConnectionInstance(), model.getCNP());
        view.teacherHome.setComboCN(cursuri);
        String curs = view.teacherHome.getComboCN();
        String[] studenti = model.sqlQueries.getStudentiNotare(model.getConnectionInstance(), model.getCNP(), curs);
        view.teacherHome.setComboIN(studenti);
    }

    public void setStergeCursuri(){
        String[] cursuri = model.sqlQueries.getCoursesID(model.getConnectionInstance(), model.getCNP());
        view.teacherHome.setComboCursuriStergere(cursuri);
    }

    public void setMesajeGrupTeacher(int selectedIDGrup) {
        String[][] mesaje = model.sqlQueries.getMesajeGrup(model.getConnectionInstance(), selectedIDGrup);
        view.teacherHome.tableModelMesaje.setNumRows(0);
        for (int i = 0; i < mesaje.length; i++) {
            view.teacherHome.setMesajeData(mesaje[i]);
        }
    }

    public void setGroupsTeacher() {
        String[][] grupuri = model.sqlQueries.getTeacherInGroups(model.getConnectionInstance(), model.getCNP());
        view.teacherHome.tableModelGrupuri.setNumRows(0);
        for (int i = 0; i < grupuri.length; i++) {
            view.teacherHome.setGroupsData(grupuri[i]);
        }
    }

    //          ADMIN HOME
    //Profil

    public void setAdminProfileData() {
        ResultSet resultSet = model.sqlQueries.getProfileData(model.getConnectionInstance(), model.getCNP());
        try {
            //Titled Border
            String lastName = model.sqlQueries.setTitleBorderLastName(model.getConnectionInstance(), model.getCNP());
            String firstName = model.sqlQueries.setTitleBorderFirstName(model.getConnectionInstance(), model.getCNP());
            view.adminHome.setTitledBorder(lastName, firstName);

            //Profile Data
            while (resultSet.next()) {
                String nume = resultSet.getString("Nume");
                String prenume = resultSet.getString("Prenume");

                String adresaEmail = resultSet.getString("Email");
                if (adresaEmail == null) {
                    adresaEmail = " ";
                }

                String telefon = resultSet.getString("Telefon");
                if (telefon == null) {
                    telefon = " ";
                }

                String adresa = resultSet.getString("Adresa");
                if (adresa == null) {
                    adresa = " ";
                }

                String CNP = resultSet.getString("CNP");
                view.adminHome.setNume(nume);
                view.adminHome.setPrenume(prenume);
                view.adminHome.setAdresaEmail(adresaEmail);
                view.adminHome.setTelefon(telefon);
                view.adminHome.setAdresa(adresa);
                view.adminHome.setCNP(CNP);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void setAdminEditareProfil() {

    }

    public void setUserManagementPanel() {
        String[][] users = model.sqlQueries.getUtilizatori(model.getConnectionInstance());
        view.adminHome.tableModelUsers.setNumRows(0);
        for (int i = 0; i < users.length; i++) {
            view.adminHome.setUsersData(users[i]);
        }
    }

    public void setRoluriPanel(){
        String[] roluri = model.sqlQueries.getUsersForRole(model.getConnectionInstance());
        view.adminHome.addComboID(roluri);
        setUserManagementPanel();
    }



    //          SUPER ADMIN HOME
    //Profil

    public void setSuperAdminProfileData() {
        ResultSet resultSet = model.sqlQueries.getProfileData(model.getConnectionInstance(), model.getCNP());
        try {
            //Titled Border
            String lastName = model.sqlQueries.setTitleBorderLastName(model.getConnectionInstance(), model.getCNP());
            String firstName = model.sqlQueries.setTitleBorderFirstName(model.getConnectionInstance(), model.getCNP());
            view.superAdminHome.setTitledBorder(lastName, firstName);

            //Profile Data
            while (resultSet.next()) {
                String nume = resultSet.getString("Nume");
                String prenume = resultSet.getString("Prenume");

                String adresaEmail = resultSet.getString("Email");
                if (adresaEmail == null) {
                    adresaEmail = " ";
                }

                String telefon = resultSet.getString("Telefon");
                if (telefon == null) {
                    telefon = " ";
                }

                String adresa = resultSet.getString("Adresa");
                if (adresa == null) {
                    adresa = " ";
                }

                String CNP = resultSet.getString("CNP");
                view.superAdminHome.setNume(nume);
                view.superAdminHome.setPrenume(prenume);
                view.superAdminHome.setAdresaEmail(adresaEmail);
                view.superAdminHome.setTelefon(telefon);
                view.superAdminHome.setAdresa(adresa);
                view.superAdminHome.setCNP(CNP);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void setSuperAdminEditareProfil() {

    }

    public void setSRoluriPanel(){
        String[] roluri = model.sqlQueries.getUsersForRole(model.getConnectionInstance());
        view.superAdminHome.addComboID(roluri);
        setSPUserManagementPanel();
    }

    public void setSPUserManagementPanel() {
        String[][] users = model.sqlQueries.getUtilizatori(model.getConnectionInstance());
        view.superAdminHome.tableModelUsers.setNumRows(0);
        for (int i = 0; i < users.length; i++) {
            view.superAdminHome.setUsersData(users[i]);
        }
    }
}