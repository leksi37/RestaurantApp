package client.model.chef;

import client.model.modelFactory.ClientModel;
import client.networking.chef.ChefClient;

import java.beans.PropertyChangeListener;

public interface ChefModel extends ClientModel {
    void addListeners(String name, PropertyChangeListener listener);
    void orderAdded();
    void sendNotification(String notification);
    void passwordDisapproved();
    void passwordApproved();
    void checkLogIn(String value);
}
