
package ChatUDP.cliente;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

/**
 *
 * @author luis
 */
public class MailApp {
    
    private IMAPFolder folder;
    private Store store;
    private Message[] mensagens;
    private Session session;

    private String login;// = "distribuidos.ads@gmail.com";
    private String passwd;// = "2017sistemas";
    private String hostSmtp;// = "smtp.gmail.com";
    private String hostImap;// = "imap.gmail.com";
    private int portSmtp;// = 587;
    private int portImap;// = 993;

    public MailApp() {
    }

    public MailApp(String login, String passwd, String hostSmtp, 
                   String hostImap, int portSmtp, int portImap) {
        this.login = login;
        this.passwd = passwd;
        this.hostSmtp = hostSmtp;
        this.hostImap = hostImap;
        this.portImap = portImap;
        this.portSmtp = portSmtp;
    }

    public void responderEmail(Message mensagem, String resposta) {
        try {
            Message replyMessage = new MimeMessage(session);
            replyMessage = (MimeMessage) mensagem.reply(false);
            replyMessage.setFrom(new InternetAddress(getLogin()));
            replyMessage.setText(resposta);
            replyMessage.setReplyTo(mensagem.getReplyTo());
            Transport t = session.getTransport("smtp");
            t.connect(getHostSmtp(), getPortaSmtp(), getLogin(), getSenha());
            t.sendMessage(replyMessage, replyMessage.getAllRecipients());
            
            t.close();
            mensagem.setFlag(Flag.SEEN, true);
        } catch (MessagingException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            folder.close(false);
        } catch (MessagingException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void conectar() {
        try {
            Properties props = new Properties();
            
            //#########necessário caso for usar gmail#################
            //String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            //props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
            //props.setProperty("mail.imap.socketFactory.fallback", "false");
            //props.setProperty("mail.imap.socketFactory.port", String.valueOf(getPortaImap()));
            //props.setProperty("mail.imap.ssl.trust", getHostImap());
            //props.setProperty("mail.smtp.ssl.trust", getHostSmtp());
            //props.setProperty("mail.smtp.starttls.enable","true");
            props.setProperty("mail.imap.port", String.valueOf(getPortaImap()));
            props.setProperty("mail.imap.host", getHostImap());
            //props.setProperty("mail.store.protocol", "imap");
            
            URLName url = new URLName("imap", getHostImap(), getPortaImap(), "", getLogin(), getSenha());
            
            session = Session.getDefaultInstance(props, null);
            store = session.getStore(url);
            
            store.connect();
            folder = (IMAPFolder) store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            atualizar();
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    private void atualizar() {
        try {
            mensagens = getF().search(new FlagTerm(new Flags(Flags.Flag.ANSWERED), false));
            /*Arrays.sort(getMensagens(), ( m1, m2 ) -> {
            try {
            return m2.getSentDate().compareTo( m1.getSentDate() );
            } catch ( MessagingException e ) {
            throw new RuntimeException( e );
            }
            } );
            */
        } catch (MessagingException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public String processMessageBody(Message message) {

        String s = "";
        try {
            Object content = message.getContent();
            if (content instanceof String) {
                return content.toString();
            } else if (content instanceof Multipart) {
                Multipart multiPart = (Multipart) content;
                s = procesMultiPart(multiPart);
            } else if (content instanceof InputStream) {
                InputStream inStream = (InputStream) content;
                int ch;
                while ((ch = inStream.read()) != -1) {
                    s += ch;
                }
            }
            message.setFlag(Flag.SEEN, false);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return s;
    }

    public String procesMultiPart(Multipart content) {
        String s = "";
        try {
            int multiPartCount = content.getCount();
            for (int i = 0; i < multiPartCount; i++) {
                BodyPart bodyPart = content.getBodyPart(i);
                Object o;
                o = bodyPart.getContent();
                if (o instanceof String) {
                    return o.toString();
                } else if (o instanceof Multipart) {
                    s += procesMultiPart((Multipart) o);
                }
            }
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return s;
    }

    public IMAPFolder getF() {
        return folder;
    }

    public void setF(IMAPFolder f) {
        this.folder = f;
    }

    public Message[] getMensagens() {
        return mensagens;
    }

    public void setMensagens(Message[] mensagens) {
        this.mensagens = mensagens;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return passwd;
    }

    public void setSenha(String senha) {
        this.passwd = senha;
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

    public int getPortaSmtp() {
        return portSmtp;
    }

    public void setPortaSmtp(int portaSmtp) {
        this.portSmtp = portaSmtp;
    }

    public int getPortaImap() {
        return portImap;
    }

    public void setPortaImap(int portaImap) {
        this.portImap = portaImap;
    }
}
