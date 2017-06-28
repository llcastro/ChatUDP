package ChatUDP.model;

import java.util.ArrayList;

public class Util {

    private ArrayList<User> credentials;

    public Util(ArrayList<User> credentials) {
        this.credentials = credentials;
    }

    public ArrayList setCredentials() {
        String name = "aluno";
        String passwd = "senha";
        this.credentials = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            this.credentials.add(new User(name + i, passwd + i, 'n'));
        }

        this.credentials.add(new User("didi", "123", 'n'));
        return this.credentials;
    }

    public int validateCredential(String userName, String passwd) {
        for (User u : this.credentials) {
            if (u.getUserName().equals(userName) && u.getPasswd().equals(passwd)) {
                if (u.getLogged() == 'n') {
                    return this.credentials.indexOf(u); // ok
                } else {
                    return -2; // user already connected
                }
            }
        }
        return -1; // invalid user
    }

    public int searchCredential(String userName, String passwd) {
        for (User u : this.credentials) {
            if (u.getUserName().equals(userName) && u.getPasswd().equals(passwd)) {
                return this.credentials.indexOf(u);
            }
        }
        return -1;
    }
}
