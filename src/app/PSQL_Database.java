package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PSQL_Database {

    private String url = "jdbc:postgresql://localhost:5432/cs_club_attendance";
    private String username = "postgres";
    private String password = "conniemac";

    //Checks to see if the members name is currently in the database or not
    public int numOfMatchingNames(Person user) throws SQLException {

        int matchingRecords = 0;
        ResultSet rs;
        String sql;

        try(Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement()){

            sql = String.format("SELECT COUNT(*) FROM members "
                    + "WHERE first_name LIKE '%s' AND "
                    + "last_name LIKE '%s';", user.getFirstName(), user.getLastName());
            rs = statement.executeQuery(sql);
            rs.next();

            matchingRecords = rs.getInt(1);
            connect.close();

        } catch(SQLException ex){

            ex.printStackTrace();
        }

        return matchingRecords;
    }

    public boolean addToList(Person user){

        boolean added = true;
        ResultSet rs;
        String sql;

        try(Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement()){

            sql = String.format("INSERT INTO members" +
                                "(first_name, last_name, major, date_joined, meetings_attended, email)" +
                                "VALUES('%s', '%s', '%s', current_date , 1, '%s')", user.getFirstName(), user.getLastName(),
                                user.getMajor(), user.getEmail());
            statement.executeUpdate(sql);
            connect.close();

        } catch(SQLException ex){

            ex.printStackTrace();
            added = false;
        }

        return added;
    }

    // This method is used to get the users id if their name is unique in the list
    public int getUserId(Person user){

        int id = -1;
        ResultSet rs;
        String sql;

        try(Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement()){

            sql = String.format("SELECT mem_id FROM members " +
                    "WHERE first_name LIKE '%s' " +
                    "AND last_name LIKE '%s';", user.getFirstName(), user.getLastName());
            rs = statement.executeQuery(sql);
            rs.next();

            id = rs.getInt(1);
            connect.close();

        } catch(SQLException ex){

            ex.printStackTrace();
        }

        return id;
    }

    public boolean recordAttendance(Person user){

        boolean added = true;

        String sql;

        try(Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement()){

            sql = String.format("INSERT INTO attendance_record " +
                            "(mem_id, date_attended) " +
                            "VALUES(%d, current_date);", user.getId());

            statement.executeUpdate(sql);
            connect.close();

        } catch(SQLException ex){

            ex.printStackTrace();
            added = false;
        }

        return added;
    }

    public int updateMeetingsAttended(Person user){

        int meetingsAttended = -1;
        ResultSet rs;
        String sql;

        try(Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement()){

            sql = String.format("SELECT meetings_attended FROM members " +
                    "WHERE mem_id = '%d';", user.getId());
            rs = statement.executeQuery(sql);
            rs.next();


            meetingsAttended = rs.getInt(1);

            sql = String.format("UPDATE members " +
                    "SET meetings_attended = %d + 1" +
                    "WHERE mem_id = %d;", meetingsAttended, user.getId());
            statement.executeUpdate(sql);
            connect.close();

        } catch(SQLException ex){

            ex.printStackTrace();
        }

        return meetingsAttended + 1;
    }

    // Overloaded methods to get the users id by using first, last and email. Used if
    // multiple members have the same name
    public int getUserId(Person user, String email){

        int id = -1;
        ResultSet rs;
        String sql;

        try(Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement()){

            sql = String.format("SELECT mem_id FROM members " +
                    "WHERE first_name LIKE '%s' " +
                    "AND last_name LIKE '%s'" +
                    "AND email LIKE '%s';", user.getFirstName(), user.getLastName(), email);
            rs = statement.executeQuery(sql);
            rs.next();

            id = rs.getInt(1);
            connect.close();

        } catch(SQLException ex){

            ex.printStackTrace();
        }

        return id;
    }

    public boolean checkEmail(Person user){

        boolean emailExists = true;
        ResultSet rs;
        String sql;
        int temp;

        try(Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement()){

            sql = String.format("SELECT COUNT(*) FROM members " +
                    "WHERE email LIKE '%s';", user.getEmail());
            rs = statement.executeQuery(sql);
            rs.next();

            if((temp = rs.getInt(1)) == 0){

                emailExists = false;
            }

            System.out.printf("%d", temp);

            connect.close();

        } catch(SQLException ex){

            ex.printStackTrace();
        }

        return emailExists;
    }
}