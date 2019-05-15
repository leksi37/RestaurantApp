package server;

import server.networking.ServerSocketHandlers.CustomerServerSocketHandler;

public class ServerSocketHandlerClientIds {
    private CustomerServerSocketHandler ssh;
    private String id;

    public ServerSocketHandlerClientIds(CustomerServerSocketHandler ssh, String id) {
        this.ssh = ssh;
        this.id = id;
    }

    public ServerSocketHandlerClientIds get(CustomerServerSocketHandler ssh)
    {
        return this;
    }

    public ServerSocketHandlerClientIds get(String id)
    {
        return this;
    }

    public CustomerServerSocketHandler getSsh() {
        return ssh;
    }

    public String getId() {
        return id;
    }
}
