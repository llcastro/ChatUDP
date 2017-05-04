package ChatUDP.servidor;

import ChatUDP.model.User;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luis
 */
public class TelaServidor extends javax.swing.JFrame implements Runnable {

    private DatagramPacket dpacket;
    private DatagramSocket dsocket;
    private int port;
    private boolean status = false;

    private ArrayList<User> usersConnected;

    public TelaServidor() {
        initComponents();

        this.usersConnected = new ArrayList<>();
        jTableLogados.setModel(new TableModelServidor(this.usersConnected));

        this.initSocket();
    }

    private void initSocket() {
        try {
            this.port = Integer.valueOf(this.jFormattedTextFieldPortaServidor.getText().toString());
            if (this.port > 0) {
                this.dsocket = new DatagramSocket(this.port);
            }
        } catch (SocketException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WestPanel = new javax.swing.JPanel();
        jCheckBoxBroadcast = new javax.swing.JCheckBox();
        jScrollPaneTabelaConectados = new javax.swing.JScrollPane();
        jTableLogados = new javax.swing.JTable();
        NorthPanel = new javax.swing.JPanel();
        rigidBox4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jLabelPortaServidor = new javax.swing.JLabel();
        rigidBox6 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jFormattedTextFieldPortaServidor = new javax.swing.JFormattedTextField();
        rigidBox5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jButtonConectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 10, 10));

        WestPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        WestPanel.setMaximumSize(new java.awt.Dimension(100, 300));
        WestPanel.setMinimumSize(new java.awt.Dimension(100, 49));
        WestPanel.setPreferredSize(new java.awt.Dimension(350, 430));
        WestPanel.setLayout(new javax.swing.BoxLayout(WestPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jCheckBoxBroadcast.setText("Broadcast");
        WestPanel.add(jCheckBoxBroadcast);

        jTableLogados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneTabelaConectados.setViewportView(jTableLogados);

        WestPanel.add(jScrollPaneTabelaConectados);

        getContentPane().add(WestPanel, java.awt.BorderLayout.CENTER);

        NorthPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        NorthPanel.setLayout(new javax.swing.BoxLayout(NorthPanel, javax.swing.BoxLayout.LINE_AXIS));
        NorthPanel.add(rigidBox4);

        jLabelPortaServidor.setText("IP do Servidor: ");
        NorthPanel.add(jLabelPortaServidor);
        NorthPanel.add(rigidBox6);

        jFormattedTextFieldPortaServidor.setText("22000");
        NorthPanel.add(jFormattedTextFieldPortaServidor);
        NorthPanel.add(rigidBox5);

        jButtonConectar.setText("Conectar");
        jButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarActionPerformed(evt);
            }
        });
        NorthPanel.add(jButtonConectar);

        getContentPane().add(NorthPanel, java.awt.BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarActionPerformed

        byte[] byteMessage = new byte[1000];
        dpacket = new DatagramPacket(byteMessage, byteMessage.length);
        System.out.println("Server up");

        if (status == false) {
            status = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }//GEN-LAST:event_jButtonConectarActionPerformed

    private String chooseAction(String message) {
        String[] parts = message.split("#");

        if (parts.length == 2 && parts[0].equals("1") && !parts[1].equals("")) {
            this.usersConnected.add(
                    new User(
                            parts[1], dpacket.getAddress().toString(),
                            dpacket.getPort()));
            ((AbstractTableModel) jTableLogados.getModel()).fireTableDataChanged();
            System.out.println("Cliente " + parts[1] + " logado");
            return "1";
        } else if (parts.length == 1 && parts[0].equals("5")) {
            int i = searchUser(dpacket.getAddress().toString());
            if (i != -1) {
                System.out.println("Usuário: "
                        + this.usersConnected.get(i).getUserName() + " desconectado");
                this.usersConnected.remove(i);
            }
            ((AbstractTableModel) jTableLogados.getModel()).fireTableDataChanged();
            return "5";
        } else if (parts.length == 1 && parts[0].equals("listar")) {
            return addConnected();
        }

        return "protocolo desconhecido: " + message;
    }

    private String addConnected() {
        String message = "";
        for (User u : this.usersConnected) {
            message += u.toString() + '#';
        }
        return message;
    }

    private int searchUser(String ip) {
        for (User u : this.usersConnected) {
            if (u.getIp().equals(ip)) {
                return this.usersConnected.indexOf(u);
            }
        }
        return -1;
    }

    @Override
    public void run() {
        // udate table of users connected
        try {
            while (true) {
                dsocket.receive(dpacket);

                byte[] me = dpacket.getData();
                int packSize = dpacket.getLength();
                String s = new String(me, 0, packSize).trim();

                System.out.println(dpacket.getAddress() + ":"
                        + dpacket.getPort() + " :" + s + ":");

                s = chooseAction(s);
                me = s.getBytes();
                DatagramPacket reply = new DatagramPacket(
                        me, me.length, dpacket.getAddress(), dpacket.getPort());
                dsocket.send(reply);
            }
        } catch (IOException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaServidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NorthPanel;
    private javax.swing.JPanel WestPanel;
    private javax.swing.JButton jButtonConectar;
    private javax.swing.JCheckBox jCheckBoxBroadcast;
    private javax.swing.JFormattedTextField jFormattedTextFieldPortaServidor;
    private javax.swing.JLabel jLabelPortaServidor;
    private javax.swing.JScrollPane jScrollPaneTabelaConectados;
    private javax.swing.JTable jTableLogados;
    private javax.swing.Box.Filler rigidBox4;
    private javax.swing.Box.Filler rigidBox5;
    private javax.swing.Box.Filler rigidBox6;
    // End of variables declaration//GEN-END:variables
}
