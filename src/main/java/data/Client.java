package data;

import java.io.Serializable;

public class Client extends User implements Serializable {
    private static final long serialVersionUID = 7646053847364977843L;

    public Client() {

    }

    public Client(String username, String password) {
        super(username, password, "client");
    }

    @Override
    public String toString() {
        return "Client " + super.toString();
    }
}
