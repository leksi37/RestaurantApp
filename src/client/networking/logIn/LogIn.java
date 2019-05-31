package client.networking.logIn;

import basicClasses.ClientType;
import basicClasses.RequestType;

public interface LogIn {

    void setClientType(ClientType type);

    ClientType getClientType();

    void connectClient();

    void checkPassword(String value);

    void passwordCheckResult(RequestType type);
}
