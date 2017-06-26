
package ChatUDP.model;

/**
 *
 * @author luis
 */
public class VOConfigMail {
    
    private String login;// = "distribuidos.ads@gmail.com";
    private String passwd;// = "2017sistemas";
    private String hostSmtp;// = "smtp.gmail.com";
    private String hostImap;// = "imap.gmail.com";
    private int portSmtp;// = 587;
    private int portImap;// = 993;

    public VOConfigMail(String login, String passwd, String hostSmtp, 
            String hostImap, int portSmtp, int portImap) {
        this.login = login;
        this.passwd = passwd;
        this.hostSmtp = hostSmtp;
        this.hostImap = hostImap;
        this.portSmtp = portSmtp;
        this.portImap = portImap;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getHostSmtp() {
        return hostSmtp;
    }

    public void setHostSmtp(String hostSmtp) {
        this.hostSmtp = hostSmtp;
    }

    public String getHostImap() {
        return hostImap;
    }

    public void setHostImap(String hostImap) {
        this.hostImap = hostImap;
    }

    public int getPortSmtp() {
        return portSmtp;
    }

    public void setPortSmtp(int portSmtp) {
        this.portSmtp = portSmtp;
    }

    public int getPortImap() {
        return portImap;
    }

    public void setPortImap(int portImap) {
        this.portImap = portImap;
    }
    
    
}
