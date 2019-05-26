package client.model.chef;

import client.model.logIn.modelFactory.ClientModel;

import java.beans.PropertyChangeListener;

public interface ChefModel extends ClientModel {

    void orderAdded();
    void sendNotification(String notification);
    void passwordDisapproved();
    void passwordApproved();
    void checkLogIn(String value);
}
