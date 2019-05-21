package client.networking.waiter;

import basicClasses.Notification;

public interface WaiterClient {
  void gotNotification(Notification notification);

}
