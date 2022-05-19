package data;

import java.io.Serializable;

public class Administrator extends User implements Serializable {
    private static final long serialVersionUID = 2983042754501098352L;

    public Administrator() {

    }

    public Administrator(String username, String password) {
        super(username, password, "administrator");
    }

    @Override
    public String toString() {
        return "Administrator " + super.toString();
    }
}
