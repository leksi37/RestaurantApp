package basicClasses;

import java.io.Serializable;

public class Notification implements Serializable {
    private String notificationText;
    private Object obj;
    private boolean taken;

    public Notification(String notificationText, Object obj){
        this.notificationText = notificationText;
        this.obj = obj;
        taken = false;
    }

    public String getNotificationText(){
        return notificationText;
    }

    public Object getObject(){
        return obj;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
