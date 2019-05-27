package client.viewModel.waiter;

import basicClasses.Notification;
import client.model.waiter.WaiterModel;
import client.viewModel.ViewModels;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class WaiterViewModel implements ViewModels {
    private ListProperty<Notification> notifications = new ListProperty<Notification>() {
        @Override
        public ReadOnlyIntegerProperty sizeProperty() {
            return null;
        }
        @Override
        public ReadOnlyBooleanProperty emptyProperty() {
            return null;
        }
        @Override
        public void bind(ObservableValue<? extends ObservableList<Notification>> observable) {

        }
        @Override
        public void unbind() {

        }

        @Override
        public boolean isBound() {
            return false;
        }

        @Override
        public Object getBean() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public ObservableList<Notification> get() {
            return null;
        }

        @Override
        public void addListener(ChangeListener<? super ObservableList<Notification>> listener) {

        }

        @Override
        public void removeListener(ChangeListener<? super ObservableList<Notification>> listener) {

        }

        @Override
        public void set(ObservableList<Notification> value) {

        }

        @Override
        public void addListener(ListChangeListener<? super Notification> listener) {

        }

        @Override
        public void removeListener(ListChangeListener<? super Notification> listener) {

        }

        @Override
        public void addListener(InvalidationListener listener) {

        }

        @Override
        public void removeListener(InvalidationListener listener) {

        }
    };
    private WaiterModel waiterModel;
    PropertyChangeSupport support;

    public WaiterViewModel(WaiterModel waiterModel){
        this.waiterModel = waiterModel;
        support = new PropertyChangeSupport(this);
        waiterModel.addListeners("Notification received", this :: notificationReceived);
    }

    public void notificationReceived(PropertyChangeEvent event){
        notifications.add((Notification)event.getNewValue());
    }

    public ListProperty<Notification> getNotifications() {
        return notifications;
    }
}
