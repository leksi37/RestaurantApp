package tests;

import JDBC.PasswordReader;
import JDBC.PasswordsReader;
import basicClasses.Passwords;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.util.PSQLException;

import static org.junit.Assert.assertTrue;

public class PasswordReaderTest {
    private PasswordsReader reader = new PasswordReader();

    @Test
    public void testChange()
    {
        reader.changePassword(new Passwords("manager", "new password"));
        assertTrue(reader.getPassword("manager").getPassword().equals("new password"));
    }

    @Test
    public void testGet()
    {
        assertTrue(reader.getPassword("manager").getPassword().equals("new password"));
        assertTrue(reader.getPassword("chef").getPassword().equals("147258"));
        assertTrue(reader.getPassword("waiter").getPassword().equals("963852"));
    }
}