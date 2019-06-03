package JDBC;

import basicClasses.Passwords;

import java.sql.ResultSet;
import java.sql.SQLException;



//needs testing


public class PasswordReader implements PasswordsReader{
    private JDBC db;
//    private static PasswordReader instance;

    public PasswordReader() {
        db = JDBC.getInstance();
    }

//    public static PasswordReader getInstance()
//    {
//        if(instance == null)
//            instance = new PasswordReader();
//        return instance;
//    }

    public void add(Passwords p)
    {
        try {
            db.insert("passwords", p.dbFormat());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Passwords toPassword(ResultSet rs)
    {
        Passwords p = null;
        try {
            if(rs.next())
                p = new Passwords(rs.getString("name"), rs.getString("password"));
        } catch (SQLException e) {
            //just return null
        }
        return p;
    }

    public Passwords getPassword(String name)
    {
        ResultSet rs = db.get("passwords", "name = '" + name + "'");
        return toPassword(rs);
    }

    public void changePassword(Passwords password)
    {
        ResultSet rs = db.get("passwords", "name = '" + password.getName() + "'");
        Passwords p = toPassword(rs);
        db.remove("passwords", "name = '" + p.getName() + "'");
        try {
            db.insert("passwords", password.dbFormat());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void deleteAll()
//    {
//        db.removeAll("passwords");
//    }
}
