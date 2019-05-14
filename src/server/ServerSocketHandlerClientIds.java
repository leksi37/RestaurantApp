package server;

import server.networking.ServerSocketHandler;

public class ServerSocketHandlerClientIds {
    private ServerSocketHandler ssh;
    private String id;

    public ServerSocketHandlerClientIds(ServerSocketHandler ssh, String id) {
        this.ssh = ssh;
        this.id = id;
    }

    public ServerSocketHandlerClientIds get(ServerSocketHandler ssh)
    {
        return this;
    }

    public ServerSocketHandlerClientIds get(String id)
    {
        return this;
    }

    public ServerSocketHandler getSsh() {
        return ssh;
    }

    public String getId() {
        return id;
    }
}
