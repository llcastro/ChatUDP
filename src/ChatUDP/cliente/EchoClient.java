
package ChatUDP.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author luis
 */
public class EchoClient {

    public static void main(String[] args) throws Exception {
        //InetAddress address = InetAddress.getLocalHost();
        InetAddress address = InetAddress.getLocalHost();
        int port = 22000;
        DatagramSocket dsocket = new DatagramSocket();
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        
        String message;

        while (true) {
            System.out.print("> ");
            message = teclado.readLine();
            byte[] byteMessage = message.getBytes();
            DatagramPacket dpacket = new DatagramPacket(
                    byteMessage, byteMessage.length, address, port);
            dsocket.send(dpacket);
            
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            dsocket.receive(reply);
            
            message = new String(reply.getData());
            
            if(message.toLowerCase().trim().equals("5")) {
                System.out.println("cliente fechado!:" + message);
                break;
            }

            System.out.println("packet: " + message);
        }
    }
}
