package client.networking.waiter;

public interface WaiterClient {
  void gotNotification(String notification);

  void addClient(Object object);
}
