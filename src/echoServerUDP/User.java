/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoServerUDP;

/**
 *
 * @author luis
 */
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
    
    @Override
    public String toString() {
        return userName + ":" + ip + ":" + port;
    }
    
}
