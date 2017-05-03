/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoServerUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class EchoServer {
    
    private static final ArrayList<User> connected = new ArrayList<>();
    
    private static DatagramPacket dpacket;
    
    public static void main(String[] args) throws SocketException, IOException {
        int port = 2000;
        DatagramSocket dsocket = new DatagramSocket(port);
        byte[] byteMessage = new byte[1000];
        dpacket = new DatagramPacket(byteMessage, byteMessage.length);
        
        while(true) {
            dsocket.receive(dpacket);
            
            byte[] me = dpacket.getData();
            int packSize = dpacket.getLength();
            String s = new String(me, 0, packSize).trim();
            
            System.out.println(packSize + " " + dpacket.getAddress() + ":"
                    + dpacket.getPort() + " #" + s + "#");
            
            s = chooseAction(s);
            me = s.getBytes();
            DatagramPacket reply = new DatagramPacket(
                    me, me.length, dpacket.getAddress(), dpacket.getPort());
            dsocket.send(reply);
        }
    }
    
    private static String chooseAction(String message) {
        String[] parts = message.split("#");
        
        if(parts.length == 2 && parts[0].equals("1") && !parts[1].equals("")) {
            connected.add(new User(parts[1], dpacket.getAddress().toString(), dpacket.getPort()));
            System.out.println("Cliente " + parts[1] + " logado");
            return "1";
        } else if(parts.length == 1 && parts[0].equals("5")) {
            int i = searchUser(dpacket.getAddress().toString());
            if(i != -1) {
                System.out.println("Usuário: " + connected.get(i).getUserName() + " desconectado");
                connected.remove(i);
            }
            return "5";
        } else if(parts.length == 1 && parts[0].equals("listar")) {
            return addConnected();
        }
        
        return "protocolo desconhecido: " + message;
    }
    
    private static String addConnected() {
        String message = "";
        for(User u : connected) {
            message += u.toString() + '\n';
        }
        return message;
    }
    
    private static int searchUser(String ip) {
        for(User u : connected) {
            if(u.getIp().equals(ip)) {
                return connected.indexOf(u);
            }
        }
        return -1;
    }
    
}
