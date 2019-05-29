package tests;

import JDBC.PasswordReader;
import basicClasses.Passwords;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.util.PSQLException;

import static org.junit.Assert.assertTrue;

public class PasswordReaderTest {
    private PasswordReader reader = PasswordReader.getInstance();

    @Test
    public void testAdd()
    {
        reader.add(new Passwords("m", "fwfw"));
        assertTrue(reader.getPassword("m").getPassword().equals("fwfw"));
    }

    @Test
    public void testChange()
    {
        reader.changePassword(new Passwords("manager", "new password"));
        assertTrue(reader.getPassword("manager").getPassword().equals("new password"));
    }

    @Test (expected = PSQLException.class)
    public void testAdd2()
    {
        reader.add(new Passwords("manager", "lala"));
    }

    @Test
    public void testGet()
    {
        System.out.println(reader.getPassword("manager").dbFormat());
        System.out.println(reader.getPassword("chef").dbFormat());
        System.out.println(reader.getPassword("waiter").dbFormat());
    }
}