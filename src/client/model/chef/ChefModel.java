package client.model.chef;

import java.beans.PropertyChangeListener;

public interface ChefModel {
    void addListeners(String name, PropertyChangeListener listener);
    void orderAdded();
}
