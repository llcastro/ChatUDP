package ChatUDP.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Reports {

    private String path;
    private Writer writer = null;

    public Reports(String path) {
        this.path = path;
    }

    public void report(String msg) {
        try {
            writer = new FileWriter(new File(path), true);
            writer.write(msg + "\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/
            }
        }
    }
}
