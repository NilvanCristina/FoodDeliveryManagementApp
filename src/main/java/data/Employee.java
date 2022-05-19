package data;

import java.io.Serializable;

public class Employee extends User implements Serializable {
    private static final long serialVersionUID = -6349010372931092617L;

    public Employee() {

    }

    public Employee(String username, String password) {
        super(username, password, "employee");
    }

    @Override
    public String toString() {
        return "Employee " + super.toString();
    }
}
