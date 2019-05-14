package BasicClasses;

import java.io.Serializable;

public class SocketClientType implements Serializable {

    private SocketType type;

    public enum SocketType{
        CUSTOMER,
        CHEF,
        WAITER,
        ADMIN
    }
}
