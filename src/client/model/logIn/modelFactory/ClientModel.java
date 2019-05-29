package client.model.logIn.modelFactory;

import java.beans.PropertyChangeListener;

public interface ClientModel {
    void addClient(Object object);
    void addListeners(String name, PropertyChangeListener listener);
}