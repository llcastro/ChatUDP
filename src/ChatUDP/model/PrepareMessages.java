
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
        
        if(message.startsWith("2")) {
            String[] parts = message.split("#");
            this.users.clear();
            int size = parts.length;
            
            for (int i = 1; i < size; i = i + 3) {
                this.users.add(
                        new User(parts[i+2],
                                parts[i],
                                Integer.valueOf(parts[i+1])));
            }
            return 2;
        } else if(message.startsWith("5")) {
            // user disconnect
            return 5;
        } else if(message.startsWith("4")) {
            // receive message
            return 4;
        }
        
        return -1;
    }
    
    // used by server
    public String prepareMessageToBroadcast(String message) {
        // broadcast of connected users
        if(message.equals("2#")) {
            String reply = "2";
            for(User u : this.users) {
                reply += "#" + u.toString();
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
    
    // returns the index of the user or -1 if he doesn't exist
    public int searchUser(String ip, int port) {

        for (User u : this.users) {
            if (u.getIp().equals(ip) && u.getPort() == port) {
                return this.users.indexOf(u);
            }
        }
        return -1;
    }
    
    // return the full message received
    // pos = position of the '#' to start substring
    public String returnMessage(String message, int pos) {
        
        char[] m = message.toCharArray();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            if(m[i] == '#') {
                j++;
            }
            if(j == pos) {
                j = i+1;
                break;
            }
        }
        
        return message.substring(j, message.length());
    }
    
}
