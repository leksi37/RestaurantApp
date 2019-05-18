//package JDBC;
//
//import basicClasses.Passwords;
//import org.junit.Before;
//import org.junit.Test;
//import org.postgresql.util.PSQLException;
//
//import static org.junit.Assert.assertTrue;
//
//public class PasswordReaderTest {
//    private PasswordReader reader = PasswordReader.getInstance();
//
//    @Before
//    public void setup()
//    {
////        reader.deleteAll();
//        Passwords p1 = new Passwords("manager", "1234");
//        Passwords p2 = new Passwords("waiter", "7844");
//        Passwords p3 = new Passwords("chef", "5434");
//        reader.add(p1);
//        reader.add(p2);
//        reader.add(p3);
//    }
//
//    @Test
//    public void testAdd()
//    {
//        reader.add(new Passwords("m", "fwfw"));
//        assertTrue(reader.getPassword("m").getPassword().equals("fwfw"));
//    }
//
//    @Test
//    public void testChange()
//    {
//        reader.changePassword(new Passwords("manager", "new password"));
//        assertTrue(reader.getPassword("manager").getPassword().equals("new password"));
//    }
//
//    @Test (expected = PSQLException.class)
//    public void testAdd2()
//    {
//        reader.add(new Passwords("manager", "lala"));
//    }
//
//    @Test
//    public void testGet()
//    {
//        System.out.println(reader.getPassword("manager").dbFormat());
//        System.out.println(reader.getPassword("chef").dbFormat());
//        System.out.println(reader.getPassword("waiter").dbFormat());
//    }
//}