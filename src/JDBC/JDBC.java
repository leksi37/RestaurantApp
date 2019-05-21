package JDBC;

import java.sql.*;

public class JDBC {
    private Connection c;
    private Statement st;
    private static JDBC instance;

    private JDBC() {
        c = null;
        try {
            Class.forName("org.postgresql.Driver");


            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "2791");


            System.out.println("Database open ok");
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static JDBC getInstance()
    {
        if(instance == null)
            instance = new JDBC();
        return instance;
    }

    public void insert(String tableName, String values) throws SQLException
    {
        String com = "insert into \"menu\"." + tableName + " values ( " + values + ");";
        st = c.createStatement();
        st.executeUpdate(com);
        System.out.println("New order received");

    }

    public void remove(String tableName, String condition)
    {
        String com = "delete from \"menu\"." + tableName + " where " + condition + ";";
        try {
            st = c.createStatement();
            st.executeUpdate(com);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll(String tableName)
    {
        String com = "delete from \"menu\"." + tableName + ";";
        try {
            st = c.createStatement();
            st.executeUpdate(com);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet get(String tableName, String condition)
    {
        ResultSet rs = null;
        String com;
        if(condition != null)
            com = "select * from \"menu\"." + tableName + " where " + condition + ";";
        else
            com = "select * from \"menu\"." + tableName + ";";
        try {
            st = c.createStatement();
            rs = st.executeQuery(com);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet get(String tableName)
    {
        return get(tableName, null);
    }
}
