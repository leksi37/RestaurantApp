package client.networking.logIn;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LogSocketHandler implements Runnable {

    private LogInClient client;

    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public LogSocketHandler(LogInClient client, ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
        this.client = client;
        this.outToServer = outToServer;
        this.inFromServer = inFromServer;
    }

    @Override
    public void run() {

    }
}
