package server;

import server.networking.ServerSocketHandler;

public class SSHIds {
    private ServerSocketHandler ssh;
    private String id;

    public SSHIds(ServerSocketHandler ssh, String id) {
        this.ssh = ssh;
        this.id = id;
    }

    public SSHIds get(ServerSocketHandler ssh)
    {
        return this;
    }

    public SSHIds get(String id)
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
