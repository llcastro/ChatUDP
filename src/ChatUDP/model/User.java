
package ChatUDP.model;

public class User {

    private String userName;
    private String ip;
    private int port;

    public User(String userName, String ip, int port) {
        this.userName = userName;
        this.ip = ip;
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip.substring(1, ip.length());
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

    @Override
    public String toString() {
        return ip + "#" + port + "#" + userName;
    }
}
