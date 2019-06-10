package basicClasses;

import java.io.Serializable;

public class Request implements Serializable {
    private RequestType type;
    private Object obj;

    public Request(RequestType type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public RequestType getType() {
        return type;
    }

    public Object getObj() {
        return obj;
    }
}
