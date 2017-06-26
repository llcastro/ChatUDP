
package ChatUDP.model;

/**
 *
 * @author luis
 */
public class VOMail {
    
    private String remetente, assunto;

    public VOMail(String remetente, String assunto) {
        this.remetente = remetente;
        this.assunto = assunto;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    
}
