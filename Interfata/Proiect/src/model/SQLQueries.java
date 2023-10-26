package model;

import javax.swing.*;
import java.sql.*;

public class SQLQueries {
    public boolean loginQuery(Connection connection, String CNP, String password) {
        try {
            PreparedStatement st = connection.prepareStatement("Select CNP, password from utilizatori where CNP=? and password=?");
            st.setString(1, CNP);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Logarea a avut succes!");
                return true;
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "CNP/Parola gresite!");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public boolean registerQuery(Connection connection, String nume, String prenume, String email, String CNP, String telefon, String password) {
        try {
            String query = "insert into utilizatori (CNP, nume, prenume, telefon, email, password) values('" + CNP + "','" + nume + "','"
                    + prenume + "','" + telefon + "','" + email + "','" + password + "')";
            Statement sta = connection.createStatement();
            sta.executeUpdate(query);
            JOptionPane.showMessageDialog(new JButton("OK!"), "Bine ai venit, " + nume + " " + prenume + "! \nContul tau a fost creat cu succes.");
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(new JButton("OK!"), "Exista deja un utilizator cu acest CNP: " + CNP + ".");
        }
        return false;
    }

    public String setTitleBorderLastName(Connection connection, String CNP) {
        String lastName = " ";
        try {
            String query = "SELECT Nume from utilizatori where CNP='" + CNP + "'";
            Statement sta = connection.createStatement();
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                lastName = rez.getString("Nume");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return lastName;
    }

    public String setTitleBorderFirstName(Connection connection, String CNP) {
        String firstName = " ";
        try {
            String query = "SELECT Prenume from utilizatori where CNP='" + CNP + "'";
            Statement sta = connection.createStatement();
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                firstName = rez.getString("Prenume");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return firstName;
    }

    public ResultSet getProfileData(Connection connection, String CNP) {
        ResultSet rez = null;
        try {
            String query = "SELECT * from utilizatori where CNP='" + CNP + "'";

            Statement sta = connection.createStatement();
            rez = sta.executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rez;
    }

    public String[] getGroupsID(Connection connection, String CNP) {
        String[] returnString = {"-"};
        try {
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select distinct(ID_Grup) from grupuri where CNP_Admin_Grup = '" + CNP + "'";

            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnString = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnString[i] = rez.getString("ID_Grup");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnString;
    }

    public String[] getGroupsMemberID(Connection connection, String CNP) {
        String[] returnString = {"-"};
        try {
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select distinct(ID_Grup) from grupuri where CNP_Student = '" + CNP + "'";

            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnString = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnString[i] = rez.getString("ID_Grup");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnString;
    }

    public String[] getUsersID(Connection connection, String CNP, String selectedItem) {
        String[] returnValue = {"-"};
        try {
            String query = "select * from  studenti where CNP_Student NOT IN (select CNP_Student from grupuri where CNP_Admin_Grup = '" + CNP + "' and ID_Grup = '" + Integer.parseInt(selectedItem) + "') ";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("CNP_Student");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getCoursesID(Connection connection, String CNP) {
        String[] returnValue = {"-"};
        try {
            String query = "select distinct(ID_Curs) as ID_Curs from cursuri where CNP_Profesor = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("ID_Curs");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getLaboratoriesID(Connection connection, String CNP) {
        String[] returnValue = {"-"};
        try {
            String query = "select distinct(ID_Curs) as ID_Curs from laboratoare where CNP_Profesor_Laborator = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("ID_Curs");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getSeminarsID(Connection connection, String CNP) {
        String[] returnValue = {"-"};
        try {
            String query = "select distinct(ID_Curs) as ID_Curs from seminarii where CNP_Profesor_Seminar = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("ID_Curs");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String programeazaCurs(Connection connection, int ID_Curs, int ziua, String oraStart, String oraFinal) {
        String error = null;
        try {
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "call programare_curs(" + ID_Curs + ", " + ziua + ", '" + oraStart + "', '" + oraFinal + "')";
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                error = rez.getString("error");
            }
        } catch (SQLException ex) {
        }
        return error;
    }

    public String programeazaLaborator(Connection connection, int ID_Curs, int ziua, String oraStart, String oraFinal) {
        String error = null;
        try {
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "call programare_laborator(" + ID_Curs + ", " + ziua + ", '" + oraStart + "', '" + oraFinal + "')";
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                error = rez.getString("error");
            }
        } catch (SQLException ex) {
        }
        return error;
    }

    public String programeazaSeminar(Connection connection, int ID_Curs, int ziua, String oraStart, String oraFinal) {
        String error = null;
        try {
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "call programare_seminar(" + ID_Curs + ", " + ziua + ", '" + oraStart + "', '" + oraFinal + "')";
            sta.executeQuery(query);
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                error = rez.getString("error");
            }
        } catch (SQLException ex) {
        }
        return error;
    }

    public String creareGrup(Connection connection, String CNP, String descriere, String ID) {
        String error = null;
        try {
            int id = Integer.parseInt(ID.trim());
            String desc = descriere.trim();
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "call creeaza_grup('" + CNP + "', '" + desc + "', " + id + ")";
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                error = rez.getString("error");
            }
        } catch (SQLException ex) {
        }
        return error;
    }

    public String getUserRole(Connection connection, String CNP) {
        String returnValue = "1";
        try {
            String query = "SELECT ID_Rol FROM Roluri WHERE CNP_User ='" + CNP + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                returnValue = resultSet.getString("ID_Rol");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getGrupurileMele(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT * from GRUPURI where CNP_Admin_Grup = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][6];
            rez.beforeFirst();
            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Grup");
                returnValue[i][1] = rez.getString("CNP_Admin_Grup");
                returnValue[i][2] = rez.getString("CNP_Profesor_Grup");
                returnValue[i][3] = rez.getString("CNP_Student");
                returnValue[i][4] = rez.getString("Invitatie_Student");
                returnValue[i][5] = rez.getString("Descriere_Grup");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getCursurileMele(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT * from cursuri where CNP_Student = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][6];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Curs");
                returnValue[i][1] = rez.getString("CNP_Profesor");
                returnValue[i][2] = rez.getString("Descriere_Curs");
                returnValue[i][3] = rez.getString("Nr_Studenti");
                returnValue[i][4] = rez.getString("Nr_Studenti_Max");
                returnValue[i][5] = rez.getString("Procent_Nota");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getCourses(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String queryC = "SELECT * from cursuri where CNP_Profesor = '" + CNP + "' order by ID_Curs asc";
            String queryS = "SELECT * from seminarii where CNP_Profesor_Seminar = '" + CNP + "' order by ID_Curs asc";
            String queryL = "SELECT * from laboratoare where CNP_Profesor_Laborator = '" + CNP + "' order by ID_Curs asc";
            Statement staC = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement staS = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement staL = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rezC = staC.executeQuery(queryC);
            ResultSet rezS = staS.executeQuery(queryS);
            ResultSet rezL = staL.executeQuery(queryL);
            rezC.last();
            rezS.last();
            rezL.last();
            int stringSize = rezC.getRow() + rezS.getRow() + rezL.getRow();
            returnValue = new String[stringSize][9];
            rezC.beforeFirst();
            rezS.beforeFirst();
            rezL.beforeFirst();

            int i = 0;
            while (rezC.next()) {
                returnValue[i][0] = rezC.getString("ID_Curs");
                returnValue[i][1] = rezC.getString("CNP_Profesor");
                returnValue[i][2] = rezC.getString("CNP_Student");
                returnValue[i][3] = rezC.getString("Descriere_Curs");
                returnValue[i][4] = rezC.getString("Nr_Studenti");
                returnValue[i][5] = rezC.getString("Nr_Studenti_Max");
                returnValue[i][6] = rezC.getString("Procent_Nota");
                returnValue[i][7] = rezC.getString("Nota_Finala");
                returnValue[i][8] = "Curs";
                i++;
            }
            while (rezS.next()) {
                returnValue[i][0] = rezS.getString("ID_Curs");
                returnValue[i][1] = rezS.getString("CNP_Profesor_Seminar");
                returnValue[i][2] = rezS.getString("CNP_Student");

                String queryDescriereCursSeminar = "SELECT distinct(Descriere_Curs) from cursuri where ID_Curs = '" + returnValue[i][0] + "'";
                String queryNrStudentiSeminar  = "SELECT distinct(Nr_Studenti) from cursuri where ID_Curs = '" + returnValue[i][0] + "'";
                String queryNrStudentiMaxSeminar  = "SELECT distinct(Nr_Studenti_Max) from cursuri where ID_Curs = '" + returnValue[i][0] + "'";
                Statement staDescriereCursSeminar  = connection.createStatement();
                Statement staNrStudentiSeminar  = connection.createStatement();
                Statement staNrStudentiMaxSeminar  = connection.createStatement();

                ResultSet rezDescriereCursSeminar  = staDescriereCursSeminar.executeQuery(queryDescriereCursSeminar);
                ResultSet rezNrStudentiSeminar  = staNrStudentiSeminar.executeQuery(queryNrStudentiSeminar);
                ResultSet rezNrStudentiMaxSeminar  = staNrStudentiMaxSeminar.executeQuery(queryNrStudentiMaxSeminar);

                if(rezDescriereCursSeminar.next()) {
                    returnValue[i][3] = rezDescriereCursSeminar.getString("Descriere_Curs");
                }
                if(rezNrStudentiSeminar.next()) {
                    returnValue[i][4] = rezNrStudentiSeminar.getString("Nr_Studenti");
                }
                if(rezNrStudentiMaxSeminar.next()) {
                    returnValue[i][5] = rezNrStudentiMaxSeminar.getString("Nr_Studenti_Max");
                }

                returnValue[i][6] = rezS.getString("Procent_Nota");
                returnValue[i][7] = rezS.getString("Nota_Seminar");
                returnValue[i][8] = "Seminar";
                i++;
            }
            while (rezL.next()) {
                returnValue[i][0] = rezL.getString("ID_Curs");
                returnValue[i][1] = rezL.getString("CNP_Profesor_Laborator");
                returnValue[i][2] = rezL.getString("CNP_Student");

                String queryDescriereCursLaborator = "SELECT distinct(Descriere_Curs) from cursuri where ID_Curs = '" + returnValue[i][0] + "'";
                String queryNrStudentiLaborator  = "SELECT distinct(Nr_Studenti) from cursuri where ID_Curs = '" + returnValue[i][0] + "'";
                String queryNrStudentiMaxLaborator  = "SELECT distinct(Nr_Studenti_Max) from cursuri where ID_Curs = '" + returnValue[i][0] + "'";

                Statement staDescriereCursLaborator  = connection.createStatement();
                Statement staNrStudentiLaborator  = connection.createStatement();
                Statement staNrStudentiMaxLaborator  = connection.createStatement();

                ResultSet rezDescriereCursLaborator  = staDescriereCursLaborator.executeQuery(queryDescriereCursLaborator);
                ResultSet rezNrStudentiLaborator  = staNrStudentiLaborator.executeQuery(queryNrStudentiLaborator);
                ResultSet rezNrStudentiMaxLaborator  = staNrStudentiMaxLaborator.executeQuery(queryNrStudentiMaxLaborator);
                if(rezDescriereCursLaborator.next()) {
                    returnValue[i][3] = rezDescriereCursLaborator.getString("Descriere_Curs");
                }
                if(rezNrStudentiLaborator.next()) {
                    returnValue[i][4] = rezNrStudentiLaborator.getString("Nr_Studenti");
                }
                if(rezNrStudentiMaxLaborator.next()) {
                    returnValue[i][5] = rezNrStudentiMaxLaborator.getString("Nr_Studenti_Max");
                }


                returnValue[i][6] = rezL.getString("Procent_Nota");
                returnValue[i][7] = rezL.getString("Nota_Laborator");
                returnValue[i][8] = "Laborator";
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String invitaInGrup(Connection connection, String CNP, String CNPinvitat, String idGrup) {
        String error = null;
        try {
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "call invita_grup('" + CNP + "', '" + CNPinvitat + "', '" + idGrup + "')";
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                error = rez.getString("error");
            }

        } catch (SQLException sqlException) {
        }
        return error;
    }

    public String[][] getOrar(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT * from orar WHERE orar.ID_Curs = (SELECT ID_Curs FROM cursuri WHERE CNP_Student ='" + CNP + "')";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][5];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Curs");
                if (rez.getString("Tip_Activitate").equals("0")) {
                    returnValue[i][1] = "Curs";
                } else if (rez.getString("Tip_Activitate").equals("1")) {
                    returnValue[i][1] = "Seminar";
                } else if (rez.getString("Tip_Activitate").equals("2")) {
                    returnValue[i][1] = "Laborator";
                }
                returnValue[i][2] = rez.getString("Ora_Start");
                returnValue[i][3] = rez.getString("Ora_Final");

                if (rez.getString("Ziua").equals("1")) {
                    returnValue[i][4] = "Luni";
                } else if (rez.getString("Ziua").equals("2")) {
                    returnValue[i][4] = "Marti";
                } else if (rez.getString("Ziua").equals("3")) {
                    returnValue[i][4] = "Miercuri";
                } else if (rez.getString("Ziua").equals("4")) {
                    returnValue[i][4] = "Joi";
                } else if (rez.getString("Ziua").equals("5")) {
                    returnValue[i][4] = "Vineri";
                }
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getNoteCurs(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT * from cursuri where CNP_Student = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][3];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Curs");
                returnValue[i][1] = rez.getString("Nota_Curs");
                returnValue[i][2] = "Curs";
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getNoteSeminarii(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT * from seminarii where CNP_Student = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][3];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Curs");
                returnValue[i][1] = rez.getString("Nota_Seminar");
                returnValue[i][2] = "Seminar";
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getNoteLaboratoare(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT * from laboratoare where CNP_Student = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][3];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Curs");
                returnValue[i][1] = rez.getString("Nota_Laborator");
                returnValue[i][2] = "Laborator";
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getUtilizatori(Connection connection) {
        String[][] returnValue = null;
        try {
            String query = "SELECT CNP, Nume, Prenume, Adresa, Telefon, Email, IBAN, Contract, Descriere_Rol from utilizatori, roluri where utilizatori.CNP = roluri.CNP_User order by Descriere_Rol ASC";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][10];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("CNP");
                returnValue[i][1] = rez.getString("Nume");
                returnValue[i][2] = rez.getString("Prenume");
                returnValue[i][3] = rez.getString("Adresa");
                returnValue[i][4] = rez.getString("Telefon");
                returnValue[i][5] = rez.getString("Email");
                returnValue[i][6] = rez.getString("IBAN");
                returnValue[i][7] = rez.getString("Contract");
                returnValue[i][8] = rez.getString("Descriere_Rol");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getCatalogue(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT Nume, Prenume, ID_Curs, Nota_Curs, Nota_Finala from utilizatori, cursuri where cursuri.CNP_Student = utilizatori.CNP and cursuri.CNP_Profesor = '" + CNP + "' order by ID_Curs asc";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][5];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Curs");
                returnValue[i][1] = rez.getString("Nume");
                returnValue[i][2] = rez.getString("Prenume");
                returnValue[i][3] = rez.getString("Nota_Curs");
                returnValue[i][4] = rez.getString("Nota_Finala");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public void deleteUserAccount(Connection connection, String CNP) {
        try {
            String query = "delete from utilizatori where CNP='" + CNP + "'";
            Statement sta = connection.createStatement();
            sta.executeUpdate(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteStudentAccount(Connection connection, String CNP) {
        try {
            String query = "call sterge_student('" + CNP + "')";
            Statement sta = connection.createStatement();
            sta.executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteTeacherAccount(Connection connection, String CNP) {
        try {
            String query = "call sterge_profesor('" + CNP + "')";
            Statement sta = connection.createStatement();
            sta.executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteAdminAccount(Connection connection, String CNP) {
        try {
            String query = "call sterge_admin('" + CNP + "')";
            Statement sta = connection.createStatement();
            sta.executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void editeazaCont(Connection connection, String CNP, String Adresa, String Telefon, String Email, String IBAN, String Contract) {
        try {
            if (Adresa == null || Telefon == null || Email == null || IBAN == null || Contract == null) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Toate campurile trebuie completate!");
                return;
            }
            String query = "update utilizatori set adresa = '" + Adresa + "', telefon = '" + Telefon + "', email = '" + Email + "', IBAN = '" + IBAN + "', contract = '" + Contract + "' where CNP = '" + CNP + "'";
            PreparedStatement sta = connection.prepareStatement(query);
            sta.executeUpdate();
            JOptionPane.showMessageDialog(new JButton("OK!"), "Profil editat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void editeazaContStudent(Connection connection, String CNP, String Adresa, String Telefon, String Email, String IBAN, String Contract, String nrOre) {
        try {
            if (Adresa == null || Telefon == null || Email == null || IBAN == null || Contract == null || nrOre == null) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Toate campurile trebuie completate!");
                return;
            }
            String query = "update utilizatori set adresa = '" + Adresa + "', telefon = '" + Telefon + "', email = '" + Email + "', IBAN = '" + IBAN + "', contract = '" + Contract + "' where CNP = '" + CNP + "'";
            PreparedStatement sta = connection.prepareStatement(query);
            sta.executeUpdate();

            String query1 = "UPDATE studenti set Nr_Ore = '" + nrOre + "' where CNP_Student = '" + CNP + "'";
            PreparedStatement sta1 = connection.prepareStatement(query1);
            sta1.executeUpdate();
            JOptionPane.showMessageDialog(new JButton("OK!"), "Profil editat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void editeazaContProfesor(Connection connection, String CNP, String Adresa, String Telefon, String Email, String IBAN, String Contract, String oreMin, String oreMax, String departament) {
        try {
            if (Adresa == null || Telefon == null || Email == null || IBAN == null || Contract == null) {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Toate campurile trebuie completate!");
                return;
            }
            String query = "update utilizatori set adresa = '" + Adresa + "', telefon = '" + Telefon + "', email = '" + Email + "', IBAN = '" + IBAN + "', contract = '" + Contract + "' where CNP = '" + CNP + "'";
            PreparedStatement sta = connection.prepareStatement(query);
            sta.executeUpdate();

            String query1 = "UPDATE profesori SET Ore_Min = '" + oreMin + "', Ore_Max = '" + oreMax + "', Departament = '" + departament + "' where CNP_Profesor = '" + CNP + "'";
            PreparedStatement sta1 = connection.prepareStatement(query1);
            sta1.executeUpdate();
            JOptionPane.showMessageDialog(new JButton("OK!"), "Profil editat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public String adaugaProfesorGrup(Connection connection, String CNP, int ID_Grup) {
        String error = null;
        try {
            String query = "call adauga_profesor_grup('" + CNP + "', " + ID_Grup + ")";
            Statement sta = connection.createStatement();
            ResultSet rez = sta.executeQuery(query);
            if(rez.next()){
                error = rez.getString("error");
            }
        } catch (SQLException sqlException) {

        }
        return error;
    }

    public String trimiteMesajGrup(Connection connection, String CNP, int ID_Grup, String mesaj) {
        String error = null;
        try {
            String query = "call trimite_mesaj('" + CNP + "', " + ID_Grup + ", '" + mesaj + "')";
            Statement sta = connection.createStatement();
            ResultSet rez = sta.executeQuery(query);
            error = rez.getString("error");
        } catch (SQLException sqlException) {
        }
        return error;
    }

    public String[][] getMesajeGrup(Connection connection, int ID_Grup) {
        String[][] returnValue = null;
        try {
            String query = "select * from mesaje where ID_Grup = '" + ID_Grup + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][4];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Grup");
                returnValue[i][1] = rez.getString("CNP");
                returnValue[i][2] = rez.getString("Mesaj");
                returnValue[i][3] = rez.getString("Data");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public void setPonderi(Connection connection, String CNP, String ID_Curs, String pondereCurs, String pondereSeminar, String pondereLaborator) {
        try {
            Statement sta = connection.createStatement();
            sta.executeUpdate("update cursuri set Procent_Nota = '" + pondereCurs + "' where CNP_Profesor = '" + CNP + "' and ID_Curs = '" + ID_Curs + "'");
            sta.executeUpdate("update seminarii set Procent_Nota = '" + pondereSeminar + "' where CNP_Profesor_Curs = '" + CNP + "' and ID_Curs = '" + ID_Curs + "'");
            sta.executeUpdate("update laboratoare set Procent_Nota = '" + pondereLaborator + "' where CNP_Profesor_Curs = '" + CNP + "' and ID_Curs = '" + ID_Curs + "'");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void notareStudent(Connection connection, String CNP_Profesor, String CNP_Student, String ID_Curs, String tipActivitate, String nota) {
        try {
            Statement statement = connection.createStatement();
            if (tipActivitate.equals("Curs")) {
                statement.executeUpdate("update cursuri set Nota_Curs = '" + nota + "' where CNP_Profesor = '" + CNP_Profesor + "' and ID_Curs = '" + ID_Curs + "' and CNP_Student = '" + CNP_Student + "'");
            } else if (tipActivitate.equals("Seminar")) {
                statement.executeUpdate("update seminarii set Nota_Seminar = '" + nota + "' where CNP_Profesor_Curs = '" + CNP_Profesor + "' and ID_Curs = '" + ID_Curs + "' and CNP_Student = '" + CNP_Student + "'");
            } else {
                statement.executeUpdate("update laboratoare set Nota_Laborator = '" + nota + "' where CNP_Profesor_Curs = '" + CNP_Profesor + "' and ID_Curs = '" + ID_Curs + "' and CNP_Student = '" + CNP_Student + "'");
            }
            JOptionPane.showMessageDialog(new JButton("OK!"), "Student notat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void seteazaNotaFinala(Connection connection, String CNP, int ID_Curs) {
        try {
            String query = "call seteaza_nota_finala('" + CNP + "', " + ID_Curs + ")";
            Statement sta = connection.createStatement();
            sta.executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    public String[] getStudentiNotare(Connection connection, String CNP, String ID_Curss) {
        String[] returnValue = {""};
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT CNP_Student FROM cursuri where CNP_Profesor = '" + CNP + "' and ID_Curs = '" + ID_Curss + "'");
            resultSet.last();
            returnValue = new String[resultSet.getRow()];
            resultSet.beforeFirst();

            int i = 0;
            while (resultSet.next()) {
                returnValue[i] = resultSet.getString("CNP_Student");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getCoursesNotare(Connection connection, String CNP) {
        String[] returnValue = {""};
        try {
            String query = "SELECT distinct(ID_Curs) from cursuri where CNP_Profesor = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("ID_Curs");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getUsers(Connection connection, String CNP, int idRol) {
        String[] returnValue = null;
        try {
            String query = "select CNP_User from roluri where ID_Rol <= " + idRol + " and CNP_User != '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("CNP_User");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public void creareCurs(Connection connection, String CNP, String ID_Curs, String Descriere) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("call creeaza_curs('" + ID_Curs + "', '" + CNP + "', '" + Descriere + "' , 2, 0, 30, 0);");
            JOptionPane.showMessageDialog(new JButton("OK!"), "Curs creat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void creareSeminar(Connection connection, String CNP, String ID_Curs) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("call creeaza_seminar('" + CNP + "', '" + CNP + "', " + ID_Curs + ", 0)");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void creareLaborator(Connection connection, String CNP, String ID_Curs) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("call creeaza_laborator('" + CNP + "', '" + CNP + "', " + ID_Curs + ", 0)");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public String[] getCursuri(Connection connection, String CNP) {
        String[] returnValue = null;
        try {
            String query = "SELECT distinct(ID_Curs) from cursuri where CNP_Student != '" + CNP + "' or CNP_Student is null";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("ID_Curs");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String adaugaStudentCurs(Connection connection, String CNP, String ID_Curs) {
        String error = null;
        try {
            String query = "call adauga_student_curs('" + CNP + "', '" + ID_Curs + "')";
            Statement sta = connection.createStatement();
            ResultSet rez = sta.executeQuery(query);
            if (rez.next()) {
                error = rez.getString("error");
            }
        } catch (SQLException sqlException) {
        }
        return error;
    }

    public String[] getCursuriProfesor(Connection connection, String CNP) {
        String[] returnValue = null;
        try {
            String query = "SELECT distinct(ID_Curs) from cursuri where CNP_Profesor = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("ID_Curs");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getNoUseriCursuri(Connection connection, String CNP, String Curs) {
        String[] returnValue = null;
        try {
            String query = "SELECT CNP_Student from studenti where CNP_Student not in(SELECT CNP_Student from cursuri where CNP_Profesor = '" + CNP + "' and ID_Curs = '" + Curs + "' and CNP_Student is not null)";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("CNP_Student");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getTeacherGroups(Connection connection, String CNP) {
        String[] returnValue = null;
        try {
            String query = "SELECT distinct(ID_Grup) from grupuri where CNP_Profesor_Grup = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("ID_Grup");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }
    public String[] getTeacherCNP(Connection connection) {
        String[] returnValue = null;
        try {
            String query = "SELECT CNP_Profesor from profesori";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()];
            rez.beforeFirst();

            int i = 0;
            while (rez.next()) {
                returnValue[i] = rez.getString("CNP_Profesor");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[][] getTeacherInGroups(Connection connection, String CNP) {
        String[][] returnValue = null;
        try {
            String query = "SELECT * from GRUPURI where CNP_Profesor_Grup = '" + CNP + "'";
            Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rez = sta.executeQuery(query);
            rez.last();
            returnValue = new String[rez.getRow()][6];
            rez.beforeFirst();
            int i = 0;
            while (rez.next()) {
                returnValue[i][0] = rez.getString("ID_Grup");
                returnValue[i][1] = rez.getString("CNP_Admin_Grup");
                returnValue[i][2] = rez.getString("CNP_Profesor_Grup");
                returnValue[i][3] = rez.getString("CNP_Student");
                returnValue[i][4] = rez.getString("Invitatie_Student");
                returnValue[i][5] = rez.getString("Descriere_Grup");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public String[] getUsersForRole(Connection connection) {
        String[] returnValue = null;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT CNP_User FROM roluri WHERE ID_Rol = 1");
            int i = 0;
            resultSet.last();
            returnValue = new String[resultSet.getRow()];
            resultSet.beforeFirst();

            while (resultSet.next()) {
                returnValue[i] = resultSet.getString("CNP_User");
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return returnValue;
    }

    public void insertStudent(Connection connection, String CNP) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate("INSERT INTO studenti VALUES('" + CNP + "', 30, 2)");
            JOptionPane.showMessageDialog(new JButton("OK!"), "Rol modificat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void insertProfesor(Connection connection, String CNP) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate("INSERT INTO profesori VALUES('" + CNP + "', 20, 30, 'Calculatoare')");
            JOptionPane.showMessageDialog(new JButton("OK!"), "Rol modificat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void insertAdmin(Connection connection, String CNP) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE utilizatori SET AdminAcces = 1 WHERE CNP = '" + CNP + "'");
            JOptionPane.showMessageDialog(new JButton("OK!"), "Rol modificat!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void stergeGrup(Connection connection, String ID){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM grupuri WHERE ID_Grup = '" + ID + "'");
            JOptionPane.showMessageDialog(new JButton("OK!"), "Grup sters!");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void stergeCurs(Connection connection, String ID){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM cursuri WHERE ID_Curs = '" + ID + "'");
            JOptionPane.showMessageDialog(new JButton("OK!"), "Grup sters!");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

}