package JDBC;

import BasicClasses.Passwords;

import java.sql.ResultSet;
import java.sql.SQLException;



//needs testing


public class PasswordReader {
    private JDBC db;
    private static PasswordReader instance;

    private PasswordReader() {
        db = JDBC.getInstance();
    }

    public static PasswordReader getInstance()
    {
        if(instance == null)
            instance = new PasswordReader();
        return instance;
    }

    public void add(Passwords p)
    {
        db.insert("passwords", p.dbFormat());
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
        db.insert("passwords", password.dbFormat());
    }

//    public void deleteAll()
//    {
//        db.removeAll("passwords");
//    }
}
