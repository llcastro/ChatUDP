
package ChatUDP.model;

import java.time.LocalDateTime;

public class User {

    private String userName;
    private String passwd;
    private String ip;
    private int port;
    private LocalDateTime login;
    private LocalDateTime logout;
    private int answeredEmails;
    private char logged;

    public User(String userName, String passwd, String ip, int port, LocalDateTime login, LocalDateTime logout) {
        this.userName = userName;
        this.passwd = passwd;
        this.ip = ip;
        this.port = port;
        this.login = login;
        this.logout = logout;
        this.answeredEmails = 0;
    }
    
    public User(String userName, String passwd, char logged) {
        this.userName = userName;
        this.passwd = passwd;
        this.logged = logged;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public LocalDateTime getLogin() {
        return login;
    }

    public void setLogin(LocalDateTime login) {
        this.login = login;
    }

    public LocalDateTime getLogout() {
        return logout;
    }

    public void setLogout(LocalDateTime logout) {
        this.logout = logout;
    }

    public int getAnsweredEmails() {
        return answeredEmails;
    }

    public void setAnsweredEmails(int answeredEmails) {
        this.answeredEmails = answeredEmails;
    }
    
    public void sumAnsweredEmails() {
        this.answeredEmails += 1;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public char getLogged() {
        return logged;
    }

    public void setLogged(char logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return ip + "#" + port + "#" + userName;
    }
}
