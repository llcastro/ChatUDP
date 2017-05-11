/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatUDP.cliente;

import ChatUDP.model.User;
import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class SplitMessages {

    private ArrayList<User> users;
    
    public SplitMessages() {
    }

    public SplitMessages(ArrayList<User> users) {
        this.users = users;
    }
    
    // return 0 if ok, -1 if not
    // clean users array
    public int splitConnectedUsers(String message) {
        String[] parts = message.split("#");
        
        if(parts[0].equals("2")) {
            this.users.clear();
            int size = parts.length;
            
            for (int i = 1; i < size; i = i + 3) {
                this.users.add(
                        new User(parts[i+2],
                                parts[i],
                                Integer.valueOf(parts[i+1])));
            }
            return 0;
        }
        
        return -1;
    }
    
}
