package client.model.logIn.modelFactory;

import java.beans.PropertyChangeListener;

public interface ClientModel {
    //giving Object as an argument because each model has a
    //client with specific methods and different goals
    void addClient(Object object);
    void addListeners(String name, PropertyChangeListener listener);
}
