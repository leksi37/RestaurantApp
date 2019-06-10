package basicClasses;

import java.io.Serializable;

public class Passwords implements Serializable {
    private String name;
    private String password;

    public Passwords(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String dbFormat()
    {
        return "'" + name + "', '" + password + "'";
    }

    public boolean equals(Object obj)
    {
        if(obj == null || !(obj instanceof Passwords))
            return false;
        Passwords p = (Passwords) obj;
        return p.name.equals(name) && p.password.equals(password);

    }

    public String getPassword() {
        return password;
    }
}
