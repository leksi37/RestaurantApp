package client.networking.logIn;

import client.model.logIn.LogInModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketLogIn implements LogInClient {

    private LogInModel model;
    private Socket socket;
    private LogSocketHandler logSocketHandler;

    public SocketLogIn(LogInModel model) {
        this.model = model;
        try{
            socket=new Socket("localHost", 2910);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            outToServer.writeObject("im a login");
            logSocketHandler= new LogSocketHandler(this,
                    outToServer,
                    new ObjectInputStream(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t= new Thread(logSocketHandler);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void addClientToServer() {

    }
}
