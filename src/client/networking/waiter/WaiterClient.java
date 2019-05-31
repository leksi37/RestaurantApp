package client.networking.waiter;

import basicClasses.Notification;

public interface WaiterClient {

  void gotNotification(Notification notification);
  void gotPresenceRequest(Notification notification);
  void checkPassword(String value);
  void passwordApproved();
  void passwordDisapproved();

    void partialToDeliver(Notification obj);

  void chefRequest(Notification obj);

    void gotReceiptRequest(Notification obj);
}
