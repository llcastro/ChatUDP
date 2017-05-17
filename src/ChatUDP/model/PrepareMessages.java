
package ChatUDP.model;

import java.util.ArrayList;

public class PrepareMessages {

    private ArrayList<User> users;
    
    public PrepareMessages() {
    }

    public PrepareMessages(ArrayList<User> users) {
        this.users = users;
    }
    
    // return 0 if ok, -1 if not
    // clean users array
    public int splitConnectedUsers(String message) {
        String[] parts = message.split("#");
        
        if(parts[0].trim().equals("2")) {
            this.users.clear();
            int size = parts.length;
            
            for (int i = 1; i < size; i = i + 3) {
                this.users.add(
                        new User(parts[i+2],
                                parts[i],
                                Integer.valueOf(parts[i+1])));
            }
            return 0;
        } else if(parts[0].trim().equals("5")) {
            // user disconnect
            return 5;
        } else if(parts[0].trim().equals("4")) {
            // receive message
            return 4;
        }
        
        return -1;
    }
    
    // used by server
    public String prepareMessageToBroadcast(String message) {
        // broadcast of connected users
        if(message.equals("2#") || message.equals("5#")) {
            String reply = "2#";
            for(User u : this.users) {
                reply += u.toString() + "#";
            }
            return reply;
        } 


        // TODO, message broadcast
        
        return null;
    }
    
    public String separateString(String s, String pattern, int pos) {
        String[] parts = s.split(pattern);
        return parts[pos];
    }
    
}
